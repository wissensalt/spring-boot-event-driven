-- ##### Create Database Order ####
-- Drop table
-- DROP TABLE public.event_state;
CREATE TABLE public.event_state (
	id bigserial NOT NULL,
	trx_code varchar NULL,
	trx_status bool NULL,
	created_by varchar NULL,
	created_on timestamp NULL,
	modified_by varchar NULL,
	modified_on timestamp NULL
);
-- Drop table
-- DROP TABLE public.event_state_detail;
CREATE TABLE public.event_state_detail (
	id bigserial NOT NULL,
	trx_code varchar NULL,
	service_name varchar NULL,
	status bool NULL,
	created_by varchar NULL,
	created_on timestamp NULL,
	modified_by varchar NULL,
	modified_on timestamp NULL,
	remarks varchar(256) NULL,
	payload text NULL
);
-- Drop table
-- DROP TABLE public.http_log;
CREATE TABLE public.http_log (
	id bigserial NOT NULL,
	request_time timestamp NULL,
	endpoint_name varchar NULL,
	request text NULL,
	response text NULL,
	created_by varchar NULL,
	created_on timestamp NULL,
	modified_by varchar NULL,
	modified_on timestamp NULL
);
-- Drop table
-- DROP TABLE public.trx_order;
CREATE TABLE public.trx_order (
	id bigserial NOT NULL,
	trx_code varchar NULL,
	customer_id int8 NULL,
	total_price float8 NULL,
	created_by varchar NULL,
	created_on timestamp NULL,
	modified_by varchar NULL,
	modified_on timestamp NULL,
	status bool NULL,
	CONSTRAINT order_pkey PRIMARY KEY (id)
);
-- Drop table
-- DROP TABLE public.trx_order_detail;
CREATE TABLE public.trx_order_detail (
	id bigserial NOT NULL,
	product_id int8 NULL,
	quantity int4 NULL,
	price float8 NULL,
	created_by varchar NULL,
	created_on timestamp NULL,
	modified_by varchar NULL,
	modified_on timestamp NULL,
	order_id int8 NULL,
	CONSTRAINT trx_order_detail_pkey PRIMARY KEY (id),
	CONSTRAINT trx_order_detail_fk FOREIGN KEY (order_id) REFERENCES trx_order(id)
);

-- #### Create Database Inventory ####
-- Drop table
-- DROP TABLE public.trx_inventory_header;
CREATE TABLE public.trx_inventory_header (
	id bigserial NOT NULL,
	current_stock int4 NULL,
	trx_code varchar NULL,
	product_id int8 NULL,
	created_by varchar NULL,
	created_on timestamp NULL,
	modified_by varchar NULL,
	modified_on timestamp NULL,
	CONSTRAINT trx_inventory_header_pk PRIMARY KEY (id)
);
-- Drop table
-- DROP TABLE public.trx_inventory_detail;
CREATE TABLE public.trx_inventory_detail (
	id bigserial NOT NULL,
	product_id int8 NULL,
	stock_in int4 NULL,
	stock_out int4 NULL,
	created_by varchar NULL,
	created_on timestamp NULL,
	modified_by varchar NULL,
	modified_on timestamp NULL,
	inventory_header_id int8 NULL,
	CONSTRAINT trx_inventory_detail_pkey PRIMARY KEY (id),
	CONSTRAINT trx_inventory_detail_fk FOREIGN KEY (inventory_header_id) REFERENCES trx_inventory_header(id)
);

-- #### Create Database Payment ####
-- Drop table
-- DROP TABLE public.trx_payment;
CREATE TABLE public.trx_payment (
	id bigserial NOT NULL,
	transaction_code varchar NULL,
	payment_total float8 NULL,
	payment_type varchar NULL,
	payment_status varchar NULL,
	created_by varchar NULL,
	created_on timestamp NULL,
	modified_by varchar NULL,
	modified_on timestamp NULL,
	CONSTRAINT trx_payment_pk PRIMARY KEY (id)
);

-- #### Create Database Product ####
-- Drop table
-- DROP TABLE public.mst_product;
CREATE TABLE public.mst_product (
	id bigserial NOT NULL,
	name varchar NULL,
	created_by varchar NULL,
	created_on timestamp NULL,
	modified_by varchar NULL,
	modified_on timestamp NULL,
	current_stock int4 NULL,
	transaction_code varchar NULL,
	previous_stock int4 NULL,
	CONSTRAINT " mst_product_pkey" PRIMARY KEY (id)
);
-- Insert Sample Data Into Product
INSERT INTO public.mst_product
("name", created_by, created_on, modified_by, modified_on, current_stock, transaction_code, previous_stock)
VALUES('Item 1', 'ADMIN', '2019-08-06 11:17:38', 'ADMIN', '2019-08-22 15:14:56', 100, '', 0);


-- #### Create Database customer ####
-- Drop table
-- DROP TABLE public.mst_customer;
CREATE TABLE public.mst_customer (
	id bigserial NOT NULL,
	user_name varchar NULL,
	created_by varchar NULL,
	created_on timestamp NULL,
	modified_by varchar NULL,
	modified_on timestamp NULL,
	CONSTRAINT mst_customer_pkey PRIMARY KEY (id)
);


