INSERT INTO users (id, balance, email, location, name, phone_number, surname) VALUES (1, 30000.00, 'beniteznahueloscar@gmail.com', 'Quilmes Oeste', 'Nahuel', '+42112555005', 'Benitez');
INSERT INTO users (id, balance, email, location, name, phone_number, surname) VALUES (2, 0.00, 'liza.chambi@gmail.com', 'Luis Guillón', 'Liza', '+42112555005', 'Chambi');

INSERT INTO providers (id, balance, city, description, email, location, logo, menus_removed, name, phone_number, website) VALUES (1, 30000.00, 'QUILMES', 'Comida gourmet hecha por los mejores chef de la zona', 'liza.chambi@gmail.com', 'Rivadavia 250', 'https://gourmetnatural.net/wp-content/uploads/2019/05/afs.png', 0, 'Liza''s', '+42112555005', 'https://gourmetnatural.net/es/home/');

INSERT INTO service_hours (id, closing_hours, day, opening_hours, opening_hours_days_id) VALUES (1, '15:00:00', 'MONDAY', '10:00:00', 1);

INSERT INTO provider_delivery_cities (provider_id, delivery_cities) VALUES (1, 3);
INSERT INTO provider_delivery_cities (provider_id, delivery_cities) VALUES (1, 0);
INSERT INTO provider_delivery_cities (provider_id, delivery_cities) VALUES (1, 2);

INSERT INTO effective_period (id, end_date, initial_date) VALUES (1, '2019-11-27', '2019-11-20');
INSERT INTO effective_period (id, end_date, initial_date) VALUES (2, '2020-05-31', '2020-02-02');
INSERT INTO effective_period (id, end_date, initial_date) VALUES (3, '2020-05-31', '2020-02-02');
INSERT INTO effective_period (id, end_date, initial_date) VALUES (4, '2020-05-31', '2020-02-02');
INSERT INTO effective_period (id, end_date, initial_date) VALUES (5, '2020-05-31', '2020-02-02');
INSERT INTO effective_period (id, end_date, initial_date) VALUES (6, '2020-05-31', '2020-02-02');
INSERT INTO effective_period (id, end_date, initial_date) VALUES (7, '2020-05-31', '2020-02-02');
INSERT INTO effective_period (id, end_date, initial_date) VALUES (8, '2020-05-31', '2020-02-02');
INSERT INTO effective_period (id, end_date, initial_date) VALUES (9, '2020-05-31', '2020-02-02');
INSERT INTO effective_period (id, end_date, initial_date) VALUES (10, '2020-05-31', '2020-02-02');
INSERT INTO effective_period (id, end_date, initial_date) VALUES (11, '2020-05-31', '2020-02-02');
INSERT INTO effective_period (id, end_date, initial_date) VALUES (12, '2019-11-27', '2019-11-20');
INSERT INTO effective_period (id, end_date, initial_date) VALUES (13, '2019-11-27', '2019-11-20');
INSERT INTO effective_period (id, end_date, initial_date) VALUES (14, '2019-11-27', '2019-11-20');
INSERT INTO effective_period (id, end_date, initial_date) VALUES (15, '2019-11-27', '2019-11-20');
INSERT INTO effective_period (id, end_date, initial_date) VALUES (16, '2019-11-27', '2019-11-20');
INSERT INTO effective_period (id, end_date, initial_date) VALUES (17, '2019-11-27', '2019-11-20');
INSERT INTO effective_period (id, end_date, initial_date) VALUES (18, '2019-11-27', '2019-11-20');
INSERT INTO effective_period (id, end_date, initial_date) VALUES (19, '2019-11-27', '2019-11-20');
INSERT INTO effective_period (id, end_date, initial_date) VALUES (20, '2019-11-27', '2019-11-20');

