CREATE TABLE privilege
(
  ID          int                   NOT NULL AUTO_INCREMENT,
  Type        enum ('BONUS', 'BAN') NOT NULL,
  Name        varchar(90)           NOT NULL,
  Description varchar(1000),
  UserID      int                   NOT NULL,

  PRIMARY KEY (ID),
  FOREIGN KEY (UserID) REFERENCES user (ID)
);