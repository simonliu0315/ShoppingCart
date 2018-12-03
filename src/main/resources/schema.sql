drop table IF EXISTS Category;
drop table IF EXISTS Product;
drop table IF EXISTS PRODUCT_CAPACITY;
drop table IF EXISTS Product_Color;
drop table IF EXISTS CUSTOMER_ORDER;
drop table IF EXISTS ORDER_DETAIL;
drop table IF EXISTS ORDER_STATUS;
drop table IF EXISTS PAYMENT_METHOD;
drop table IF EXISTS APP_USER;
drop table IF EXISTS USER_CONTRACT;
drop table IF EXISTS USER_ROLE;
drop table IF EXISTS APPLICATION_LIST;
drop table IF EXISTS APPLICATION_LIST_IMAGE;
drop table IF EXISTS Product_TAG;
drop table IF EXISTS Product_TAG_RELATION;
drop table IF EXISTS SHIPPING_METHOD;
drop table IF EXISTS ORDER_CONTRACT;
create table Category(
    id int not null,
    name varchar(180),
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
    name varchar(180),
    eng_name varchar(120),
    model varchar(20),
    original_price int default 0,
    discount int default 0,
    price int default 0,
    short_description varchar(600),
    description varchar(4096),
    img varchar(200),
    category_id int,
    activate int,
    published int default 0,
    newest int default 0,
    promotion_start timestamp,
    promotion_end timestamp,
    promotion_discount int default 0,
    promotion_price int default 0,
    promotion_on int default 0,
    inserted timestamp,
    insert_by varchar(30),
    updated timestamp,
    update_by varchar(30),
    stock_quantity int default 0,
    primary key(id)
    --img_src BOLB()
);
create table CUSTOMER_ORDER(
    id int not null,
    order_no varchar(30) not null,
	amount int not null,
	username varchar (30) not null,
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
	product_name varchar (400),
	quantity int default 0,
	color varchar(20),
    primary key(order_no, product_id, color)
);
create table ORDER_STATUS(
    id int not null,
    status int not null,
    description varchar(400),
    primary key(id)
);
create table ORDER_CONTRACT(
    order_no varchar (20),
    post_name  varchar(40),
    email  varchar(30),
    zip_code  varchar(30),
    county  varchar(60),
    district  varchar(60),
    address  varchar(60),
    tel  varchar(30),
    primary key(order_no)
);
create table PAYMENT_METHOD(
    id int not null,
    method int not null,
    description varchar(400),
    eng_description varchar(400),
    content varchar(400),
    status int not null,
    primary key(id)
);
create table APP_USER(
    username  varchar (60) not null,
	c_name varchar(60),
	email  varchar(30),
	password varchar(256),
	zip_code varchar(50),
	county varchar(50),
	district varchar(50),
	address varchar(400),
	birthday date,
	inserted timestamp,
    insert_by varchar(30),
    updated timestamp,
    update_by varchar(30),
    verify_code varchar(256),
    verify_date timestamp,
    status int default 0,
    mobile varchar(30),
	primary key(username)
);

create table USER_CONTRACT(
    id int not null,
    username varchar(60),
    post_name  varchar(40),
    email  varchar(30),
    zip_code  varchar(30),
    county  varchar(60),
    district  varchar(60),
    address  varchar(60),
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
    name varchar(200),
    content varchar(4096),
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
    name varchar(200),
    img varchar(200),
    inserted timestamp,
    insert_by varchar(30),
    updated timestamp,
    update_by varchar(30),
    primary key(id, name)
);

create table Product_Color(
    product_id int not null,
    color varchar(20),
    name varchar(120),
    e_name varchar(120),
    linner_color varchar(20),
    status int default 1,
    inserted timestamp,
    insert_by varchar(30),
    updated timestamp,
    update_by varchar(30),
    primary key(product_id, color)
);
create table PRODUCT_CAPACITY(
    product_id int not null,
    capacity NUMERIC(5,2),
    unit varchar(20),
    name varchar(120),
    e_name varchar(120),
    status int default 1,
    inserted timestamp,
    insert_by varchar(30),
    updated timestamp,
    update_by varchar(30),
    primary key(product_id, capacity, unit)
);
create table Product_TAG(
    id int not null,
    tag_name varchar(20),
    status int default 1,
    primary key(id)
);
create table Product_TAG_RELATION(
    product_id int not null,
    tag_id int not null,
    inserted timestamp,
    insert_by varchar(30),
    updated timestamp,
    update_by varchar(30),
    primary key(product_id, tag_id)
);
create table SHIPPING_METHOD(
    id int not null,
    method int not null,
    description varchar(400),
    eng_description varchar(400),
    content varchar(400),
    status int not null,
    shipping int,
    primary key(id)
);