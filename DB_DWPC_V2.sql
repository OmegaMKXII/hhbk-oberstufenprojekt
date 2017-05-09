
CREATE DATABASE DB_DWPC;

USE DB_DWPC;

  CREATE TABLE IF NOT EXISTS `Authorisation` (
  `AuthorisationID` int(255)  NOT NULL AUTO_INCREMENT,
  `Role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`AuthorisationID`));
  
CREATE TABLE IF NOT EXISTS `User` (
  `UserID` int(255)  NOT NULL AUTO_INCREMENT,
  `Username` varchar(255) DEFAULT NULL,
  `Password` varchar(255) DEFAULT NULL,
  `FK_AuthorisationID` int(6) DEFAULT NULL,
  PRIMARY KEY (`UserID`),
  FOREIGN KEY (FK_AuthorisationID) REFERENCES Authorisation(AuthorisationID));
  
  CREATE TABLE IF NOT EXISTS `Template` (
  `TemplateID` int(255)  NOT NULL AUTO_INCREMENT,
  `Title` varchar(255) DEFAULT NULL,
  `FK_UserID` int(6) DEFAULT NULL,
  PRIMARY KEY (`TemplateID`),
  FOREIGN KEY (FK_UserID) REFERENCES User(UserID));
  
   CREATE TABLE IF NOT EXISTS `Configuration` (
  `ConfigurationID` int(255)  NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ConfigurationID`));
  
  CREATE TABLE IF NOT EXISTS `Template_Configuration` (
  `FK_TemplateID` int(255) ,
  `FK_ConfigurationID` int(255),
  FOREIGN KEY (FK_TemplateID) REFERENCES Template(TemplateID),
  FOREIGN KEY (FK_ConfigurationID) REFERENCES Configuration(ConfigurationID),
  PRIMARY KEY (FK_TemplateID, FK_ConfigurationID));

  
    INSERT INTO Authorisation VALUES
    (NULL,"ADMIN"),
    (NULL,"TEACHER"),
    (NULL,"GUEST");

    INSERT INTO User VALUES
    (NULL,"K","k",1),
     (NULL,"Dresen","dresen",2),
    (NULL,"Monika","test",3);
	
	INSERT INTO template (title, fk_userid) VALUES 
	('kenjis', '1'), 
	('monis', '3');
	
	INSERT INTO configuration (name) VALUES 
	('kompetenzen'),  
	('handlungsprodukt');
	
	INSERT INTO template_configuration (fk_templateid, fk_configurationid) VALUES 
	(1, 1),  
	(2,1),  
	(1,2);
