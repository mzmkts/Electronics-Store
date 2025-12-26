create table t_role
(
    id   BIGSERIAL primary key,
    name varchar not null
);
create table t_user
(
    id       BIGSERIAL primary key,
    username varchar(20) not null,
    password varchar     not null,
    email    varchar     not null unique,
    address  varchar
);
create table t_category
(
    id            BIGSERIAL primary key,
    category_name varchar not null
);
create table t_product
(
    id                  BIGSERIAL primary key,
    product_name        varchar,
    product_price       numeric,
    product_description varchar,
    category_id         bigint
);

create table t_orders
(
    id              BIGSERIAL primary key,
    order_date_time TIMESTAMP,
    status          varchar,
    total_amount    numeric,
    user_id         bigint
);

create table t_order_item
(
    id         BIGSERIAL primary key,
    quantity   int,
    order_id   bigint,
    product_id bigint
);
create table t_payment
(
    id             BIGSERIAL primary key,
    payment_method varchar,
    payment_date   TIMESTAMP,
    order_id       bigint
);

create table t_review
(
    id         BIGSERIAL primary key,
    review     varchar,
    rating     int,
    user_id    bigint,
    product_id bigint
);