INSERT INTO offer (id, price, quantity) VALUES (1, 180, 10);
INSERT INTO offer (id, price, quantity) VALUES (2, 150, 80);
INSERT INTO offer (id, price, quantity) VALUES (3, 90, 50);
INSERT INTO offer (id, price, quantity) VALUES (4, 80, 150);
INSERT INTO offer (id, price, quantity) VALUES (5, 90, 50);
INSERT INTO offer (id, price, quantity) VALUES (6, 80, 150);
INSERT INTO offer (id, price, quantity) VALUES (7, 90, 50);
INSERT INTO offer (id, price, quantity) VALUES (8, 80, 150);
INSERT INTO offer (id, price, quantity) VALUES (9, 90, 50);
INSERT INTO offer (id, price, quantity) VALUES (10, 80, 150);
INSERT INTO offer (id, price, quantity) VALUES (11, 90, 50);
INSERT INTO offer (id, price, quantity) VALUES (12, 80, 150);
INSERT INTO offer (id, price, quantity) VALUES (13, 90, 50);
INSERT INTO offer (id, price, quantity) VALUES (14, 80, 150);
INSERT INTO offer (id, price, quantity) VALUES (15, 90, 50);
INSERT INTO offer (id, price, quantity) VALUES (16, 80, 150);
INSERT INTO offer (id, price, quantity) VALUES (17, 90, 50);
INSERT INTO offer (id, price, quantity) VALUES (18, 80, 150);
INSERT INTO offer (id, price, quantity) VALUES (19, 90, 50);
INSERT INTO offer (id, price, quantity) VALUES (20, 80, 150);
INSERT INTO offer (id, price, quantity) VALUES (21, 90, 50);
INSERT INTO offer (id, price, quantity) VALUES (22, 80, 150);
INSERT INTO offer (id, price, quantity) VALUES (23, 180, 10);
INSERT INTO offer (id, price, quantity) VALUES (24, 150, 80);
INSERT INTO offer (id, price, quantity) VALUES (25, 180, 10);
INSERT INTO offer (id, price, quantity) VALUES (26, 150, 80);
INSERT INTO offer (id, price, quantity) VALUES (27, 180, 10);
INSERT INTO offer (id, price, quantity) VALUES (28, 150, 80);
INSERT INTO offer (id, price, quantity) VALUES (29, 180, 10);
INSERT INTO offer (id, price, quantity) VALUES (30, 150, 80);
INSERT INTO offer (id, price, quantity) VALUES (31, 180, 10);
INSERT INTO offer (id, price, quantity) VALUES (32, 150, 80);
INSERT INTO offer (id, price, quantity) VALUES (33, 180, 10);
INSERT INTO offer (id, price, quantity) VALUES (34, 150, 80);
INSERT INTO offer (id, price, quantity) VALUES (35, 180, 10);
INSERT INTO offer (id, price, quantity) VALUES (36, 150, 80);
INSERT INTO offer (id, price, quantity) VALUES (37, 180, 10);
INSERT INTO offer (id, price, quantity) VALUES (38, 150, 80);
INSERT INTO offer (id, price, quantity) VALUES (39, 180, 10);
INSERT INTO offer (id, price, quantity) VALUES (40, 150, 80);

