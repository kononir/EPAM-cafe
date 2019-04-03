CREATE TRIGGER constraints_user
  BEFORE INSERT
  ON user
  FOR EACH ROW
BEGIN
  IF NEW.Role = 'CLIENT' AND NEW.Score IS NULL
  THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Wrong score entered. Client have to has score.';
  END IF;

  IF NEW.Role = 'CLIENT' AND NEW.AccountId IS NULL
  THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Wrong account id entered. Client have to has account.';
  END IF;

  IF NEW.Role = 'ADMINISTRATOR' AND NEW.Score IS NOT NULL
  THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Wrong score entered. Administrator can not has score.';
  END IF;

  IF NEW.Role = 'ADMINISTRATOR' AND NEW.AccountId IS NOT NULL
  THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Wrong score entered. Administrator can not has account.';
  END IF;
END;