-- liquibase formatted sql

-- changeset codenation:1.0 dbms:mysql
CREATE TABLE IF NOT EXISTS 'User' (
  'id' BIGINT(20) NOT NULL AUTO_INCREMENT,
  'name' VARCHAR(50) NOT NULL,
  'email' VARCHAR(120) NOT NULL,
  'password_hash' VARCHAR(128) NOT NULL,
  'last_seen' DATETIME NOT NULL,
  PRIMARY KEY ('id'),
  UNIQUE INDEX 'id_UNIQUE' ('id' ASC) VISIBLE,
  UNIQUE INDEX 'email_UNIQUE' ('email' ASC) VISIBLE)
ENGINE = InnoDB;

-- changeset codenation:1.1 dbms:mysql
CREATE TABLE IF NOT EXISTS 'Environment' (
  'id' BIGINT(20) NOT NULL AUTO_INCREMENT,
  'name' VARCHAR(128) NOT NULL,
  PRIMARY KEY ('id'),
  UNIQUE INDEX 'id_UNIQUE' ('id' ASC) VISIBLE)
ENGINE = InnoDB;

-- changeset codenation:1.2 dbms:mysql
CREATE TABLE IF NOT EXISTS 'Level' (
  'id' BIGINT(20) NOT NULL AUTO_INCREMENT,
  'name' VARCHAR(45) NOT NULL,
  PRIMARY KEY ('id'),
  UNIQUE INDEX 'id_UNIQUE' ('id' ASC) VISIBLE)
ENGINE = InnoDB;

-- changeset codenation:1.3 dbms:mysql
CREATE TABLE IF NOT EXISTS 'Log' (
  'id' BIGINT(20) NOT NULL AUTO_INCREMENT,
  'levelId' BIGINT(20) NOT NULL,
  'title' VARCHAR(255) NOT NULL,
  'description' VARCHAR(256) NOT NULL,
  'details' LONGTEXT NOT NULL,
  'origin' VARCHAR(128) NOT NULL,
  'environmentId' BIGINT(20) NOT NULL,
  'timestamp' DATETIME NOT NULL,
  'status' TINYINT NOT NULL,
  PRIMARY KEY ('id'),
  INDEX 'id_idx' ('environmentId' ASC) VISIBLE,
  INDEX 'id_idx1' ('levelId' ASC) VISIBLE,
  CONSTRAINT 'id'
    FOREIGN KEY ('environmentId')
    REFERENCES'Environment' ('id')
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT 'id'
    FOREIGN KEY ('levelId')
    REFERENCES'Level' ('id')
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- changeset codenation:1.4 dbms:mysql
CREATE TABLE IF NOT EXISTS 'Role' (
  'id' BIGINT(20) NOT NULL AUTO_INCREMENT,
  'name' VARCHAR(45) NOT NULL,
  PRIMARY KEY ('id'),
  UNIQUE INDEX 'name_UNIQUE' ('name' ASC) VISIBLE,
  UNIQUE INDEX 'id_UNIQUE' ('id' ASC) VISIBLE)
ENGINE = InnoDB;

-- changeset codenation:1.5 dbms:mysql
CREATE TABLE IF NOT EXISTS 'User_Role' (
  'id' BIGINT(20) NOT NULL AUTO_INCREMENT,
  'userId' BIGINT(20) NOT NULL,
  'RoleId' BIGINT(20) NOT NULL,
  PRIMARY KEY ('id'),
  UNIQUE INDEX 'id_UNIQUE' ('id' ASC) VISIBLE,
  INDEX 'id_idx' ('RoleId' ASC) VISIBLE,
  INDEX 'id_idx1' ('userId' ASC) VISIBLE,
  CONSTRAINT 'id'
    FOREIGN KEY ('userId')
    REFERENCES'User' ('id')
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT 'id'
    FOREIGN KEY ('RoleId')
    REFERENCES'Role' ('id')
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

