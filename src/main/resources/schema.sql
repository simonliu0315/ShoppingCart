create table Category(
    id int not null,
    name nvarchar(60),
    type int,
    img varchar(200),
    url_view varchar(200),
    inserted timestamp,
    insert_by varchar(30),
    updated timestamp,
    update_by varchar(30),
    status int,
    primary key(id)
);
create table Product(
    id int not null,
    name nvarchar2(60),
    eng_name varchar2(120),
    original_price int default 0,
    discount int default 0,
    price int default 0,
    short_description nvarchar(200),
    description nvarchar(2048),
    img varchar(200),
    category_id int,
    activate int,
    published int default 0,
    newest int default 0,
    promotion_start datetime,
    promotion_end datetime,
    promotion_discount int default 0,
    promotion_price int default 0,
    promotion_on int default 0,
    inserted timestamp,
    insert_by varchar(30),
    updated timestamp,
    update_by varchar(30),
    primary key(id)
    --img_src BOLB()
);
create table CUSTOMER_ORDER(
    id int not null,
    order_no varchar(30) not null,
	amount int not null,
	username nvarchar (30) not null,
	status_id int not null,
	inserted timestamp,
    insert_by varchar(30),
    updated timestamp,
    update_by varchar(30),
    shipping int default 0,
    payment_method int,
    primary key(id)
);
create table ORDER_DETAIL(
    order_no varchar (20),
    product_id int,
	price int default 0,
	discount int default 0,
	product_name nvarchar (200),
	quantity int default 0,
    primary key(order_no, product_id)
);
create table ORDER_STATUS(
    id int not null,
    status int not null,
    description nvarchar(200),
    primary key(id)
);

create table PAYMENT_METHOD(
    id int not null,
    method int not null,
    description nvarchar(200),
    eng_description nvarchar(200),
    primary key(id)
);
create table USER(
    username  nvarchar (30) not null,
	c_name nvarchar(30),
	email  varchar(30),
	password varchar(256),
	address nvarchar(200),
	birthday date,
	inserted timestamp,
    insert_by varchar(30),
    updated timestamp,
    update_by varchar(30),
    verify_code varchar(256),
    verfiy_date timestamp,
    status int default 0,
	primary key(username)
);

create table USER_CONTRACT(
    id int not null,
    username nvarchar(30),
    post_name  nvarchar(30),
    email  varchar(30),
    zip_code  varchar(30),
    city  nvarchar(30),
    district  nvarchar(30),
    address  nvarchar(30),
    tel  varchar(30),
    primary key(id)
);
create table USER_ROLE(
    username  varchar (30) not null,
	role varchar(30),
	status int,
	primary key(username, role)
);

create table APPLICATION_LIST(
    id int not null,
    name nvarchar(100),
    content nvarchar(2048),
    status int,
    img varchar(200),
    inserted timestamp,
    insert_by varchar(30),
    updated timestamp,
    update_by varchar(30),
    primary key(id)
);

create table APPLICATION_LIST_IMAGE(
    id int not null,
    name nvarchar(100),
    img varchar(200),
    inserted timestamp,
    insert_by varchar(30),
    updated timestamp,
    update_by varchar(30),
    primary key(id, name)
);

create table Product_Color(
    id int not null,
    color varchar2(20),
    name varchar2(120),
    e_name varchar2(120),
    linner_color varchar2(20),
    status int default 1,
    inserted timestamp,
    insert_by varchar(30),
    updated timestamp,
    update_by varchar(30),
    primary key(id, color)
);
