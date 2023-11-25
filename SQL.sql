Create database TrackCar;

Create table tblConsumo(
    idConsumo int identity(1,1) primary key not null, 
    Consumo varchar(100) not null
)

create table tblCentroDeInspecao(
    idCentro int identity(1,1) primary key not null,
    NomeCentro varchar(150) not null,
    Morada varchar(200) not null,
    Telefone varchar(15) not null,
    Email varchar(100) not null,
    ChefeDoCentro varchar(100) not null,
);

Create table tblEstado(
    idEstado int identity(1,1) primary key not null,
    Estado varchar(50) not null,
    MensagemdoEstado varchar(500) not null
)

Create table tblInspecoes(
    idInspecao int identity(1,1) primary key not null,
    DataHora datetime not null,
    CentroDeInspecao int foreign key references tblCentroDeInspecao(idCentro),
    idCarro int foreign key references tblCar(idCar),
    idEstado int foreign key references tblEstado(idEstado),
    Reprovado bit not null,
);

Create table tblCar(
	idCar int identity(1,1) primary key not null,
	MatriculaCarro varchar(8) not null,
	Modelo varchar(50) not null,
	MesAno varchar(5) not null, -- AA/MM
	CV int not null, --celindrada
	Consumo int Foreign key references tblConsumo(idConsumo), -- eletrico, gasoleo, gasolina, GPL, hibrido
	KmFeitos Decimal(25, 2) not null,
	LastLocalization varchar(max),
);

Create table tblLogin(
	idLogin int identity(1,1) primary key not null,
	Username varchar(max) not null,
	Password varchar(max) not null,
	Icon Image,
);

Create table tblMetodoDePagament(
    idMetodo int identity(1,1) primary key not null,
    Metodo varchar(30) not null,
)
Create table tblMulta(
    idMulta int identity(1,1) primary key not null,
    ValorMulta decimal(9, 2) not null,
    Descricao varchar(300) not null,
    idMetodo int foreign key references tblMetodoDePagament(idMetodo)
    Pago bit not null,
    DataHoraPagamento datetime,
    DataHoraMultado datetime not null,
);


Create table tblClient(
	idClient int identity(1,1) primary key not null,
	FirstName varchar(50) not null,
	LastName varchar(100) not null,
	idLogin int Foreign Key references tblLogin(idLogin),
	DataNascimento date not null,
	Email varchar(150) not null,
	FiltroNoticias varchar(max),
);

Create table tbldetalhesCarro(
    idDetalhesCarro int identity(1,1) primary key not null,
    idCar int foreign key references tblCar(idCar),
    idClient int foreign key references tblClient(idClient),
    idMulta int foreign key references tblMulta(idMulta),
);
