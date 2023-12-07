Create table TrackCar;



Create table tblConsumo(
    idConsumo int primary key not null auto_increment, 
    Consumo varchar(100) not null
);

create table tblCentroDeInspecao(
    idCentro int primary key not null auto_increment,
    NomeCentro varchar(150) not null,
    Morada varchar(200) not null,
    Telefone varchar(15) not null,
    Email varchar(100) not null,
    ChefeDoCentro varchar(100) not null
);

Create table tblEstado(
    idEstado int primary key not null auto_increment,
    Estado varchar(50) not null,
    MensagemdoEstado varchar(500) not null
);



Create table tblCar(
	idCar int primary key not null auto_increment,
	MatriculaCarro varchar(8) not null,
	Modelo varchar(50) not null,
	MesAno varchar(5) not null, -- AA/MM
	CV int not null, -- celindrada
	Consumo int , -- eletrico, gasoleo, gasolina, GPL, hibrido
	KmFeitos Decimal(25, 2) not null,
	LastLocalization text,
    
    foreign key (Consumo) references tblConsumo(idConsumo)
);

Create table tblInspecoes(
    idInspecao int primary key not null auto_increment,
    DataHora datetime not null,
    CentroDeInspecao int,
    idCarro int,
    idEstado int,
    Reprovado bit not null,
    
    foreign key (CentroDeInspecao) references tblCentroDeInspecao(idCentro),
    foreign key (idCarro) references tblCar(idCar),
    foreign key (idEstado) references tblEstado(idEstado)
);

Create table tblLogin(
	idLogin int primary key not null auto_increment,
	Username varchar(255) not null,
	Password varchar(255) not null,
	Image varchar(255)
);

Create table tblMetodoDePagament(
    idMetodo int primary key not null auto_increment,
    Metodo varchar(30) not null
);

Create table tblMulta(
    idMulta int primary key not null auto_increment,
    ValorMulta decimal(9, 2) not null,
    Descricao varchar(300) not null,
    idMetodo int,
    Pago bit not null,
    DataHoraPagamento datetime,
    DataHoraMultado datetime not null,
    foreign key (idMetodo) references tblMetodoDePagament(idMetodo)
);


Create table tblClient(
	idClient int primary key not null auto_increment,
	FirstName varchar(50) not null,
	LastName varchar(100) not null,
	idLogin int,
	DataNascimento date not null,
	Email varchar(150) not null,
	FiltroNoticias text,
    
    foreign Key (idLogin) references tblLogin(idLogin)
);

Create table tbldetalhesCarro(
    idDetalhesCarro int primary key not null auto_increment,
    idCar int,
    idClient int,
    idMulta int,
    
    foreign key (idCar) references tblCar(idCar),
    foreign key (idClient) references tblClient(idClient),
    foreign key (idMulta) references tblMulta(idMulta)
);