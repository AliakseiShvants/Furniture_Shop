INSERT INTO role(title) VALUES ('ROLE_GUEST');
INSERT INTO role(title) VALUES ('ROLE_USER');
INSERT INTO role(title) VALUES ('ROLE_ADMIN');

INSERT INTO customer (FULLNAME, LOGIN, PASSWORD, email, role_id)
VALUES ('Nadzeja Shvants', 'nad', '123', 'nada@gmail.com', 2),
       ('masha shvants', 'ms@2003', '2003', 'masha@mail.ru', 2),
       ('Алексей Шванц', 'ashvants', '11091991', 'ashvants91@gmail.com', 3);