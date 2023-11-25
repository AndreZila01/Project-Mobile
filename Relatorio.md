# Relatório do Projeto de Desenvolvimento Mobile

**Desenvolvido por André Custódio, 20220112**

**Desenvolvido por Diogo Nunes, 20220540**

# Indice de Relatorio




## Nome do Projeto
* TrackCar


## Enquadramento do Projeto
Oferecendo uma experiência automobilística completa e sem igual, a nossa aplicação foi projetada especialmente para aqueles que possuem uma vasta quantidade de automóveis. Com as ferramentas à disposição, tens a capacidade de ver e ter detalhes do universo dos teus veículos, proporcionando acesso instantâneo e detalhado a informações cruciais. Como os detalhes de cada carro, além de um registro abrangente das inspeções passadas e futuras, a vigência dos seguros de cada veículo, a conveniência de adicionar notas pessoais e relevantes a cada automóvel, o controle das multas em tempo real, a oportunidade de se manter sempre à frente das últimas tendências e notícias do mercado automobilístico, tudo isso em um único lugar, simplificando e aprimorando a gestão de sua frota de maneira eficiente e sofisticada.

Numa pesquisa muito rapida, descobrimos duas aplicações com pequenas ideias do que o nosso projeto irá ter. Como por exemplo a aplicação "Tem Seguro?", "Qual veículo é" e "Fleetio".

## Versão Preliminar

> No primeiro rectangulo dá para ver o user a fazer login, caso tenha esquecido do seu username ou password, tem de clicar no texto esqueci.

> Quando o utilizador faz login com sucesos, o utilizador há de ver uma pagina com noticias e seus icon. Caso queira ver as noticias, basta clicar em cima da noticia para visionar a mesma.

> Caso o utilizador clique no menu irá aparecer textos como detalhes dos carros, despesas, ultimas inspecções, entre outros.

![](https://github.com/AndreZila01/Project-Mobile/blob/main/Imagens/Esboco1.jpg)


### Modelo Entidade Relação
![](https://github.com/AndreZila01/Project-Mobile/blob/main/Imagens/S.png)

### Dicionario de Dados


tblConsumo:
```
idConsumo (int, primary key) -- Identificador único do consumo.
Consumo (varchar(100), not null) -- Tipo de consumo (eletrico, gasoleo, gasolina, GPL, hibrido).
```
tblCentroDeInspecao:
```
idCentro (int, primary key) -- Identificador único do centro de inspeção.
NomeCentro (varchar(150), not null) -- Nome do centro de inspeção.
Morada (varchar(200), not null) -- Endereço do centro de inspeção.
Telefone (varchar(15), not null) -- Número de telefone do centro de inspeção.
Email (varchar(100), not null) -- Endereço de e-mail do centro de inspeção.
ChefeDoCentro (varchar(100), not null) -- Nome do chefe do centro de inspeção.
```
tblEstado:
```
idEstado (int, primary key) -- Identificador único do estado.
Estado (varchar(50), not null) -- Descrição do estado.
MensagemdoEstado (varchar(500), not null) -- Mensagem associada ao estado.
```
tblInspecoes:
```
idInspecao (int, primary key) -- Identificador único da inspeção.
DataHora (datetime, not null) -- Data e hora da inspeção.
CentroDeInspecao (int, foreign key references tblCentroDeInspecao(idCentro)) -- Chave estrangeira para o centro de inspeção.
idCarro (int, foreign key references tblCar(idCar)) -- Chave estrangeira para o carro inspecionado.
idEstado (int, foreign key references tblEstado(idEstado)) -- Chave estrangeira para o estado da inspeção.
Reprovado (bit, not null) -- Indica se o veículo foi reprovado na inspeção.
```
tblCar:
```
idCar (int, primary key) -- Identificador único do carro.
MatriculaCarro (varchar(8), not null) -- Matrícula do carro.
Modelo (varchar(50), not null) -- Modelo do carro.
MesAno (varchar(5), not null) -- Mês e ano de fabricação do carro.
CV (int, not null) -- Cavalos de potência (cilindrada).
Consumo (int, foreign key references tblConsumo(idConsumo)) -- Tipo de consumo do carro.
KmFeitos (Decimal(25, 2), not null) -- Quilômetros percorridos pelo carro.
LastLocalization (varchar(max)) -- Última localização conhecida do carro.
```
tblLogin:
```
idLogin (int, primary key) -- Identificador único do login.
Username (varchar(max), not null) -- Nome de usuário.
Password (varchar(max), not null) -- Senha.
Icon (Image) -- Imagem associada ao login.
```
tblMetodoDePagament:
```
idMetodo (int, primary key) -- Identificador único do método de pagamento.
Metodo (varchar(30), not null) -- Descrição do método de pagamento.
```
tblMulta:
```
idMulta (int, primary key) -- Identificador único da multa.
ValorMulta (decimal(9, 2), not null) -- Valor da multa.
Descricao (varchar(300), not null) -- Descrição da multa.
idMetodo (int, foreign key references tblMetodoDePagament(idMetodo)) -- Chave estrangeira para o método de pagamento associado à multa.
Pago (bit, not null) -- Indica se a multa foi paga.
DataHoraPagamento (datetime) -- Data e hora do pagamento da multa, se aplicável.
DataHoraMultado (datetime, not null) -- Data e hora em que a multa foi aplicada.
```
tblClient:
```
idClient (int, primary key) -- Identificador único do cliente.
FirstName (varchar(50), not null) -- Primeiro nome do cliente.
LastName (varchar(100), not null) -- Sobrenome do cliente.
idLogin (int, foreign key references tblLogin(idLogin)) -- Chave estrangeira para o login associado ao cliente.
DataNascimento (date, not null) -- Data de nascimento do cliente.
Email (varchar(150), not null) -- Endereço de e-mail do cliente.
FiltroNoticias (varchar(max)) -- Filtro de notícias preferido pelo cliente.
```
tbldetalhesCarro:
```
idDetalhesCarro (int, primary key) -- Identificador único dos detalhes do carro.
idCar (int, foreign key references tblCar(idCar)) -- Chave estrangeira para o carro associado aos detalhes.
idClient (int, foreign key references tblClient(idClient)) -- Chave estrangeira para o cliente associado aos detalhes do carro.
idMulta (int, foreign key references tblMulta(idMulta)) -- Chave estrangeira para a multa associada aos detalhes do carro.
```

## Codigo SQL

```
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

```