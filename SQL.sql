-- Create table TrackCar;
CREATE SCHEMA `trackcar`;

Create table tblConsumo(
    idConsumo int primary key not null auto_increment, -- Id da tabela consumo
    Consumo varchar(100) not null -- Tipo de Consumo do Carro, eletrico, gasoleo, gasolina, GPL, hibrido
);

create table tblCentroDeInspecao(
    idCentro int primary key not null auto_increment, -- Id do Centro de Inspecções
    NomeCentro varchar(150) not null, -- Nome do centro de Inspecções
    Morada varchar(200) not null, -- Morada do centro de Inspecções
    Telefone varchar(15) not null, -- Número de Telefone do centro de inspecções
    Email varchar(100) not null, -- Email do Centro de Inspecções
    ChefeDoCentro varchar(100) not null -- Nome do Chefe do Centro de Inspeções
);

Create table tblEstado(
    idEstado int primary key not null auto_increment, -- IdEstado do Estado de Inspeção
    Estado varchar(50) not null, -- Titulo do Estado da inspeção
    MensagemdoEstado varchar(500) not null -- Mensagem mais detalhada do Estado da Inspecção
);

Create table tblCar(
	idCar int primary key not null auto_increment, -- id do Carro
	MatriculaCarro varchar(8) not null, -- Numeros e Letras da Matricula
	Modelo varchar(50) not null, -- Marca + Modelo da Matrica
	MesAno varchar(5) not null, -- AA/MM da matricula
	CV int not null, -- celindrada
	Consumo int , -- ligado a tblConsumo
	KmFeitos Decimal(25, 2) not null, -- Numero de Kilometros feitos pelo carro
	LastLocalization text, -- ultima localização do carro
    
    foreign key (Consumo) references tblConsumo(idConsumo) -- chave estrangeira ligada a tblConsumo
);


Create table tblInspecoes(
    idInspecao int primary key not null auto_increment, -- id da Inspecao
    DataHora datetime not null, -- data e hora da inspeção
    CentroDeInspecao int, -- id da inspecção ligado ao tblCentroDeinspecao
    -- idCarro int,
    idEstado int, -- id do estado da inspeção ligada a tblEstado
    Reprovado bit not null, -- recebe true or false se o carro está chumbado ou não
    
    foreign key (CentroDeInspecao) references tblCentroDeInspecao(idCentro), 
    -- foreign key (idCarro) references tblCar(idCar),
    foreign key (idEstado) references tblEstado(idEstado)
);

Create table tblInsCar(
    idInsCar int primary key not null auto_increment, 
    idCar int not null, 
    idInspecao int not null, 

    foreign key(idCar) references tblCar(idCar),
    foreign key(idInspecao) references tblInspecoes(idInspecao)
);

Create table tblLogin(
	idLogin int primary key not null auto_increment, -- id do Login
	Username varchar(255) not null, -- username do utilizador
	Password varchar(255) not null, -- palavra passe do utilizador
	Image varchar(255) -- imagem do utilizador
);

Create table tblMetodoDePagament(
    idMetodo int primary key not null auto_increment, -- id Do metodo de Pagamento
    Metodo varchar(30) not null -- Metodo de pagamento
);

Create table tblMulta(
    idMulta int primary key not null auto_increment, -- id da Multa
    ValorMulta decimal(9, 2) not null, -- Valor da multa
    Descricao varchar(300) not null, -- Descricão da Multa
    -- idMetodo int, -- id do Metodo
);


Create table tblClient(
	idClient int primary key not null auto_increment, -- chave primaria do idCliente
	FirstName varchar(50) not null, -- Primeiro Nome do Utilizador
	LastName varchar(100) not null, -- Ultimo nome do Utilizador
	idLogin int, -- id ligado ao IdLogin 
	DataNascimento date not null, -- data de nascimento do utilizador
	Email varchar(150) not null, -- email do utilizador
	FiltroNoticias text, -- filtro das noticias
    
    foreign Key (idLogin) references tblLogin(idLogin)
);


Create table tblMulta(
    idMulta int primary key not null auto_increment, -- id da Multa
    ValorMulta decimal(9, 2) not null, -- Valor da multa
    Descricao varchar(300) not null -- Descricão da Multa
    -- idMetodo int, -- id do Metodo
);


Create table tblClient(
	idClient int primary key not null auto_increment, -- chave primaria do idCliente
	FirstName varchar(50) not null, -- Primeiro Nome do Utilizador
	LastName varchar(100) not null, -- Ultimo nome do Utilizador
	idLogin int, -- id ligado ao IdLogin 
	DataNascimento date not null, -- data de nascimento do utilizador
	Email varchar(150) not null, -- email do utilizador
	FiltroNoticias text, -- filtro das noticias
    
    foreign Key (idLogin) references tblLogin(idLogin)
);

