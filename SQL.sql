Create database TrackCar;

Create table tblCar(
	idCar int identity(1,1) primary key not null,
	MatriculaCarro varchar(8) not null,
	Modelo varchar(50) not null,
	MesAno varchar(5) not null, -- AA/MM
	CV int not null, --celindrada
	Consumo varchar(50) not null, -- eletrico, gasoleo, gasolina, GPL, hibrido
	KmFeitos Decimal(25, 2) not null,
	LastLocalization varchar(max),
);

Create table tblLogin(
	idLogin int identity(1,1) primary key not null,
	Username varchar(max) not null,
	Password varchar(max) not null,
	Icon Image,
);

Create table tblClient(
	idClient int identity(1,1) primary key not null,
	FirstName varchar(50) not null,
	LastName varchar(100) not null,
	idLogin int Foreign Key references tblLogin(idLogin),
	DataNascimento date not null,
	Email varchar(150) not null,
	idCarros int Foreign key references tblCar(idCar),
	FiltroNoticias varchar(max),
);