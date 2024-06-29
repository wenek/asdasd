CREATE TABLE users_holidays
(
    user_id    INT NOT NULL,
    holiday_id INT NOT NULL,
    CONSTRAINT pk_users_holidays PRIMARY KEY (user_id, holiday_id)
);

INSERT INTO users_holidays(user_id, holiday_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (1, 5),
       (2, 10),
       (2, 11)