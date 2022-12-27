-- auto-generated definition
create table t_order
(
    id            bigint         not null
        primary key,
    t_user_id     bigint         null,
    t_user_name   varchar(50)    null,
    t_phone       varchar(20)    null,
    t_address     varchar(255)   null,
    t_total_price decimal(10, 2) null
);