insert into t_role (name)
values ('admin'),
       ('manager'),
       ('user');

--- adding users
insert into t_user (username, password, email, address)
values ('kalzhan', 'kalzhan', 'kalzhan@gmail.com', 'Dorm'),
       ('Admin', 'Admin', 'Admin@gmail.com', 'Narxoz'),
       ('Manager', 'manager', 'Manager@gmail.com', 'Almaty');

--- giving to users role
insert into t_user_roles (user_id, roles_id)
values (1, 3),
       (2, 1),
       (3, 2);

--- adding categories
insert into t_category (category_name)
values ('Phones'),
       ('Headphones'),
       ('PCs');

--- adding products
insert into t_product (product_name, product_price, product_description, category_id)
values ('Pc', 500000, 'HyperPC', 3),
       ('Iphone 15', 600000, 'Apple', 1),
       ('AirPods 4', 130000, 'Apple', 2);

--- adding order
insert into t_orders (order_date_time, status, total_amount, user_id)
values ('2025-12-26 14:13:24', 'delivered', 130000, 1);

--- adding information about order
insert into t_order_item (quantity, order_id, product_id)
values (1, 1, 3);

--- adding payment method
insert into t_payment (payment_method, payment_date, order_id)
values ('Kaspi', '2025-12-26 12:13:24', 1);

--- adding review
insert into t_review (review, rating, user_id, product_id)
values ('great headphones', 5, 1, 3);