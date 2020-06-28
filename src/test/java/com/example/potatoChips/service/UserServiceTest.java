package com.example.potatoChips.service;

import com.example.potatoChips.domain.Role;
import com.example.potatoChips.domain.User;
import com.example.potatoChips.repos.UserRepo;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.mockito.Mockito.mock;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {



    private UserRepo userRepo = mock(UserRepo.class);
    private MailSender mailSender = mock(MailSender.class);
    private PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);

    private UserService userService = new UserService(userRepo, mailSender, passwordEncoder);
    //add user (if true) and send letter on mail test
    @Test
    public void addUser() {
        User user = new User();
        user.setEmail("example@mail.ru");
        boolean isUserCreated = userService.addUser(user);

        Assert.assertTrue(isUserCreated);
        Assert.assertNotNull(user.getActivationCode());
        //has a user role
        Assert.assertTrue(CoreMatchers.is(user.getRoles()).matches(Collections.singleton(Role.USER)));

        //testing that obj userRepo called once with save method and obj user
        Mockito.verify(userRepo, Mockito.times(1)).save(user);
        //we don't know what arguments user write in send method, so we add help methods (matchers)
        Mockito.verify(mailSender, Mockito.times(1))
                .send(ArgumentMatchers.eq(user.getEmail()),
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.anyString());
    }

    //add user (if true) and send letter on mail test
    @Test
    public void addUserFailTest() {
        User user = new User();
        user.setUsername("mike");

        Mockito.doReturn(new User()) //эмуляция того, что в бд уже существует такой юзер
                .when(userRepo)
                .findByUsername("mike");

        boolean isUserCreated = userService.addUser(user);
        Assert.assertFalse(isUserCreated);

        //testing that obj userRepo called once with save method and obj user
        Mockito.verify(userRepo, Mockito.times(0)).save(ArgumentMatchers.any(User.class));
        //we don't know what arguments user write in send method, so we add help methods (matchers)
        Mockito.verify(mailSender, Mockito.times(0))
                .send(ArgumentMatchers.anyString(),
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.anyString());
    }

    @Test
    public void activateUser() {
        User user = new User();
        user.setActivationCode("exActicationCode");

        Mockito.doReturn(user)
                .when(userRepo)
                .findByActivationCode(ArgumentMatchers.anyString());

        boolean isUserActivated = userService.activateUser(ArgumentMatchers.anyString());
        Assert.assertTrue(isUserActivated);
        Assert.assertNull(user.getActivationCode());

        Mockito.verify(userRepo, Mockito.times(1)).save(user);
    }

    @Test
    public void activateUserFailTest() {
        boolean isUserActivated = userService.activateUser(ArgumentMatchers.anyString());
        Assert.assertFalse(isUserActivated);
        Mockito.verify(userRepo, Mockito.times(0)).save(ArgumentMatchers.any(User.class));
    }


}