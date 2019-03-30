CREATE TABLE User (
  ID int,
  Login varchar(90),
  Password varchar(90),
  Name varchar(90),
  Surname varchar(90),
  Role enum('ADMINISTRATOR', 'CLIENT'),
  AccountId int
);