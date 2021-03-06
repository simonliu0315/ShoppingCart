update PRODUCT_COLOR set seq = 1 where product_id = 17 and color = '#FFFFFF;';


drop table IF EXISTS AUDIT_LOG;

create table AUDIT_LOG(
    SESSION_ID varchar (256),
    FUNCTION_ID varchar (256),
    IP varchar (36),
    URI varchar (256),
    inserted timestamp,
    insert_by varchar(30),
    http_Status int,
    http_method varchar(50),
    java_Method varchar(512),
    response varchar(4069),
    primary key(SESSION_ID,FUNCTION_ID,inserted)
);