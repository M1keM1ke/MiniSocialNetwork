package com.example.potatoChips;

import com.example.potatoChips.controller.MainController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;


@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@WithUserDetails("admin") //авторизация для теста mainPageTest
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-user-before.sql", "/messages-list-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/messages-list-after.sql", "/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    MainController mainController;

    @Test
    public void mainPageTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/main"))
                .andDo(print())
                .andExpect(xpath("//*[@id='navbarSupportedContent']/div").string("admin"));
                //проверка на корректную аутентификацию(пройдет, если есть сессия)

    }

    @Test
    public void messageListTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/main"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(MockMvcResultMatchers.xpath("//div[@id='message-list']/div").nodeCount(4));
                        //кол-во элементов на странице (4 сообщения ожидаем из бд), т.е в контейнере 4 элемента
    }

    @Test
    public void filterMessageTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/main").param("filter", "my-tag"))
                .andDo(print())
                .andExpect(SecurityMockMvcResultMatchers.authenticated())
                .andExpect(MockMvcResultMatchers.xpath("//div[@id='message-list']/div").nodeCount(2))
                .andExpect(MockMvcResultMatchers.xpath("//div[@id='message-list']/div[@data-id=1]").exists())
                .andExpect(MockMvcResultMatchers.xpath("//div[@id='message-list']/div[@data-id=3]").exists());

    }

    @Test
    public void addMessageToListTest() throws Exception {
        MockHttpServletRequestBuilder multipart = multipart("/main")
                .file("file", "123".getBytes())
                .param("text", "fifth")
                .param("tag", "new one")
                .with(SecurityMockMvcRequestPostProcessors.csrf());

        mockMvc.perform(multipart)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(authenticated())
                .andExpect(MockMvcResultMatchers.xpath("//div[@id='message-list']/div").nodeCount(5))
                .andExpect(MockMvcResultMatchers.xpath("//div[@id='message-list']/div[@data-id=10]").exists())
                .andExpect(MockMvcResultMatchers.xpath("//div[@id='message-list']/div[@data-id=10]/div/span").string("fifth"))
                .andExpect(MockMvcResultMatchers.xpath("//div[@id='message-list']/div[@data-id=10]/div/i").string("new one"));

    }
}
