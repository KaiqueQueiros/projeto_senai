drop database data_trevo_agro;
create database data_trevo_agro
drop table tb_budget_product,tb_product_category,tb_product_culture,Tb_order,Tb_Client,tb_category,Tb_PRODUCT,tb_culture

CREATE TABLE public.tb_category(
		id serial ,
		name varchar (255),
		primary key (id)			
); 
CREATE TABLE public.Tb_PRODUCT (
                id serial,
				name VARCHAR(40) not null,
                area_size VARCHAR NOT NULL,
                description VARCHAR(500) NOT NULL,
                date DATE DEFAULT CURRENT_DATE,
                culture VARCHAR(70) NOT NULL,
	 			img text,
				category_id integer,
                CONSTRAINT tb_product_pk PRIMARY KEY (id),
				CONSTRAINT tb_product_tb_product_category FOREIGN KEY (category_id) references public.tb_category(id)
);
---insert into tb_product (name,area_size,description,date,culture,img,category_id) values ('Condorito','500','alow','2020-05-21','cereais','slfmks nfpasfjasfa',1);
CREATE TABLE public.tb_culture (
       id serial not null,
       name varchar(255),
       primary key (id)
   );


CREATE TABLE public.Tb_Client (
                id serial,
                name VARCHAR(50) NOT NULL,
                email VARCHAR(50) NOT NULL,
                country VARCHAR(20) NOT NULL,
                phone VARCHAR(14) NOT NULL,
                CONSTRAINT tb_client_pk PRIMARY KEY (id)
);


CREATE TABLE public.Tb_order (
                id serial,
                id_client INTEGER NOT NULL,
                date DATE,
                CONSTRAINT tb_order_pk PRIMARY KEY (id),
				CONSTRAINT tb_client_tb_order_fk FOREIGN KEY (id_client) REFERENCES public.Tb_Client (id)
);
select * from public.tb_product

drop table public tb_culture
/*
CREATE TABLE public.Tb_ITEM_ORDER (
                id_item_order serial,
                cod_product INTEGER NOT NULL,
				cod_order integer not null,
                CONSTRAINT tb_item_order_pk PRIMARY KEY (id_item_order),
				CONSTRAINT tb_product_tb_item_order_fk FOREIGN KEY (cod_product) REFERENCES public.Tb_PRODUCT (id),
				CONSTRAINT tb_item_order_order_fk FOREIGN KEY (cod_order) REFERENCES public.Tb_order(id)
);

CREATE TABLE public.Tb_image_product(
	id serial,
	id_product integer not null,
	id_img integer not null,
	constraint pk_tb_image_product_id primary key(id),
	constraint fk_tb_image_product_product_cod_product foreign key (id_product) references Tb_product(id),
	constraint fk_tb_image_image_id_img foreign key(id_img) references Tb_image(id)
	);
	*/
	
	/*CREATE TABLE public.Tb_IMAGE (
                id serial,
                name VARCHAR(20) NOT NULL,
                img text NOT NULL,
                CONSTRAINT tb_image_pk PRIMARY KEY (id)
);*/
	
select * from tb_product
select * from public.tb_category

select * from public.tb_client


drop table tb_category

insert into 

