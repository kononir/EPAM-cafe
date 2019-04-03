CREATE TABLE user
(
  ID        int                              NOT NULL AUTO_INCREMENT,
  Login     varchar(90)                      NOT NULL,
  Password  varchar(90)                      NOT NULL,
  Name      varchar(90)                      NOT NULL,
  Surname   varchar(90)                      NOT NULL,
  Role      enum ('ADMINISTRATOR', 'CLIENT') NOT NULL,
  Score     int,
  AccountId int,

  PRIMARY KEY (ID),
  UNIQUE (Login),
  FOREIGN KEY (AccountId) REFERENCES account (ID),
  CHECK ( CASE WHEN Role = 'CLIENT' THEN (Score IS NOT NULL AND AccountId IS NOT NULL) END)
);