
CREATE SEQUENCE public.tb_product_id_seq;

CREATE TABLE public.tb_product (
                id BIGINT DEFAULT nextval('tb_product_id_seq'::regclass) NOT NULL DEFAULT nextval('public.tb_product_id_seq'),
                area_size VARCHAR(255),
                date DATE,
                description VARCHAR(255),
                img VARCHAR(255),
                name VARCHAR(255),
                CONSTRAINT tb_product_pkey PRIMARY KEY (id)
);


ALTER SEQUENCE public.tb_product_id_seq OWNED BY public.tb_product.id;

CREATE SEQUENCE public.tb_category_id_seq;

CREATE TABLE public.tb_category (
                id BIGINT DEFAULT nextval('tb_category_id_seq'::regclass) NOT NULL DEFAULT nextval('public.tb_category_id_seq'),
                name VARCHAR(255) NOT NULL,
                CONSTRAINT tb_category_pkey PRIMARY KEY (id)
);


ALTER SEQUENCE public.tb_category_id_seq OWNED BY public.tb_category.id;

CREATE TABLE public.tb_product_category (
                product_id BIGINT NOT NULL,
                category_id BIGINT NOT NULL
);


CREATE SEQUENCE public.tb_budget_id_seq;

CREATE TABLE public.tb_budget (
                id BIGINT DEFAULT nextval('tb_budget_id_seq'::regclass) NOT NULL DEFAULT nextval('public.tb_budget_id_seq'),
                company VARCHAR(255),
                country VARCHAR(255),
                date DATE,
                email VARCHAR(255),
                name VARCHAR(255),
                phone VARCHAR(255),
                CONSTRAINT tb_budget_pkey PRIMARY KEY (id)
);


ALTER SEQUENCE public.tb_budget_id_seq OWNED BY public.tb_budget.id;

CREATE TABLE public.tb_budget_product (
                budget_id BIGINT NOT NULL,
                product_id BIGINT NOT NULL
);


CREATE SEQUENCE public.tb_culture_id_seq;

CREATE TABLE public.tb_culture (
                id BIGINT DEFAULT nextval('tb_culture_id_seq'::regclass) NOT NULL DEFAULT nextval('public.tb_culture_id_seq'),
                name VARCHAR(255) NOT NULL,
                CONSTRAINT tb_culture_pkey PRIMARY KEY (id)
);


ALTER SEQUENCE public.tb_culture_id_seq OWNED BY public.tb_culture.id;

CREATE TABLE public.flyway_schema_history (
                installed_rank INTEGER NOT NULL,
                version VARCHAR(50),
                description VARCHAR(200) NOT NULL,
                type VARCHAR(20) NOT NULL,
                script VARCHAR(1000) NOT NULL,
                checksum INTEGER,
                installed_by VARCHAR(100) NOT NULL,
                installed_on TIMESTAMP DEFAULT now() NOT NULL,
                execution_time INTEGER NOT NULL,
                success BIT NOT NULL,
                CONSTRAINT flyway_schema_history_pk PRIMARY KEY (installed_rank)
);


CREATE INDEX flyway_schema_history_s_idx
 ON public.flyway_schema_history
 ( success ASC );

CREATE TABLE public.tb_product_culture (
                product_id BIGINT NOT NULL,
                culture_id BIGINT NOT NULL
);


ALTER TABLE public.tb_product_category ADD CONSTRAINT fkgbof0jclmaf8wn2alsoexxq3u
FOREIGN KEY (product_id)
REFERENCES public.tb_product (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.tb_product_culture ADD CONSTRAINT fks6pd7hqgil6cib7g0y6llsw4f
FOREIGN KEY (product_id)
REFERENCES public.tb_product (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.tb_budget_product ADD CONSTRAINT fkdxafxq1mvnd0eaa2pbmw0hxpx
FOREIGN KEY (product_id)
REFERENCES public.tb_product (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.tb_product_category ADD CONSTRAINT fk5r4sbavb4nkd9xpl0f095qs2a
FOREIGN KEY (category_id)
REFERENCES public.tb_category (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.tb_budget_product ADD CONSTRAINT fk6cugse6nya5og7oe00n5rl04
FOREIGN KEY (budget_id)
REFERENCES public.tb_budget (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.tb_product_culture ADD CONSTRAINT fkkaq7dyg3hpyc3llsgiofsh7en
FOREIGN KEY (culture_id)
REFERENCES public.tb_culture (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

Select * from tb_budget