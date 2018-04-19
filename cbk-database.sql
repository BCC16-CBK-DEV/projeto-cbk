-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           10.1.31-MariaDB - mariadb.org binary distribution
-- OS do Servidor:               Win32
-- HeidiSQL Versão:              9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Copiando estrutura para tabela cbk_database.cidade
CREATE TABLE IF NOT EXISTS `cidade` (
  `id_cidade` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(120) DEFAULT NULL,
  `id_estado` int(5) DEFAULT NULL,
  PRIMARY KEY (`id_cidade`),
  KEY `fk_Cidade_estado` (`id_estado`),
  CONSTRAINT `fk_Cidade_estado` FOREIGN KEY (`id_estado`) REFERENCES `estado` (`id_estado`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela cbk_database.cidade: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `cidade` DISABLE KEYS */;
/*!40000 ALTER TABLE `cidade` ENABLE KEYS */;

-- Copiando estrutura para tabela cbk_database.cliente
CREATE TABLE IF NOT EXISTS `cliente` (
  `id_cliente` int(11) NOT NULL AUTO_INCREMENT,
  `nome_cliente` varchar(70) NOT NULL,
  `cpf` char(14) NOT NULL,
  `rg` char(12) DEFAULT NULL,
  `cep` varchar(9) NOT NULL,
  `endereco` varchar(50) NOT NULL,
  `bairro` varchar(50) NOT NULL,
  `numero` int(11) DEFAULT NULL,
  `complemento` varchar(30) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `telefone` varchar(14) DEFAULT NULL,
  `celular` varchar(15) NOT NULL,
  `cidade` varchar(50) DEFAULT NULL,
  `uf` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela cbk_database.cliente: ~8 rows (aproximadamente)
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` (`id_cliente`, `nome_cliente`, `cpf`, `rg`, `cep`, `endereco`, `bairro`, `numero`, `complemento`, `email`, `telefone`, `celular`, `cidade`, `uf`) VALUES
	(1, 'Ubiraja Julião', '777.777.777-77', '77.777.777-7', '17520-250', 'Rua Ubiraja', 'Jd. Acapulco', 24, 'Casa', NULL, '14 34511697', '14 998546366', NULL, NULL),
	(2, '', 'ididids', 'idididid', 'dasdsad', 'dadadasds', 'dasdasd', 12, 'dsaasdasdsdas', 'dadasdasdas', 'dasdasd', 'dasdads', NULL, NULL),
	(3, 'dsad', '', '', '', '', '', 21, '', '', '', '', NULL, NULL),
	(4, '', '', '', '', '', '', 12, '', '', '', '', NULL, NULL),
	(5, '', '', '', '', '', '', 23, '', '', '', '', NULL, NULL),
	(6, '', '', '', '', '', '', 12, '', '', '', '', NULL, NULL),
	(7, '', '', '', '', '', '', 0, '', '', '', '', NULL, NULL),
	(8, 'Julio da Gaita', '462.829.518-28', '45.656.522-x', '17525-270', 'Rua Acapulco', 'Jd Acapulco', 12, 'Casa', 'igor492@gmail.com', '(14)998546366', '(14)998546366', NULL, NULL),
	(9, 'Igor Casconi', '123341', '4134342', '413412412', '41241241244', '1414241', 0, '412421412', '41242141', '412421412', '414141424', NULL, NULL);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;

-- Copiando estrutura para tabela cbk_database.departamento
CREATE TABLE IF NOT EXISTS `departamento` (
  `id_departamento` int(11) NOT NULL AUTO_INCREMENT,
  `nome_departamento` varchar(50) NOT NULL,
  PRIMARY KEY (`id_departamento`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela cbk_database.departamento: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `departamento` DISABLE KEYS */;
INSERT INTO `departamento` (`id_departamento`, `nome_departamento`) VALUES
	(1, 'administrador'),
	(2, 'recepcao'),
	(3, 'tecnico');
/*!40000 ALTER TABLE `departamento` ENABLE KEYS */;

-- Copiando estrutura para tabela cbk_database.informacao_autorizada
CREATE TABLE IF NOT EXISTS `informacao_autorizada` (
  `id_autorizada` int(11) NOT NULL AUTO_INCREMENT,
  `nome_autorizada` varchar(100) NOT NULL,
  PRIMARY KEY (`id_autorizada`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela cbk_database.informacao_autorizada: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `informacao_autorizada` DISABLE KEYS */;
INSERT INTO `informacao_autorizada` (`id_autorizada`, `nome_autorizada`) VALUES
	(1, 'MR ASSISTÊNCIA TÉCNICA');
/*!40000 ALTER TABLE `informacao_autorizada` ENABLE KEYS */;

-- Copiando estrutura para tabela cbk_database.ordem_servico
CREATE TABLE IF NOT EXISTS `ordem_servico` (
  `id_ordem` int(11) NOT NULL AUTO_INCREMENT,
  `numero_ordem` int(11) NOT NULL,
  `nota_fiscal` varchar(50) NOT NULL,
  `data_compra` date NOT NULL,
  `defeito_reclamado` text,
  `codigo_produto` varchar(30) NOT NULL,
  `descricao_produto` text,
  `voltagem` int(11) DEFAULT NULL,
  `numero_serie_produto` varchar(30) DEFAULT NULL,
  `id_cliente` int(11) NOT NULL,
  PRIMARY KEY (`id_ordem`),
  KEY `Fk_id_cliente` (`id_cliente`),
  CONSTRAINT `Fk_id_cliente` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela cbk_database.ordem_servico: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `ordem_servico` DISABLE KEYS */;
/*!40000 ALTER TABLE `ordem_servico` ENABLE KEYS */;

-- Copiando estrutura para tabela cbk_database.pedido_peca
CREATE TABLE IF NOT EXISTS `pedido_peca` (
  `id_pedido` int(11) NOT NULL AUTO_INCREMENT,
  `numero_pedido` int(11) NOT NULL,
  `codigo_peca` int(11) DEFAULT NULL,
  `descricao_peca` text,
  `quantidade_peca` int(11) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `id_ordem` int(11) NOT NULL,
  PRIMARY KEY (`id_pedido`),
  KEY `Fk_id_ordem` (`id_ordem`),
  CONSTRAINT `Fk_id_ordem` FOREIGN KEY (`id_ordem`) REFERENCES `ordem_servico` (`id_ordem`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela cbk_database.pedido_peca: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `pedido_peca` DISABLE KEYS */;
/*!40000 ALTER TABLE `pedido_peca` ENABLE KEYS */;

-- Copiando estrutura para tabela cbk_database.status_os
CREATE TABLE IF NOT EXISTS `status_os` (
  `id_status` int(11) NOT NULL AUTO_INCREMENT,
  `nome_status` varchar(50) NOT NULL,
  PRIMARY KEY (`id_status`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela cbk_database.status_os: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `status_os` DISABLE KEYS */;
/*!40000 ALTER TABLE `status_os` ENABLE KEYS */;

-- Copiando estrutura para tabela cbk_database.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `nome_usuario` varchar(30) NOT NULL,
  `senha` varchar(30) NOT NULL,
  `id_departamento` int(11) NOT NULL,
  PRIMARY KEY (`id_usuario`),
  KEY `Fk_departamento` (`id_departamento`),
  CONSTRAINT `Fk_departamento` FOREIGN KEY (`id_departamento`) REFERENCES `departamento` (`id_departamento`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela cbk_database.usuario: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`id_usuario`, `nome_usuario`, `senha`, `id_departamento`) VALUES
	(1, 'admin', '123', 1),
	(2, 'teste', '123', 2);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;

-- Copiando estrutura para tabela cbk_database.versao
CREATE TABLE IF NOT EXISTS `versao` (
  `id_versao` int(11) NOT NULL AUTO_INCREMENT,
  `versao_num` varchar(50) NOT NULL,
  `data_versao` date NOT NULL,
  PRIMARY KEY (`id_versao`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela cbk_database.versao: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `versao` DISABLE KEYS */;
INSERT INTO `versao` (`id_versao`, `versao_num`, `data_versao`) VALUES
	(1, '0.0.0.1', '2018-04-12');
/*!40000 ALTER TABLE `versao` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
