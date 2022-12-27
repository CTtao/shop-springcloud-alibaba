-- auto-generated definition
create table t_order_item
(
    id          bigint         not null
        primary key,
    t_order_id  bigint         null,
    t_pro_id    bigint         null,
    t_pro_name  varchar(50)    null,
    t_pro_price decimal(10, 2) null,
    t_number    int            null
);