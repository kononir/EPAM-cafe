CREATE TABLE `order`
(
    ID          int      NOT NULL AUTO_INCREMENT,
    ReceiptTime datetime NOT NULL,
    Score       int,
    Comment     varchar(1000),
    UserID      int      NOT NULL,

    PRIMARY KEY (ID),
    FOREIGN KEY (UserID) REFERENCES user (ID)
);