INSERT INTO menu (id, average_delivery_time, daily_stock, delivery_price, description, image, name, price, provider_email, effective_period_id, offer1_id, offer2_id, current_menus_id) VALUES (1, '00:30:00', 100, 10, 'Hamburguesa con lechuga, tomate y huevo', 'https://static.mercadoshops.com/hamburguesa-completa-con-fritas_iZ97382298XvZgrandeXpZ1XfZ97514688-96459770849-1XsZ97514688xIM.jpg', 'Hamburguesa completa', 200, 'liza.chambi@gmail.com', 1, 1, 2, 1);
INSERT INTO menu (id, average_delivery_time, daily_stock, delivery_price, description, image, name, price, provider_email, effective_period_id, offer1_id, offer2_id, current_menus_id) VALUES (2, '00:45:00', 500, 20, 'Cerveza Quilmes lata 500ml', 'https://http2.mlstatic.com/cervezas-D_NP_652355-MLA28894867640_122018-Q.jpg', 'Cerveza Quilmes', 100, 'liza.chambi@gmail.com', 2, 3, 4, 1);
INSERT INTO menu (id, average_delivery_time, daily_stock, delivery_price, description, image, name, price, provider_email, effective_period_id, offer1_id, offer2_id, current_menus_id) VALUES (3, '00:45:00', 500, 20, 'Cerveza Quilmes lata 500ml', 'https://http2.mlstatic.com/cervezas-D_NP_652355-MLA28894867640_122018-Q.jpg', 'Cerveza Quilmes2', 100, 'liza.chambi@gmail.com', 3, 5, 6, 1);
INSERT INTO menu (id, average_delivery_time, daily_stock, delivery_price, description, image, name, price, provider_email, effective_period_id, offer1_id, offer2_id, current_menus_id) VALUES (4, '00:45:00', 500, 20, 'Cerveza Quilmes lata 500ml', 'https://http2.mlstatic.com/cervezas-D_NP_652355-MLA28894867640_122018-Q.jpg', 'Cerveza Quilmes3', 100, 'liza.chambi@gmail.com', 4, 7, 8, 1);
INSERT INTO menu (id, average_delivery_time, daily_stock, delivery_price, description, image, name, price, provider_email, effective_period_id, offer1_id, offer2_id, current_menus_id) VALUES (5, '00:45:00', 500, 20, 'Cerveza Quilmes lata 500ml', 'https://http2.mlstatic.com/cervezas-D_NP_652355-MLA28894867640_122018-Q.jpg', 'Cerveza Quilmes4', 100, 'liza.chambi@gmail.com', 5, 9, 10, 1);
INSERT INTO menu (id, average_delivery_time, daily_stock, delivery_price, description, image, name, price, provider_email, effective_period_id, offer1_id, offer2_id, current_menus_id) VALUES (6, '00:45:00', 500, 20, 'Cerveza Quilmes lata 500ml', 'https://http2.mlstatic.com/cervezas-D_NP_652355-MLA28894867640_122018-Q.jpg', 'Cerveza Quilmes5', 100, 'liza.chambi@gmail.com', 6, 11, 12, 1);
INSERT INTO menu (id, average_delivery_time, daily_stock, delivery_price, description, image, name, price, provider_email, effective_period_id, offer1_id, offer2_id, current_menus_id) VALUES (7, '00:45:00', 500, 20, 'Cerveza Quilmes lata 500ml', 'https://http2.mlstatic.com/cervezas-D_NP_652355-MLA28894867640_122018-Q.jpg', 'Cerveza Quilmes6', 100, 'liza.chambi@gmail.com', 7, 13, 14, 1);
INSERT INTO menu (id, average_delivery_time, daily_stock, delivery_price, description, image, name, price, provider_email, effective_period_id, offer1_id, offer2_id, current_menus_id) VALUES (8, '00:45:00', 500, 20, 'Cerveza Quilmes lata 500ml', 'https://http2.mlstatic.com/cervezas-D_NP_652355-MLA28894867640_122018-Q.jpg', 'Cerveza Quilmes7', 100, 'liza.chambi@gmail.com', 8, 15, 16, 1);
INSERT INTO menu (id, average_delivery_time, daily_stock, delivery_price, description, image, name, price, provider_email, effective_period_id, offer1_id, offer2_id, current_menus_id) VALUES (9, '00:45:00', 500, 20, 'Cerveza Quilmes lata 500ml', 'https://http2.mlstatic.com/cervezas-D_NP_652355-MLA28894867640_122018-Q.jpg', 'Cerveza Quilmes8', 100, 'liza.chambi@gmail.com', 9, 17, 18, 1);
INSERT INTO menu (id, average_delivery_time, daily_stock, delivery_price, description, image, name, price, provider_email, effective_period_id, offer1_id, offer2_id, current_menus_id) VALUES (10, '00:45:00', 500, 20, 'Cerveza Quilmes lata 500ml', 'https://http2.mlstatic.com/cervezas-D_NP_652355-MLA28894867640_122018-Q.jpg', 'Cerveza Quilmes9', 100, 'liza.chambi@gmail.com', 10, 19, 20, 1);
INSERT INTO menu (id, average_delivery_time, daily_stock, delivery_price, description, image, name, price, provider_email, effective_period_id, offer1_id, offer2_id, current_menus_id) VALUES (11, '00:45:00', 500, 20, 'Cerveza Quilmes lata 500ml', 'https://http2.mlstatic.com/cervezas-D_NP_652355-MLA28894867640_122018-Q.jpg', 'Cerveza Quilmes10', 100, 'liza.chambi@gmail.com', 11, 21, 22, 1);
INSERT INTO menu (id, average_delivery_time, daily_stock, delivery_price, description, image, name, price, provider_email, effective_period_id, offer1_id, offer2_id, current_menus_id) VALUES (12, '00:30:00', 100, 10, 'Hamburguesa con lechuga, tomate y huevo', 'https://static.mercadoshops.com/hamburguesa-completa-con-fritas_iZ97382298XvZgrandeXpZ1XfZ97514688-96459770849-1XsZ97514688xIM.jpg', 'Hamburguesa completa2', 200, 'liza.chambi@gmail.com', 12, 23, 24, 1);
INSERT INTO menu (id, average_delivery_time, daily_stock, delivery_price, description, image, name, price, provider_email, effective_period_id, offer1_id, offer2_id, current_menus_id) VALUES (13, '00:30:00', 100, 10, 'Hamburguesa con lechuga, tomate y huevo', 'https://static.mercadoshops.com/hamburguesa-completa-con-fritas_iZ97382298XvZgrandeXpZ1XfZ97514688-96459770849-1XsZ97514688xIM.jpg', 'Hamburguesa completa3', 200, 'liza.chambi@gmail.com', 13, 25, 26, 1);
INSERT INTO menu (id, average_delivery_time, daily_stock, delivery_price, description, image, name, price, provider_email, effective_period_id, offer1_id, offer2_id, current_menus_id) VALUES (14, '00:30:00', 100, 10, 'Hamburguesa con lechuga, tomate y huevo', 'https://static.mercadoshops.com/hamburguesa-completa-con-fritas_iZ97382298XvZgrandeXpZ1XfZ97514688-96459770849-1XsZ97514688xIM.jpg', 'Hamburguesa completa4', 200, 'liza.chambi@gmail.com', 14, 27, 28, 1);
INSERT INTO menu (id, average_delivery_time, daily_stock, delivery_price, description, image, name, price, provider_email, effective_period_id, offer1_id, offer2_id, current_menus_id) VALUES (15, '00:30:00', 100, 10, 'Hamburguesa con lechuga, tomate y huevo', 'https://static.mercadoshops.com/hamburguesa-completa-con-fritas_iZ97382298XvZgrandeXpZ1XfZ97514688-96459770849-1XsZ97514688xIM.jpg', 'Hamburguesa completa5', 200, 'liza.chambi@gmail.com', 15, 29, 30, 1);
INSERT INTO menu (id, average_delivery_time, daily_stock, delivery_price, description, image, name, price, provider_email, effective_period_id, offer1_id, offer2_id, current_menus_id) VALUES (16, '00:30:00', 100, 10, 'Hamburguesa con lechuga, tomate y huevo', 'https://static.mercadoshops.com/hamburguesa-completa-con-fritas_iZ97382298XvZgrandeXpZ1XfZ97514688-96459770849-1XsZ97514688xIM.jpg', 'Hamburguesa completa6', 200, 'liza.chambi@gmail.com', 16, 31, 32, 1);
INSERT INTO menu (id, average_delivery_time, daily_stock, delivery_price, description, image, name, price, provider_email, effective_period_id, offer1_id, offer2_id, current_menus_id) VALUES (17, '00:30:00', 100, 10, 'Hamburguesa con lechuga, tomate y huevo', 'https://static.mercadoshops.com/hamburguesa-completa-con-fritas_iZ97382298XvZgrandeXpZ1XfZ97514688-96459770849-1XsZ97514688xIM.jpg', 'Hamburguesa completa7', 200, 'liza.chambi@gmail.com', 17, 33, 34, 1);
INSERT INTO menu (id, average_delivery_time, daily_stock, delivery_price, description, image, name, price, provider_email, effective_period_id, offer1_id, offer2_id, current_menus_id) VALUES (18, '00:30:00', 100, 10, 'Hamburguesa con lechuga, tomate y huevo', 'https://static.mercadoshops.com/hamburguesa-completa-con-fritas_iZ97382298XvZgrandeXpZ1XfZ97514688-96459770849-1XsZ97514688xIM.jpg', 'Hamburguesa completa8', 200, 'liza.chambi@gmail.com', 18, 35, 36, 1);
INSERT INTO menu (id, average_delivery_time, daily_stock, delivery_price, description, image, name, price, provider_email, effective_period_id, offer1_id, offer2_id, current_menus_id) VALUES (19, '00:30:00', 100, 10, 'Hamburguesa con lechuga, tomate y huevo', 'https://static.mercadoshops.com/hamburguesa-completa-con-fritas_iZ97382298XvZgrandeXpZ1XfZ97514688-96459770849-1XsZ97514688xIM.jpg', 'Hamburguesa completa9', 200, 'liza.chambi@gmail.com', 19, 37, 38, 1);
INSERT INTO menu (id, average_delivery_time, daily_stock, delivery_price, description, image, name, price, provider_email, effective_period_id, offer1_id, offer2_id, current_menus_id) VALUES (20, '00:30:00', 100, 10, 'Hamburguesa con lechuga, tomate y huevo', 'https://static.mercadoshops.com/hamburguesa-completa-con-fritas_iZ97382298XvZgrandeXpZ1XfZ97514688-96459770849-1XsZ97514688xIM.jpg', 'Hamburguesa completa10', 200, 'liza.chambi@gmail.com', 20, 39, 40, 1);