Create table tbldetalhesCarro(
    idDetalhesCarro int primary key not null auto_increment, -- id Detalhes Carro
    idCar int, -- id do Carro ligado a tblCar
    idClient int, -- id do Cliente ligado a tblClient
    idCarroMulta int, -- id da Multa ligado a tblMulta
    
    foreign key (idCar) references tblCar(idCar),
    foreign key (idClient) references tblClient(idClient)
    -- foreign key (idCarroMulta) references tblCarrosMultas(idCarrosMultas)
    -- foreign key (idMulta) references tblCarroMultas(idCarrosMultas)
);

Create table tblCarrosMultas(
    idCarrosMultas int primary key not null auto_increment, -- id do Carro Multas
    idDetalhesCarro int not null, -- id do detalhes carro ligado a tblDetalhesCarro
    idMulta int not null, -- id da Multa ligado a tblMultas
    idMetodo int not null, -- id da Multa ligado a tblMultas
 
    Pago bit not null, -- Certificar se ficou pago ou não
    DataHoraPagamento datetime, -- data e hora que a multa foi paga
    DataHoraMultado datetime not null, -- data e hora que o utilizador foi multado


    -- foreign key (idDetalhesCarro) references tbldetalhesCarro(idDetalhesCarro),
    foreign key (idDetalhesCarro) references tbldetalhesCarro(idDetalhesCarro),
    foreign key (idMulta) references tblMulta(idMulta),
    foreign key (idMetodo) references tblMetodoDePagament(idMetodo) 
    -- foreign key (idMetodo) references tblMulta(idMulta) 
);


-- Dados para tblConsumo
INSERT INTO `trackcar`.`tblConsumo` (`Consumo`) VALUES 
('Elétrico'),
('Gasóleo'),
('Gasolina'),
('GPL'),
('Híbrido');

-- Dados para tblCentroDeInspecao
INSERT INTO `trackcar`.`tblCentroDeInspecao` (`NomeCentro`, `Morada`, `Telefone`, `Email`, `ChefeDoCentro`) VALUES 
('Centro de Inspeção A', 'Rua A, nº 123', '913 456 789', 'centroA@example.com', 'Chefe A'),
('Centro de Inspeção B', 'Rua B, nº 456', '987 654 321', 'centroB@example.com', 'Chefe B'),
('Centro de Inspeção C', 'Rua C, nº 789', '911 222 333', 'centroC@example.com', 'Chefe C'),
('Centro de Inspeção D', 'Rua D, nº 987', '944 555 666', 'centroD@example.com', 'Chefe D'),
('Centro de Inspeção E', 'Rua E, nº 654', '977 888 999', 'centroE@example.com', 'Chefe E');

-- Dados para tblEstado
INSERT INTO `trackcar`.`tblEstado` (`Estado`, `MensagemdoEstado`) VALUES 
('Aprovado', 'O veículo passou na inspeção com sucesso.'),
('Reprovado', 'O veículo não atende aos requisitos e foi reprovado na inspeção.'),
('Em andamento', 'A inspeção está em andamento. Aguarde o resultado.');

-- Dados para tblCar
INSERT INTO `trackcar`.`tblCar` (`MatriculaCarro`, `Modelo`, `MesAno`, `CV`, `Consumo`, `KmFeitos`, `LastLocalization`) VALUES 
('AB-12-CD', 'Volkswagen Golf', '06/22', 1500, 1, 50000, 'Rua X, nº 789'),
('CD-45-EF', 'Ford Focus', '01/21', 1200, 2, 60000, 'Rua Y, nº 456'),
('EF-78-GH', 'Toyota Corolla', '04/23', 1800, 3, 70000, 'Rua Z, nº 123'),
('GH-98-IJ', 'Renault Clio', '08/20', 1000, 4, 80000, 'Rua W, nº 987'),
('IJ-65-KL', 'BMW 320i', '20/19', 2000, 5, 90000, 'Rua V, nº 654');

-- Dados para tblInspecoes
INSERT INTO `trackcar`.`tblInspecoes` (`DataHora`, `CentroDeInspecao`, `idEstado`, `Reprovado`) VALUES 
('2023-01-01 10:00:00', 1, 1, 0),
('2023-02-02 11:30:00', 2, 2, 1),
('2023-03-03 13:45:00', 3, 3, 0),
('2023-04-04 15:15:00', 4, 1, 0),
('2023-05-05 17:30:00', 5, 2, 1);

-- Dados para tblInsCar
INSERT INTO `trackcar`.`tblInsCar` (`idCar`, `idInspecao`) VALUES 
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5);

