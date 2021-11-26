CREATE DATABASE `plaza_estacionamiento` /*!40100 DEFAULT CHARACTER SET utf8 */;
CREATE TABLE `cocheras` (
  `idcocheras` int(11) NOT NULL AUTO_INCREMENT,
  `patente` varchar(6) NOT NULL,
  `hora_ocupacion` double NOT NULL,
  `tipo_auto` char(1) NOT NULL,
  `piso` int(11) NOT NULL,
  PRIMARY KEY (`idcocheras`,`patente`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;
CREATE TABLE `historico` (
  `idhistorico` int(11) NOT NULL AUTO_INCREMENT,
  `patente` varchar(6) NOT NULL,
  `hora_ocupacion` double NOT NULL,
  `tipo_auto` char(1) NOT NULL,
  `piso` int(11) NOT NULL,
  `hora_salida` double NOT NULL,
  `importe` double unsigned NOT NULL,
  PRIMARY KEY (`idhistorico`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
CREATE TABLE `tarifas` (
  `idtarifas` int(11) NOT NULL AUTO_INCREMENT,
  `tipo_auto` char(1) NOT NULL,
  `tarifa` double unsigned NOT NULL,
  PRIMARY KEY (`idtarifas`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
