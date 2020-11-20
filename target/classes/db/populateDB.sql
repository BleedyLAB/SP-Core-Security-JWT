DELETE
FROM users;
ALTER SEQUENCE global_seq RESTART WITH 1;

INSERT INTO users (name, email, password, role, status)
VALUES ('User', 'user@yandex.ru', '$2y$12$JV2HA4iHX9RylV.tWO11bOwB2aeKR0uuD6kBntD5BQ.Jsz8pD8DlS', 'USER', 'true'),
       ('Admin', 'admin@gmail.com', '$2y$12$ytm0tpETwes..ux.m1IMUuHY3qK9DLPMxAe6noRVdASEYChPYmvUO', 'ADMIN', 'true');
