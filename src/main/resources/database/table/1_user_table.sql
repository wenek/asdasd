CREATE TABLE users
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    email    VARCHAR(254) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

INSERT INTO users(email, password)
VALUES ('a@wp.pl', '$argon2id$v=19$m=16384,t=2,p=1$fv2V3A+Z47ALNV/XMJOVMA$/jG0XCBrUS3xmroHAs1xOhSd5zJa4C8BEaowN2jz1Rg'),
       ('b@wp.pl', '$argon2id$v=19$m=16384,t=2,p=1$fv2V3A+Z47ALNV/XMJOVMA$/jG0XCBrUS3xmroHAs1xOhSd5zJa4C8BEaowN2jz1Rg'),
       ('c@wp.pl', '$argon2id$v=19$m=16384,t=2,p=1$fv2V3A+Z47ALNV/XMJOVMA$/jG0XCBrUS3xmroHAs1xOhSd5zJa4C8BEaowN2jz1Rg'),
       ('d@wp.pl', '$argon2id$v=19$m=16384,t=2,p=1$fv2V3A+Z47ALNV/XMJOVMA$/jG0XCBrUS3xmroHAs1xOhSd5zJa4C8BEaowN2jz1Rg'),
       ('e@wp.pl', '$argon2id$v=19$m=16384,t=2,p=1$fv2V3A+Z47ALNV/XMJOVMA$/jG0XCBrUS3xmroHAs1xOhSd5zJa4C8BEaowN2jz1Rg')