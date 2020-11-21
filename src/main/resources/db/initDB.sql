DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 1;

CREATE TABLE users
(
    id         INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name       VARCHAR                     NOT NULL,
    email      VARCHAR                          NOT NULL,
    password   VARCHAR                           NOT NULL,
    role       VARCHAR             DEFAULT USER  NOT NULL,
    registered TIMESTAMP           DEFAULT now() NOT NULL,
    status     BOOL                DEFAULT TRUE  NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