INSERT INTO menu_category (menu_id, category) VALUES (1, 2);

INSERT INTO menu_category (menu_id, category) VALUES (2, 1);
INSERT INTO menu_category (menu_id, category) VALUES (3, 1);
INSERT INTO menu_category (menu_id, category) VALUES (4, 1);
INSERT INTO menu_category (menu_id, category) VALUES (5, 1);
INSERT INTO menu_category (menu_id, category) VALUES (6, 1);
INSERT INTO menu_category (menu_id, category) VALUES (7, 1);
INSERT INTO menu_category (menu_id, category) VALUES (8, 1);
INSERT INTO menu_category (menu_id, category) VALUES (9, 1);
INSERT INTO menu_category (menu_id, category) VALUES (10, 1);
INSERT INTO menu_category (menu_id, category) VALUES (11, 1);
INSERT INTO menu_category (menu_id, category) VALUES (12, 2);
INSERT INTO menu_category (menu_id, category) VALUES (13, 2);
INSERT INTO menu_category (menu_id, category) VALUES (14, 2);
INSERT INTO menu_category (menu_id, category) VALUES (15, 2);
INSERT INTO menu_category (menu_id, category) VALUES (16, 2);
INSERT INTO menu_category (menu_id, category) VALUES (17, 2);
INSERT INTO menu_category (menu_id, category) VALUES (18, 2);
INSERT INTO menu_category (menu_id, category) VALUES (19, 2);
INSERT INTO menu_category (menu_id, category) VALUES (20, 2);

