-- auto-generated definition
create table t_product
(
    id          bigint         not null
        primary key,
    t_pro_name  varchar(50)    null,
    t_pro_price decimal(10, 2) null,
    t_pro_stock int            null
);