INSERT INTO furniture_shop.role(title) VALUES ('ROLE_USER'), ('ROLE_MANAGER'), ('ROLE_ADMIN');

INSERT INTO furniture_shop.requisite(zip, country, city, address)
VALUES  ('230010', 'РБ', 'Гродно', 'ул. Кабяка, 12/2-136'),
  ('223610', 'РБ', 'Слуцк', 'ул. Серпуховская, 6-24'),
  ('230017', 'РБ', 'Гродно', 'ул. Огинского, 1-23');

INSERT INTO furniture_shop.customer (FULLNAME, LOGIN, PASSWORD, email, role_id, requisite_id)
VALUES ('Nadzeja Shvants', 'nad', '123', 'nada@gmail.com', 1, 1),
       ('masha shvants', 'ms@2003', '2003', 'masha@mail.ru', 1, 2);
INSERT INTO furniture_shop.customer (FULLNAME, LOGIN, PASSWORD, email, role_id, requisite_id, team)
VALUES ('Бовшевич Анна', 'ane4ka', '24121986', 'ab@gmail.com', 2, 3, 1),
       ('Алексей Шванц', 'ashvants', '11091991', 'ashvants91@gmail.com', 2, 3, 2);
INSERT INTO furniture_shop.customer (FULLNAME, LOGIN, PASSWORD, email, role_id)
VALUES ('Aliaksei Shvants', 'admin', 'admin', 'admin@gmail.com', 3);

