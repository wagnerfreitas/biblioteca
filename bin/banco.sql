-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.53-community


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema biblioteca
--

CREATE DATABASE IF NOT EXISTS biblioteca;
USE biblioteca;

--
-- Definition of table `emprestimo`
--

DROP TABLE IF EXISTS `emprestimo`;
CREATE TABLE `emprestimo` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idUsuario` int(10) unsigned NOT NULL,
  `idLivro` int(10) unsigned NOT NULL,
  `dataDeEmprestimo` datetime NOT NULL,
  `dataDeEntrega` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_emprestimo_usuario` (`idUsuario`),
  KEY `FK_emprestimo_livro` (`idLivro`),
  CONSTRAINT `FK_emprestimo_livro` FOREIGN KEY (`idLivro`) REFERENCES `livro` (`id`),
  CONSTRAINT `FK_emprestimo_usuario` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `emprestimo`
--

/*!40000 ALTER TABLE `emprestimo` DISABLE KEYS */;
/*!40000 ALTER TABLE `emprestimo` ENABLE KEYS */;


--
-- Definition of table `livro`
--

DROP TABLE IF EXISTS `livro`;
CREATE TABLE `livro` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `autor` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `livro`
--

/*!40000 ALTER TABLE `livro` DISABLE KEYS */;
/*!40000 ALTER TABLE `livro` ENABLE KEYS */;


--
-- Definition of table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `usuario`
--

/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;