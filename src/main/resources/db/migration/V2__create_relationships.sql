create table t_user_roles
(
    user_id bigint not null,
    roles_id bigint not null,
    primary key (user_id, roles_id),
    foreign key (user_id) references t_user (id) on delete cascade,
    foreign key (roles_id) references t_role (id) on delete cascade
);

alter table t_product
    add constraint fk_product_category
        foreign key (category_id) references t_category (id);

alter table t_orders
    add constraint fk_orders_user
        foreign key (user_id) references t_user (id);

alter table t_order_item
    add constraint fk_orderitem_order
        foreign key (order_id) references t_orders (id),
    add constraint fk_orderitem_product
        foreign key (product_id) references t_product (id);

alter table t_payment
    add constraint fk_payment_order
        foreign key (order_id) references t_orders (id);

alter table t_review
    add constraint fk_review_user
        foreign key (user_id) references t_user (id),
    add constraint fk_review_product
        foreign key (product_id) references t_product (id);