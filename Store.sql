
create table [dbo].[position]
(
	ID_position int identity(1,1) not null,
	position_name varchar(50) not null,
	constraint [PK_ID_position] primary key clustered
	([ID_position] ASC) on [PRIMARY],	
)
go
create table [dbo].[store]
(
	ID_store int identity(1,1) not null,
	store_name varchar(max) not null,
	store_address varchar(max) not null
	constraint [PK_ID_park] primary key clustered
	([ID_store] ASC) on [PRIMARY],	
)
go
create table [dbo].[category]
(
	ID_category int identity(1,1) not null,
	category_name varchar(50) not null,
	constraint [PK_ID_category] primary key clustered
	([ID_category] ASC) on [PRIMARY],			
)
go
create table [dbo].[action]
(
	ID_action int identity(1,1) not null,
	action_proc float not null,
	acition_opisanie varchar(max) not null,
	action_time datetime not null,
	constraint [PK_ID_action] primary key clustered
	([ID_action] ASC) on [PRIMARY],	
)
go
create table [dbo].[client]
(
	ID_client int identity(1,1) not null,
	client_name varchar(50) not null,
	client_midlle_name varchar(50) not null,
	client_surname varchar(50) not null,
	balance float not null,
	phone_number varchar(50) not null,
	client_login varchar(50) not null,
	client_password varchar(50) not null,
	constraint [PK_ID_client] primary key clustered
	([ID_client] ASC) on [PRIMARY],	
)
go
create table [dbo].[subcategory]
(
	ID_subcategory int identity(1,1) not null,
	subcategory_name varchar(150) not null,
	ID_category int not null,
	constraint [PK_ID_subcategory] primary key clustered
	([ID_subcategory] ASC) on [PRIMARY],
	constraint [FK_ID_categoria] foreign key ([ID_category]) references [dbo].[category] ([ID_category])
)
go
create table [dbo].[employee]
(
	ID_employee int identity(1,1) not null,
	employee_login varchar(50) not null,
	employee_password varchar(50) not null,
	employee_name varchar(50) not null,
	employee_middle_name varchar(50) not null,
	employee_surname varchar(50) not null,
	position_ID int not null,
	constraint [CH_employee_login] check
	(len([employee_login])>=8),
	constraint [CH_password_employee_upper] check
	([employee_password] like ('%[A-Z]%')),
	constraint [CH_Password_Employee_Lower] check
	([employee_password] like ('%[a-z]%')),
	constraint [CH_Password_Employee_Symbwols] check
	([employee_password] like ('%[!#$><|?)(_-+=&*%^@{}"":;.,]%')),
	constraint [CH_Password_Employee_Numbers] check
	([employee_password] like ('%[0-9]%')),
	constraint [PK_ID_employee] primary key clustered
	([ID_employee] ASC) on [PRIMARY],	

	constraint [FK_ID_position] foreign key ([position_ID]) references [dbo].[position] ([ID_position])
)
go
create table [dbo].[product]
(
	ID_product int identity(1,1) not null,
	product_weight float not null,
	product_price float not null,
	product_name varchar(50) not null,
	product_opisanie varchar(max) not null,
	product_opisanieFULL varchar(max) not null,
	product_raiting float not null,
	store_ID int not null,
	action_ID int not null,
	subcategory_ID int not null,
	constraint [PK_ID_product] primary key clustered
	([ID_product] ASC) on [PRIMARY],	
	constraint [FK_ID_store] foreign key (store_ID) references [dbo].[store] (ID_store),
	constraint [FK_ID_action] foreign key (action_ID) references [dbo].[action] (ID_action),
	constraint [FK_ID_subcategory] foreign key (subcategory_ID) references [dbo].[subcategory] (ID_subcategory)
)
go
create table [dbo].[order]
(
	ID_order int identity(1,1) not null,
	order_address varchar(max) not null,
	order_date datetime not null,
	order_price float not null, 
	client_ID int not null,
	constraint [PK_ID_order] primary key clustered
	([ID_order] ASC) on [PRIMARY],	
	constraint [FK_ID_client] foreign key (client_ID) references [dbo].[client] (ID_client),
)
go
