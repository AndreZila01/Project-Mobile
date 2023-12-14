Create table TrackCar;

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
    idInsCar int primary key not null auto_increment, -- id da InsCar
    idCar int not null, -- id do Carro que está ligado ao tblCar
    idInspecao int not null, -- id da Inspecao que está ligado ao tblInspecções

    foreign key(idCar) references tblCar(idCar),
    foreign key(idInspecao) references tblInspecoes(idInspecao),
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
    idMetodo int, -- id do Metodo
    Pago bit not null, -- Certificar se ficou pago ou não
    DataHoraPagamento datetime, -- data e hora que a multa foi paga
    DataHoraMultado datetime not null, -- data e hora que o utilizador foi multado
    foreign key (idMetodo) references tblMetodoDePagament(idMetodo) 
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
    idMulta int, -- id da Multa ligado a tblMulta
    
    foreign key (idCar) references tblCar(idCar),
    foreign key (idClient) references tblClient(idClient),
    --foreign key (idMulta) references tblMulta(idMulta)
    foreign key (idMulta) references tbldetalhesCarro(idMulta)

);

Create table tblCarrosMultas(
    idCarrosMultas int primary key not null auto_increment, -- id do Carro Multas
    idDetalhesCarro int not null, -- id do detalhes carro ligado a tblDetalhesCarro
    idMulta int not null, -- id da Multa ligado a tblMultas

    foreign key (idDetalhesCarro) references tbldetalhesCarro(idDetalhesCarro),
    foreign key (idMulta) references tblMulta(idMulta)
);