INSERT INTO menu_deliveries_schedules (menu_id, delivery_schedule) VALUES (1, '13:00:00');

INSERT INTO menu_deliveries_schedules (menu_id, delivery_schedule) VALUES (1, '20:00:00');
INSERT INTO menu_deliveries_schedules (menu_id, delivery_schedule) VALUES (2, '20:00:00');
INSERT INTO menu_deliveries_schedules (menu_id, delivery_schedule) VALUES (2, '23:59:59');
INSERT INTO menu_deliveries_schedules (menu_id, delivery_schedule) VALUES (3, '20:00:00');
INSERT INTO menu_deliveries_schedules (menu_id, delivery_schedule) VALUES (3, '23:59:59');
INSERT INTO menu_deliveries_schedules (menu_id, delivery_schedule) VALUES (4, '20:00:00');
INSERT INTO menu_deliveries_schedules (menu_id, delivery_schedule) VALUES (4, '23:59:59');
INSERT INTO menu_deliveries_schedules (menu_id, delivery_schedule) VALUES (5, '20:00:00');
INSERT INTO menu_deliveries_schedules (menu_id, delivery_schedule) VALUES (5, '23:59:59');
INSERT INTO menu_deliveries_schedules (menu_id, delivery_schedule) VALUES (6, '20:00:00');
INSERT INTO menu_deliveries_schedules (menu_id, delivery_schedule) VALUES (6, '23:59:59');
INSERT INTO menu_deliveries_schedules (menu_id, delivery_schedule) VALUES (7, '20:00:00');
INSERT INTO menu_deliveries_schedules (menu_id, delivery_schedule) VALUES (7, '23:59:59');
INSERT INTO menu_deliveries_schedules (menu_id, delivery_schedule) VALUES (8, '20:00:00');
INSERT INTO menu_deliveries_schedules (menu_id, delivery_schedule) VALUES (8, '23:59:59');
INSERT INTO menu_deliveries_schedules (menu_id, delivery_schedule) VALUES (9, '20:00:00');
INSERT INTO menu_deliveries_schedules (menu_id, delivery_schedule) VALUES (9, '23:59:59');
INSERT INTO menu_deliveries_schedules (menu_id, delivery_schedule) VALUES (10, '20:00:00');
INSERT INTO menu_deliveries_schedules (menu_id, delivery_schedule) VALUES (10, '23:59:59');
INSERT INTO menu_deliveries_schedules (menu_id, delivery_schedule) VALUES (11, '20:00:00');
INSERT INTO menu_deliveries_schedules (menu_id, delivery_schedule) VALUES (11, '23:59:59');
INSERT INTO menu_deliveries_schedules (menu_id, delivery_schedule) VALUES (12, '13:00:00');
INSERT INTO menu_deliveries_schedules (menu_id, delivery_schedule) VALUES (12, '20:00:00');
INSERT INTO menu_deliveries_schedules (menu_id, delivery_schedule) VALUES (13, '13:00:00');
INSERT INTO menu_deliveries_schedules (menu_id, delivery_schedule) VALUES (13, '20:00:00');
INSERT INTO menu_deliveries_schedules (menu_id, delivery_schedule) VALUES (14, '13:00:00');
INSERT INTO menu_deliveries_schedules (menu_id, delivery_schedule) VALUES (14, '20:00:00');
INSERT INTO menu_deliveries_schedules (menu_id, delivery_schedule) VALUES (15, '13:00:00');
INSERT INTO menu_deliveries_schedules (menu_id, delivery_schedule) VALUES (15, '20:00:00');
INSERT INTO menu_deliveries_schedules (menu_id, delivery_schedule) VALUES (16, '13:00:00');
INSERT INTO menu_deliveries_schedules (menu_id, delivery_schedule) VALUES (16, '20:00:00');
INSERT INTO menu_deliveries_schedules (menu_id, delivery_schedule) VALUES (17, '13:00:00');
INSERT INTO menu_deliveries_schedules (menu_id, delivery_schedule) VALUES (17, '20:00:00');
INSERT INTO menu_deliveries_schedules (menu_id, delivery_schedule) VALUES (18, '13:00:00');
INSERT INTO menu_deliveries_schedules (menu_id, delivery_schedule) VALUES (18, '20:00:00');
INSERT INTO menu_deliveries_schedules (menu_id, delivery_schedule) VALUES (19, '13:00:00');
INSERT INTO menu_deliveries_schedules (menu_id, delivery_schedule) VALUES (19, '20:00:00');
INSERT INTO menu_deliveries_schedules (menu_id, delivery_schedule) VALUES (20, '13:00:00');
INSERT INTO menu_deliveries_schedules (menu_id, delivery_schedule) VALUES (20, '20:00:00');

