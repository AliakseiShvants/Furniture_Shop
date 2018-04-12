INSERT INTO furniture_shop.role(title) VALUES ('ROLE_USER'), ('ROLE_MANAGER'), ('ROLE_ADMIN');

INSERT INTO furniture_shop.requisite(zip, country, city, address)
VALUES  ('230010', 'РБ', 'Гродно', 'ул. Кабяка, 12/2-136'),
  ('223610', 'РБ', 'Слуцк', 'ул. Серпуховская, 6-24'),
  ('230017', 'РБ', 'Гродно', 'ул. Огинского, 1-23'),
  ('', 'КНР', 'Пекин', ''),
  ('', 'Тайвань', '', ''),
  ('65056', 'РФ', 'Москва', 'ул. Столяров, 10а');

INSERT INTO furniture_shop.users (FULLNAME, LOGIN, PASSWORD, email, role_id, requisite_id)
VALUES ('Nadzeja Shvants', 'nad', '123', 'nada@gmail.com', 1, 1),
       ('masha shvants', 'ms@2003', '2003', 'masha@mail.ru', 1, 2);
INSERT INTO furniture_shop.users (FULLNAME, LOGIN, PASSWORD, email, role_id, requisite_id, team)
VALUES ('Бовшевич Анна', 'ane4ka', '24121986', 'ab@gmail.com', 2, 3, 1),
       ('Алексей Шванц', 'ashvants', '11091991', 'ashvants91@gmail.com', 2, 3, 2);
INSERT INTO furniture_shop.users (FULLNAME, LOGIN, PASSWORD, email, role_id)
VALUES ('Aliaksei Shvants', 'admin', 'admin', 'admin@gmail.com', 3);

INSERT INTO furniture_shop.manufacturer(requisite_id, title)
    VALUES (4, 'China Furniture'), (5, 'Spring'), (6, 'Стол & стул');

INSERT INTO furniture_shop.category(title)
VALUES ('door'), ('chair'), ('table'), ('bed'), ('dresser');

INSERT INTO furniture_shop.product(manufacturer_id, category_id, name, description)
VALUES
  (1, 2, 'Пекин', 'Зеленый пластиковый стул со спинкой'),
  (1, 2, 'Восток', 'Красный деревянный стул с мягкой спинкой'),
  (1, 2, 'Шанхай', 'Белый офисный стул с мягкой спинкой на колесиках'),
  (1, 3, 'Хуанхэ', 'Круглый обеденный стол'),

  (2, 1, 'Стена', 'Деревянная дверь дверь'),
  (2, 1, 'Гора', 'Металлическая дверь'),
  (2, 2, 'Подмога', 'Стул для ног'),

  (3, 3, 'Застолье', 'Большой деревянный стол на 12 чел.'),
  (3, 4, 'Ложе', 'Кровать двуспальная широкая'),
  (3, 4, 'Койка', 'Кровать односпальная складная'),
  (3, 5, 'Зима', 'Шкаф зеркальный'),
  (3, 5, 'Осень', 'Шкаф-купе зеркальный'),
  (3, 5, 'Лето', 'Шкаф двудверный');

INSERT INTO furniture_shop.product_image(product_id, url)
    VALUES
      (1, '../../assets/img/chairs/green.jpg'),
      (2, '../../assets/img/chairs/red.jpg'),
      (3, '../../assets/img/chairs/white.jpg');

INSERT INTO furniture_shop.storage(product_id, code, price, quantity)
    VALUES
      (1, 'CH001', 10.00, 100),
      (2, 'CH002', 15.00, 80),
      (3, 'CH003', 11.00, 70),
      (4, 'TBL001', 110.00, 20),
      (5, 'DR001', 50.00, 20),
      (6, 'DR002', 150.00, 10),
      (7, 'CH004', 5.00, 100),
      (8, 'TBL002', 75.00, 21),
      (9, 'BD001', 175.00, 9),
      (10, 'BD002', 35.00, 45),
      (11, 'DRS001', 95.00, 12),
      (12, 'DRS002', 125.00, 10),
      (13, 'DRS003', 195.00, 5);

INSERT INTO furniture_shop.orders(customer_id, manager_id, create_date)
    VALUES
      (1, 3, '2018-04-12 08:00:00'),
      (1, 3, '2018-04-12 09:00:00');

INSERT INTO furniture_shop.order_details(order_id, product_id, quantity)
  VALUES
    (1, 1, 1),
    (1, 2, 2),
    (1, 3, 1),

    (2, 9, 1),
    (2, 10, 1);


