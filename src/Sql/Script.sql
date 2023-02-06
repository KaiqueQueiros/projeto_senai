drop database data_trevo_agro;
create database data_trevo_agro
CREATE TABLE public.Tb_IMAGE (
                id_img serial,
                name VARCHAR(20) NOT NULL,
                img BYTEA NOT NULL,
                CONSTRAINT tb_image_pk PRIMARY KEY (id_img)
);

CREATE TABLE public.Tb_PRODUCT (
                cod_product serial,
                id_img VARCHAR(10) NOT NULL,
                status BOOLEAN NOT NULL,
                area_size VARCHAR NOT NULL,
                description VARCHAR(500) NOT NULL,
                date DATE NOT NULL,
                culture VARCHAR(70) NOT NULL,
                CONSTRAINT tb_product_pk PRIMARY KEY (cod_product)
);

CREATE TABLE public.Tb_image_product(
	id serial,
	id_product integer not null,
	id_img integer not null,
	constraint pk_tb_image_product_id primary key(id),
	constraint fk_tb_image_product_product_cod_product foreign key (id_product) references Tb_product(cod_product),
	constraint fk_tb_image_image_id_img foreign key(id_img) references Tb_image(id_img)
	);

CREATE TABLE public.Tb_Client (
                cod_client serial,
                name VARCHAR(50) NOT NULL,
                email VARCHAR(50) NOT NULL,
                country VARCHAR(20) NOT NULL,
                phone VARCHAR(14) NOT NULL,
                CONSTRAINT tb_client_pk PRIMARY KEY (cod_client)
);


CREATE TABLE public.Tb_order (
                cod_order serial,
                cod_client INTEGER NOT NULL,
                date DATE NOT NULL,
                CONSTRAINT tb_order_pk PRIMARY KEY (cod_order),
				CONSTRAINT tb_client_tb_order_fk FOREIGN KEY (cod_client) REFERENCES public.Tb_Client (cod_client)
);

CREATE TABLE public.Tb_ITEM_ORDER (
                cod_order serial,
                cod_product INTEGER NOT NULL,
                CONSTRAINT tb_item_order_pk PRIMARY KEY (cod_order, cod_product),
				CONSTRAINT tb_product_tb_item_order_fk FOREIGN KEY (cod_product) REFERENCES public.Tb_PRODUCT (cod_product)
);
