-- auto-generated definition
create table t_user
(
    id         bigint       not null
        primary key,
    t_username varchar(50)  null,
    t_password varchar(64)  null,
    t_phone    varchar(20)  null,
    t_address  varchar(255) null
);