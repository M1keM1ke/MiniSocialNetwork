delete from user_role; -- чистим бд перед тестом
delete from usr;

insert into usr(id, active, password, username) values
  (1, true, '$2a$08$w4iD4NupCez2NU7Vja41ceW5IDw2K6pWadDFsGrOJrXIdbJbhDlOC', 'admin'),
  (2, true, '$2a$08$w4iD4NupCez2NU7Vja41ceW5IDw2K6pWadDFsGrOJrXIdbJbhDlOC', 'mike');

insert into user_role(user_id, roles) values
  (1, 'ADMIN'), (2, 'USER'),
  (2, 'USER');
