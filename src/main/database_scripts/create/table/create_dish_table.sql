CREATE TABLE dish
(
  ID          int         NOT NULL AUTO_INCREMENT,
  Name        varchar(90) NOT NULL,
  Description varchar(1000),
  Cost        decimal     NOT NULL,
  IsInMenu    boolean     NOT NULL,

  PRIMARY KEY (ID)
);