INSERT INTO current_order (id, client_id, orders_id) VALUES (1, 1, 1);

INSERT INTO orders (id, delivery_date_and_hour, order_date_and_hour, provider_email, quantity, ranking, status, type_delivery, value, menu_id, order_history_id, orders_id) VALUES (1, '2020-02-26 13:00:00.000000', '2020-02-25 13:00:00.000000', 'liza.chambi@gmail.com', 5, 0, 'CREATED', 'HOME_DELIVERY', 1010, 1, 1, 1);
INSERT INTO orders (id, delivery_date_and_hour, order_date_and_hour, provider_email, quantity, ranking, status, type_delivery, value, menu_id, order_history_id, orders_id) VALUES (2, '2020-02-26 13:00:00.000000', '2020-02-25 13:00:00.000000', 'liza.chambi@gmail.com', 80, 0, 'CREATED', 'HOME_DELIVERY', 16010, 1, 1, 1);

--INSERT INTO orders (id, delivery_date_and_hour, order_date_and_hour, provider_email, quantity, ranking, status, type_delivery, value, menu_id, order_history_id) VALUES (1, '2020-02-19 13:00:00.000000', '2020-02-18 13:00:00.000000', 'liza.chambi@gmail.com', 1, 0, 'CREATED', 'HOME_DELIVERY', 210, 1, 1);
--INSERT INTO orders (id, delivery_date_and_hour, order_date_and_hour, provider_email, quantity, ranking, status, type_delivery, value, menu_id, order_history_id) VALUES (2, '2020-02-17 13:00:00.000000', '2020-02-16 13:00:00.000000', 'liza.chambi@gmail.com', 1, 0, 'IN_PROGRESS', 'HOME_DELIVERY', 210, 1, 1);


