SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

CREATE TABLE IF NOT EXISTS `artikel` (
  `code` varchar(45) NOT NULL,
  `naam` varchar(45) DEFAULT NULL,
  `aantal` int(11) DEFAULT NULL,
  `prijs` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `auto` (
`autoid` int(11) NOT NULL,
  `merk` varchar(45) DEFAULT NULL,
  `model` varchar(45) DEFAULT NULL,
  `bouwjaar` int(11) DEFAULT NULL,
  `kenteken` char(6) DEFAULT NULL,
  `laatste_beurt` timestamp NULL DEFAULT NULL,
  `klant` varchar(45) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `factuur` (
`factuurid` int(11) NOT NULL,
  `betaald` tinyint(1) NOT NULL DEFAULT '0',
  `datum` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `factuuronderdeel` (
`factuuronderdeelid` int(11) NOT NULL,
  `factuurid` int(11) NOT NULL,
  `reserveringid` int(11) DEFAULT NULL,
  `klusid` int(11) DEFAULT NULL,
  `totaalprijs` double NOT NULL,
  `omschrijving` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `klant` (
  `gebruikersnaam` varchar(45) NOT NULL,
  `wachtwoord` char(128) DEFAULT NULL,
  `laatste_bezoek` timestamp NULL DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `naam` varchar(45) DEFAULT NULL,
  `adres` varchar(100) DEFAULT NULL,
  `postcode` char(6) DEFAULT NULL,
  `woonplaats` varchar(100) DEFAULT NULL,
  `telefoonnummer` varchar(14) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `klus` (
`idklus` int(11) NOT NULL,
  `type` varchar(45) DEFAULT NULL,
  `klaar` tinyint(1) DEFAULT NULL,
  `datum` timestamp NULL DEFAULT NULL,
  `omschrijving` text,
  `monteur` varchar(45) DEFAULT NULL,
  `klant` varchar(45) NOT NULL,
  `auto` int(11) NOT NULL,
  `uren` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `monteur` (
  `gebruikersnaam` varchar(45) NOT NULL,
  `wachtwoord` char(128) DEFAULT NULL,
  `naam` varchar(45) DEFAULT NULL,
  `salarisnummer` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `onderdeel` (
  `idklus` int(11) NOT NULL,
  `code` varchar(45) NOT NULL,
  `aantal` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `parkeerplek` (
`parkeerplekid` int(11) NOT NULL,
  `rij` char(1) NOT NULL,
  `plek` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `reservering` (
`reserveringid` int(11) NOT NULL,
  `van` timestamp NULL DEFAULT NULL,
  `tot` timestamp NULL DEFAULT NULL,
  `auto` int(11) NOT NULL,
  `klant` varchar(45) NOT NULL,
  `parkeerplek` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


ALTER TABLE `artikel`
 ADD PRIMARY KEY (`code`);

ALTER TABLE `auto`
 ADD PRIMARY KEY (`autoid`), ADD KEY `klant_idx` (`klant`);

ALTER TABLE `factuur`
 ADD PRIMARY KEY (`factuurid`);

ALTER TABLE `factuuronderdeel`
 ADD PRIMARY KEY (`factuuronderdeelid`), ADD KEY `factuuronderdeel_factuur_idx` (`factuurid`), ADD KEY `factuuronderdeel_reservering_idx` (`reserveringid`), ADD KEY `factuuronderdeel_klus_idx` (`klusid`);

ALTER TABLE `klant`
 ADD PRIMARY KEY (`gebruikersnaam`);

ALTER TABLE `klus`
 ADD PRIMARY KEY (`idklus`), ADD KEY `monteur_idx` (`monteur`), ADD KEY `klant_idx` (`klant`), ADD KEY `auto_idx` (`auto`);

ALTER TABLE `monteur`
 ADD PRIMARY KEY (`gebruikersnaam`);

ALTER TABLE `onderdeel`
 ADD PRIMARY KEY (`idklus`,`code`), ADD KEY `code_idx` (`code`);

ALTER TABLE `parkeerplek`
 ADD PRIMARY KEY (`parkeerplekid`);

ALTER TABLE `reservering`
 ADD PRIMARY KEY (`reserveringid`), ADD KEY `reservering_auto_idx` (`auto`), ADD KEY `reservering_klant_idx` (`klant`), ADD KEY `reservering_parkeerplek_idx` (`parkeerplek`);


ALTER TABLE `auto`
MODIFY `autoid` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;
ALTER TABLE `factuur`
MODIFY `factuurid` int(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `factuuronderdeel`
MODIFY `factuuronderdeelid` int(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `klus`
MODIFY `idklus` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;
ALTER TABLE `parkeerplek`
MODIFY `parkeerplekid` int(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `reservering`
MODIFY `reserveringid` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `auto`
ADD CONSTRAINT `klantauto` FOREIGN KEY (`klant`) REFERENCES `klant` (`gebruikersnaam`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `factuuronderdeel`
ADD CONSTRAINT `factuuronderdeel_factuur` FOREIGN KEY (`factuurid`) REFERENCES `factuur` (`factuurid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `factuuronderdeel_klus` FOREIGN KEY (`klusid`) REFERENCES `klus` (`idklus`) ON DELETE SET NULL ON UPDATE SET NULL,
ADD CONSTRAINT `factuuronderdeel_reservering` FOREIGN KEY (`reserveringid`) REFERENCES `reservering` (`reserveringid`) ON DELETE SET NULL ON UPDATE SET NULL;

ALTER TABLE `klus`
ADD CONSTRAINT `auto` FOREIGN KEY (`auto`) REFERENCES `auto` (`autoid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `klant` FOREIGN KEY (`klant`) REFERENCES `klant` (`gebruikersnaam`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `monteur` FOREIGN KEY (`monteur`) REFERENCES `monteur` (`gebruikersnaam`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `onderdeel`
ADD CONSTRAINT `code` FOREIGN KEY (`code`) REFERENCES `artikel` (`code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `idklus` FOREIGN KEY (`idklus`) REFERENCES `klus` (`idklus`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `reservering`
ADD CONSTRAINT `reservering_auto` FOREIGN KEY (`auto`) REFERENCES `auto` (`autoid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `reservering_klant` FOREIGN KEY (`klant`) REFERENCES `klant` (`gebruikersnaam`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `reservering_parkeerplek` FOREIGN KEY (`parkeerplek`) REFERENCES `parkeerplek` (`parkeerplekid`) ON DELETE NO ACTION ON UPDATE NO ACTION;


INSERT INTO `atd`.`parkeerplek` (`parkeerplekid`, `rij`, `plek`) VALUES (NULL, 'A', '1'), (NULL, 'A', '2'), (NULL, 'A', '3'), (NULL, 'B', '1'), (NULL, 'B', '2'), (NULL, 'B', '3'), (NULL, 'C', '1'), (NULL, 'C', '2'), (NULL, 'C', '3');