-- Dados para tblLogin
INSERT INTO `trackcar`.`tblLogin` (`Username`, `Password`, `Image`) VALUES 
('user1', 'pass1', 'image1.jpg'),
('user2', 'pass2', 'image2.jpg'),
('user3', 'pass3', 'image3.jpg'),
('user4', 'pass4', 'image4.jpg'),
('user5', 'pass5', 'image5.jpg');

-- Dados para tblMetodoDePagament
INSERT INTO `trackcar`.`tblMetodoDePagament` (`Metodo`) VALUES 
('Cartão de Crédito'),
('Cartão de Debito'),
('Cheques'),
('Dinheiro'),
('Transferência Bancária');




-- Dados para tblMulta
INSERT INTO `trackcar`.`tblMulta` (`ValorMulta`, `Descricao`) VALUES 
(50.00, 'Estacionamento em local proibido'),
(100.00, 'Excesso de velocidade'),
(30.00, 'Sem cinto de segurança'),
(80.00, 'Condução sob efeito de álcool'),
(120.00, 'Ultrapassagem indevida');

-- Dados para tblClient
INSERT INTO `trackcar`.`tblClient` (`FirstName`, `LastName`, `idLogin`, `DataNascimento`, `Email`, `FiltroNoticias`) VALUES 
('João', 'Silva', 1, '1990-05-15', 'joao@example.com', 'Notícias importantes'),
('Maria', 'Santos', 2, '1985-08-20', 'maria@example.com', 'Promoções e eventos'),
('Carlos', 'Ferreira', 3, '1995-02-10', 'carlos@example.com', 'Todas as notícias'),
('Ana', 'Oliveira', 4, '1980-11-25', 'ana@example.com', 'Sem filtro'),
('Pedro', 'Pereira', 5, '1992-07-05', 'pedro@example.com', 'Notícias de tecnologia');

-- Dados para tbldetalhesCarro
INSERT INTO `trackcar`.`tbldetalhesCarro` (`idCar`, `idClient`) VALUES 
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5);

-- Dados para tblCarrosMultas
INSERT INTO `trackcar`.`tblCarrosMultas` (`idDetalhesCarro`, `idMulta`, `idMetodo`, `Pago`, `DataHoraPagamento`, `DataHoraMultado`) VALUES 
(1, 1, 1, 1, '2023-01-02 12:00:00', '2023-01-01 09:30:00'),
(2, 2, 2, 0, NULL, '2023-02-02 11:45:00'),
(3, 3, 3, 1, '2023-03-04 14:00:00', '2023-03-03 13:50:00'),
(4, 4, 4, 0, NULL, '2023-04-05 15:30:00'),
(5, 5, 1, 1, '2023-05-06 18:00:00', '2023-05-05 17:45:00');


-- Esboços ou exemplos de codigo sql
SELECT AVG(KmFeitos) AS MediaKmFeitos FROM tblCar;
SELECT COUNT(DISTINCT idClient) AS ClientesComMultas FROM tbldetalhesCarro;
SELECT COUNT(idMulta) AS 'Total Multas', SUM(ValorMulta) AS 'Valor Total Arrecadado', (Sum(ValorMulta)-(Sum(ValorMulta)*0.3)) as 'Valor Limpo' FROM tblmulta;
SELECT c.FirstName, c.LastName, m.ValorMulta, m.Descricao FROM tblClient c JOIN tbldetalhesCarro dc ON c.idClient = dc.idClient JOIN tblCarrosMultas cm ON dc.idDetalhesCarro = cm.idDetalhesCarro JOIN tblMulta m ON cm.idMulta = m.idMulta ORDER BY m.ValorMulta DESC LIMIT 1;
SELECT AVG(YEAR(CURDATE()) - YEAR(DataNascimento)) AS MediaIdadeClientes FROM tblClient;
SELECT Consumo, COUNT(*) AS QuantidadeCarros FROM tblCar, tblconsumo inner join Consumo on idConsumo GROUP BY Consumo;
select MatriculaCarro, Modelo, MesAno, tblconsumo.Consumo from tblcar inner join tblconsumo on tblconsumo.idConsumo = tblcar.Consumo
select tblconsumo.consumo, Count(*) from tblcar inner join tblconsumo on tblcar.Consumo = tblconsumo.idConsumo group by tblcar.consumo
select Count(*), NomeCentro from tblinspecoes inner join tblcentrodeinspecao on tblcentrodeinspecao.idCentro = tblinspecoes.CentroDeInspecao group by CentroDeInspecao 
select Count(*) as 'Numero de Inspecções', NomeCentro from tblinspecoes inner join tblcentrodeinspecao on tblcentrodeinspecao.idCentro = tblinspecoes.CentroDeInspecao group by CentroDeInspecao order by 'Numero de Inspecções' desc limit 1


