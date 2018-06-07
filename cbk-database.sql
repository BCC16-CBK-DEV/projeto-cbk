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

-- Copiando estrutura para procedure cbk_database.atualiza_historico
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `atualiza_historico`(id_tecnico int, id_ordem int)
BEGIN
	INSERT INTO historico_ordem_servico(id_tecnico, id_ordem, data_historico) VALUES (id_tecnico, id_ordem, NOW());
END//
DELIMITER ;

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
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela cbk_database.cliente: 6 rows
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` (`id_cliente`, `nome_cliente`, `cpf`, `rg`, `cep`, `endereco`, `bairro`, `numero`, `complemento`, `email`, `telefone`, `celular`, `cidade`, `uf`) VALUES
	(1, 'Igor Casconi', '462.829.518-28', '45.656.522-x', '17500-270', 'Maciel Parente', 'Jd. Monte Castelo', '100', 'Casa', 'igor_casconi@hotmail.com', '(14)9985436', '(14)99854366', 'Marília', 'SP'),
	(2, 'Ubiraja Julião', '555.555.555-55', '45.656.522-x', '17525-270', 'Ricieri Piai', 'Jd. Acapulco', '223', 'Casa', 'ubira@gmail.com', '(14)9985436', '(14)77777777', 'Marília', 'SP'),
	(3, 'João Santos Filho', '111.111.111-11', '45.656.522-x', '17525-270', 'Ricieri Piai', 'Jd. Acapulco', '223', 'Casa', 'joao492@gmail.com', '(14)9985436', '(14)11111111', 'Marília', 'SP'),
	(5, 'Carlos Alberto de Nóbrega', '222.222.222-22', '45.656.522-x', '17525-270', 'Ricieri Piai', 'Jd. Acapulco', '223', 'Casa', 'carlos@gmail.com', '(14)9985436', '(14)22222222', 'Marília', 'SP'),
	(6, 'Cristiano Ronaldo', '999.999.999-99', '88.888.888-8', '77777-77', 'Martins Coelho', 'Jd Paris', '111', 'Casa', 'cristiano.ronaldo@hotmail.com', '(14)98777777', '(14)988888888', 'Marília', 'SP'),
	(7, 'Matheus Augusto Knopo', '464.728.898-64', '36.144.797-8', '17514-310', 'Diogo Melhado', 'Jardim Luciana', '492', 'Casa', 'matheusaugustoknopp@gmail.com', '(14)3306-8304', '(14)98102-9024', 'Marilia', 'SP');
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

-- Copiando estrutura para tabela cbk_database.historico_ordem_servico
CREATE TABLE IF NOT EXISTS `historico_ordem_servico` (
  `id_historico` int(11) NOT NULL AUTO_INCREMENT,
  `id_tecnico` int(11) NOT NULL,
  `id_ordem` int(11) NOT NULL,
  `data_historico` date NOT NULL,
  PRIMARY KEY (`id_historico`),
  KEY `Fk_id_tecnico` (`id_tecnico`),
  KEY `Fk_id_ordem` (`id_ordem`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela cbk_database.historico_ordem_servico: 4 rows
/*!40000 ALTER TABLE `historico_ordem_servico` DISABLE KEYS */;
INSERT INTO `historico_ordem_servico` (`id_historico`, `id_tecnico`, `id_ordem`, `data_historico`) VALUES
	(1, 1, 3, '2018-06-04'),
	(2, 1, 2, '2018-06-05'),
	(3, 3, 2, '2018-06-05'),
	(4, 3, 2, '2018-06-05');
/*!40000 ALTER TABLE `historico_ordem_servico` ENABLE KEYS */;

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
  `numero_ordem` varchar(10) NOT NULL,
  `nota_fiscal` varchar(50) NOT NULL,
  `data_compra` date NOT NULL,
  `defeito_reclamado` text,
  `codigo_produto` varchar(30) NOT NULL,
  `descricao_produto` text,
  `voltagem` int(11) DEFAULT NULL,
  `numero_serie_produto` varchar(30) DEFAULT NULL,
  `data_abertura` date NOT NULL,
  `observacao_tecnico` text,
  `tecnico` int(11) DEFAULT '0',
  `id_status` int(11) NOT NULL DEFAULT '1',
  `id_cliente` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_ordem`),
  KEY `Fk_id_cliente` (`id_cliente`),
  KEY `Fk_id_status` (`id_status`),
  KEY `Fk_id_usuario` (`tecnico`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela cbk_database.ordem_servico: 4 rows
/*!40000 ALTER TABLE `ordem_servico` DISABLE KEYS */;
INSERT INTO `ordem_servico` (`id_ordem`, `numero_ordem`, `nota_fiscal`, `data_compra`, `defeito_reclamado`, `codigo_produto`, `descricao_produto`, `voltagem`, `numero_serie_produto`, `data_abertura`, `observacao_tecnico`, `tecnico`, `id_status`, `id_cliente`) VALUES
	(1, '0001', '321321312', '2018-02-26', 'teste', '12312321', 'teste', 123, 'tste', '2018-05-22', 'teste', 1, 1, 1),
	(2, '0002', '1312321', '2018-02-26', 'teste 2', '1232', 'teste1', 122, '3423423434324', '2018-05-22', 'Teste de Observação, teste teste Novamente, teste 3', 3, 2, 6),
	(3, '0003', '3123123', '2018-01-29', 'DDFSDFDF', '23213', 'ASDASDA', 323, 'SADSDSADAS', '2018-05-22', 'Teste de Observação 23', 1, 1, 3),
	(4, '0004', '244324', '2018-04-29', 'teste', '123', 'steete', 233, '3424234344', '2018-05-22', NULL, 0, 1, 2);
/*!40000 ALTER TABLE `ordem_servico` ENABLE KEYS */;

-- Copiando estrutura para tabela cbk_database.pedido_peca
CREATE TABLE IF NOT EXISTS `pedido_peca` (
  `id_peca` int(11) NOT NULL AUTO_INCREMENT,
  `num_pedido` varchar(10) NOT NULL,
  `email_fabricante` varchar(50) DEFAULT NULL,
  `id_ordem` int(11) NOT NULL,
  PRIMARY KEY (`id_peca`),
  KEY `fk_id_ordem` (`id_ordem`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela cbk_database.pedido_peca: 1 rows
/*!40000 ALTER TABLE `pedido_peca` DISABLE KEYS */;
INSERT INTO `pedido_peca` (`id_peca`, `num_pedido`, `email_fabricante`, `id_ordem`) VALUES
	(1, '0001', 'igor492@gmail.com', 1);
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
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela cbk_database.pedido_peca_item: 10 rows
/*!40000 ALTER TABLE `pedido_peca_item` DISABLE KEYS */;
INSERT INTO `pedido_peca_item` (`id_peca_item`, `id_peca`, `codigo_peca`, `descricao_peca`, `qtd_peca`) VALUES
	(1, 1, '1', 'peca1', 10),
	(2, 1, '2', 'peca2', 20),
	(3, 1, '3', 'peca3', 30),
	(4, 1, '4', 'peca4', 40),
	(5, 1, '5', 'peca5', 50),
	(6, 1, '6', 'peca6', 60),
	(7, 1, '7', 'peca7', 70),
	(8, 1, '8', 'peca8', 80),
	(9, 1, '9', 'peca9', 90),
	(10, 1, '10', 'peca10', 100);
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
  `nome_completo` varchar(50) DEFAULT NULL,
  `id_departamento` int(11) NOT NULL,
  PRIMARY KEY (`id_usuario`),
  KEY `Fk_departamento` (`id_departamento`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela cbk_database.usuario: 3 rows
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`id_usuario`, `nome_usuario`, `senha`, `nome_completo`, `id_departamento`) VALUES
	(1, 'admin', '123', 'Administrador', 1),
	(3, 'igorcasconi', 'igor12', 'Igor Casconi de Oliveira', 1),
	(10, 'garbielviado', '123', 'Garbiel', 2);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;

-- Copiando estrutura para tabela cbk_database.versao
CREATE TABLE IF NOT EXISTS `versao` (
  `id_versao` int(11) NOT NULL AUTO_INCREMENT,
  `versao_num` varchar(30) NOT NULL,
  `data_versao` varchar(10) NOT NULL,
  PRIMARY KEY (`id_versao`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela cbk_database.versao: 2 rows
/*!40000 ALTER TABLE `versao` DISABLE KEYS */;
INSERT INTO `versao` (`id_versao`, `versao_num`, `data_versao`) VALUES
	(1, '0.0.0.1', '26/04/2018'),
	(2, '0.0.0.2', '29/05/2018');
/*!40000 ALTER TABLE `versao` ENABLE KEYS */;

-- Copiando estrutura para trigger cbk_database.altera_ordem
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER';
DELIMITER //
CREATE TRIGGER `altera_ordem` AFTER UPDATE ON `ordem_servico` FOR EACH ROW BEGIN
	CALL atualiza_historico(new.tecnico, old.id_ordem);
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
