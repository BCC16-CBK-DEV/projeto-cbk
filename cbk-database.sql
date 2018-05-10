-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           5.7.21 - MySQL Community Server (GPL)
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Copiando estrutura para tabela cbk_database.cliente
CREATE TABLE IF NOT EXISTS `cliente` (
  `id_cliente` int(11) NOT NULL AUTO_INCREMENT,
  `nome_cliente` varchar(70) NOT NULL,
  `cpf` char(14) NOT NULL,
  `rg` char(12) DEFAULT NULL,
  `cep` varchar(9) NOT NULL,
  `endereco` varchar(50) NOT NULL,
  `bairro` varchar(50) NOT NULL,
  `numero` varchar(5) DEFAULT NULL,
  `complemento` varchar(30) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `telefone` varchar(14) DEFAULT NULL,
  `celular` varchar(15) NOT NULL,
  `cidade` varchar(50) DEFAULT NULL,
  `uf` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_cliente`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela cbk_database.cliente: 2 rows
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` (`id_cliente`, `nome_cliente`, `cpf`, `rg`, `cep`, `endereco`, `bairro`, `numero`, `complemento`, `email`, `telefone`, `celular`, `cidade`, `uf`) VALUES
	(1, 'Igor Casconi de Oliveira', '462.829.518-28', '45.656.522-x', '17525-270', 'Ricieri Piai', 'Jd. Acapulco', '223', 'Casa', 'igor492@gmail.com', '(14)9985436', '(14)99854366', 'Marília', 'SP'),
	(2, 'Ubiraja Julião', '555.555.555-55', '45.656.522-x', '17525-270', 'Ricieri Piai', 'Jd. Acapulco', '223', 'Casa', 'igor492@gmail.com', '(14)9985436', '(14)99854366', 'Marília', 'SP');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;

-- Copiando estrutura para tabela cbk_database.departamento
CREATE TABLE IF NOT EXISTS `departamento` (
  `id_departamento` int(11) NOT NULL AUTO_INCREMENT,
  `nome_departamento` varchar(50) NOT NULL,
  PRIMARY KEY (`id_departamento`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela cbk_database.departamento: 3 rows
/*!40000 ALTER TABLE `departamento` DISABLE KEYS */;
INSERT INTO `departamento` (`id_departamento`, `nome_departamento`) VALUES
	(1, 'Administrador'),
	(2, 'Técnico'),
	(3, 'Atendente');
/*!40000 ALTER TABLE `departamento` ENABLE KEYS */;

-- Copiando estrutura para tabela cbk_database.informacao_autorizada
CREATE TABLE IF NOT EXISTS `informacao_autorizada` (
  `id_autorizada` int(11) NOT NULL AUTO_INCREMENT,
  `nome_autorizada` varchar(50) NOT NULL,
  PRIMARY KEY (`id_autorizada`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela cbk_database.informacao_autorizada: 1 rows
/*!40000 ALTER TABLE `informacao_autorizada` DISABLE KEYS */;
INSERT INTO `informacao_autorizada` (`id_autorizada`, `nome_autorizada`) VALUES
	(1, 'MR ASSISTÊNCIA TÉCNICA');
/*!40000 ALTER TABLE `informacao_autorizada` ENABLE KEYS */;

-- Copiando estrutura para tabela cbk_database.ordem_servico
CREATE TABLE IF NOT EXISTS `ordem_servico` (
  `id_ordem` int(11) NOT NULL AUTO_INCREMENT,
  `numero_ordem` int(11) NOT NULL,
  `nota_fiscal` varchar(50) NOT NULL,
  `data_compra` varchar(10) NOT NULL,
  `defeito_reclamado` text,
  `codigo_produto` varchar(30) NOT NULL,
  `descricao_produto` text,
  `voltagem` int(11) DEFAULT NULL,
  `numero_serie_produto` varchar(30) DEFAULT NULL,
  `data_abertura` varchar(10) NOT NULL,
  `id_status` int(11) NOT NULL DEFAULT '1',
  `id_cliente` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_ordem`),
  KEY `Fk_id_cliente` (`id_cliente`),
  KEY `Fk_id_status` (`id_status`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela cbk_database.ordem_servico: 9 rows
/*!40000 ALTER TABLE `ordem_servico` DISABLE KEYS */;
INSERT INTO `ordem_servico` (`id_ordem`, `numero_ordem`, `nota_fiscal`, `data_compra`, `defeito_reclamado`, `codigo_produto`, `descricao_produto`, `voltagem`, `numero_serie_produto`, `data_abertura`, `id_status`, `id_cliente`) VALUES
	(1, 1, '1111', '01/04/2018', 'Teste 1', '1111', 'Teste 2', 111, '111111', '01/05/2018', 1, 0),
	(2, 2, '2222', '01/04/2018', 'Tesrte', '132132', 'tertesr', 122, '123213213', '01/05/2018', 1, 1),
	(3, 3, '13232', '28/04/2018', 'Teste 1', '1111111', 'Teste', 123, '23123123213', '05/05/2018', 1, 1),
	(4, 0, '434343', '24/04/2018', 'dasdasdasd', '13234', 'sadadsa', 213, '131231231333', '05/10/2018', 1, 2),
	(5, 4, '321213213123', '25/05/2018', 'tetestesr', '2131232', 'Teste 12', 110, '13213213213123', '28/10/2018', 1, 2),
	(6, 0, '13213123', '20/04/2018', 'adasadasdasd', '13123', 'dasdasdsa', 110, 'sdadasdasdasd', '28/04/2018', 1, 2),
	(7, 0, 'dasdadas', '28/04/2018', 'adasdasd', '13213', 'dadasdad', 121, '1313231', '28/04/2018', 1, 1),
	(8, 0, '13123123', '19/04/2018', 'Defeito Reclamado', '131231', 'Teste 2', 111, 'dadasda', '20/04/2018', 1, 1),
	(9, 5, '1323123', '20/04/2018', 'Barru Gay', '321321', 'Knopp Gay', 110, '123123123', '20/04/2018', 1, 1);
/*!40000 ALTER TABLE `ordem_servico` ENABLE KEYS */;

-- Copiando estrutura para tabela cbk_database.pedido_peca
CREATE TABLE IF NOT EXISTS `pedido_peca` (
  `id_peca` int(11) NOT NULL AUTO_INCREMENT,
  `num_pedido` int(11) NOT NULL,
  `email_fabricante` varchar(50) DEFAULT NULL,
  `id_ordem` int(11) NOT NULL,
  PRIMARY KEY (`id_peca`),
  KEY `fk_id_ordem` (`id_ordem`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela cbk_database.pedido_peca: 3 rows
/*!40000 ALTER TABLE `pedido_peca` DISABLE KEYS */;
INSERT INTO `pedido_peca` (`id_peca`, `num_pedido`, `email_fabricante`, `id_ordem`) VALUES
	(1, 1, 'igor492@gmail.com', 1),
	(2, 2, 'teste@gmail.com', 1),
	(3, 3, 'interno@gmail.com', 9);
/*!40000 ALTER TABLE `pedido_peca` ENABLE KEYS */;

-- Copiando estrutura para tabela cbk_database.pedido_peca_item
CREATE TABLE IF NOT EXISTS `pedido_peca_item` (
  `id_peca_item` int(11) NOT NULL AUTO_INCREMENT,
  `id_peca` int(11) NOT NULL,
  `codigo_peca` varchar(20) NOT NULL,
  `descricao_peca` varchar(100) NOT NULL,
  `qtd_peca` int(11) NOT NULL,
  PRIMARY KEY (`id_peca_item`),
  KEY `fk_id_peca` (`id_peca`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela cbk_database.pedido_peca_item: 2 rows
/*!40000 ALTER TABLE `pedido_peca_item` DISABLE KEYS */;
INSERT INTO `pedido_peca_item` (`id_peca_item`, `id_peca`, `codigo_peca`, `descricao_peca`, `qtd_peca`) VALUES
	(1, 1, '1', '1', 1),
	(2, 3, '101', 'Parafuso alien', 10);
/*!40000 ALTER TABLE `pedido_peca_item` ENABLE KEYS */;

-- Copiando estrutura para tabela cbk_database.status_os
CREATE TABLE IF NOT EXISTS `status_os` (
  `id_status` int(11) NOT NULL AUTO_INCREMENT,
  `nome_status` varchar(50) NOT NULL,
  PRIMARY KEY (`id_status`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela cbk_database.status_os: 2 rows
/*!40000 ALTER TABLE `status_os` DISABLE KEYS */;
INSERT INTO `status_os` (`id_status`, `nome_status`) VALUES
	(1, 'ABERTO'),
	(2, 'FECHADA');
/*!40000 ALTER TABLE `status_os` ENABLE KEYS */;

-- Copiando estrutura para tabela cbk_database.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `nome_usuario` varchar(30) NOT NULL,
  `senha` varchar(30) NOT NULL,
  `id_departamento` int(11) NOT NULL,
  PRIMARY KEY (`id_usuario`),
  KEY `Fk_departamento` (`id_departamento`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela cbk_database.usuario: 2 rows
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`id_usuario`, `nome_usuario`, `senha`, `id_departamento`) VALUES
	(1, 'admin', '123', 1),
	(3, 'igor', '123', 1);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;

-- Copiando estrutura para tabela cbk_database.versao
CREATE TABLE IF NOT EXISTS `versao` (
  `id_versao` int(11) NOT NULL AUTO_INCREMENT,
  `versao_num` varchar(30) NOT NULL,
  `data_versao` varchar(10) NOT NULL,
  PRIMARY KEY (`id_versao`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela cbk_database.versao: 1 rows
/*!40000 ALTER TABLE `versao` DISABLE KEYS */;
INSERT INTO `versao` (`id_versao`, `versao_num`, `data_versao`) VALUES
	(1, '0.0.0.1', '26/04/2018');
/*!40000 ALTER TABLE `versao` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
