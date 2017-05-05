
CREATE DATABASE DB_DWPC;

USE DB_DWPC;

  CREATE TABLE IF NOT EXISTS `Authorisation` (
  `AuthorisationID` int(255)  NOT NULL AUTO_INCREMENT,
  `Role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`AuthorisationID`));
  
CREATE TABLE IF NOT EXISTS `User` (
  `UserID` int(255)  NOT NULL AUTO_INCREMENT,
  `User` varchar(255) DEFAULT NULL,
  `Password` varchar(255) DEFAULT NULL,
  `FK_AuthorizationID` int(6) DEFAULT NULL,
  PRIMARY KEY (`UserID`),
  FOREIGN KEY (FK_AuthorizationID) REFERENCES Authorisation(AuthorisationID));
  
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
  
  
  //inserts
  INSERT INTO Authorisation values
  (NULL,"Admin"),
  (NULL,"Teacher"),
  (NULL,"Guest");
  
  INSERT INTO User values
  (NULL,"Kenji","kenji",1),
   (NULL,"Dresen","dresen",2),
  (NULL,"Monika","test",3);
  
  INSERT INTO Template values
  (NULL, "testTemplate1",1),
  (NULL, "testTemplate2",2),
   (NULL, "testTemplate3",3);
   
   INSERT INTO Configuration values (NULL, "Einstiegsszenario"), (NULL, "Handlungsprodukt/Lernergebnis"), (NULL, "Wesentliche Kompetenzen"), (NULL, "Inhalte"), (NULL, "Unterrichtsmaterialien"), (NULL, "Organisatorische Hinweise"), (NULL, "Lern- und Arbeitstechniken"), (NULL, "Leistungsnachweis");
  

  
  
  

  