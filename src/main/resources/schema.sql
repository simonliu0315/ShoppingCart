create table Category(
    id int not null,
    name varchar(60),
    type int,
    img varchar(200),
    inserted timestamp,
    insert_by varchar(30),
    updated timestamp,
    update_by varchar(30),
    primary key(id)
);
create table Product(
    id int not null,
    name varchar2(60),
    original_price int default 0,
    discount int default 0,
    price int default 0,
    short_description varchar(200),
    description varchar(200),
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
	username varchar (30) not null,
	status_id int not null,
	inserted timestamp,
    insert_by varchar(30),
    updated timestamp,
    update_by varchar(30),
    shipping int default 0,
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
    description varchar(200),
    primary key(id)
);
create table USER(
    username  varchar (30) not null,
	c_name varchar(30),
	email  varchar(30),
	password varchar(60),
	address varchar(200),
	birthday date,
	inserted timestamp,
    insert_by varchar(30),
    updated timestamp,
    update_by varchar(30),
	primary key(username)
);

create table USER_CONTRACT(
    id int not null,
    username varchar(30),
    post_name  varchar(30),
    email  varchar(30),
    zip_code  varchar(30),
    city  varchar(30),
    district  varchar(30),
    address  varchar(30),
    tel  varchar(30),
    primary key(id)
);
create table USER_ROLE(
    username  varchar (30) not null,
	role varchar(30),
	status int,
	primary key(username, role)
);



