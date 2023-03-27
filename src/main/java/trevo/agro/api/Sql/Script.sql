create database data_trevo_agro;
create table tb_area (
      id bigserial not null,
        size varchar(255),
        primary key (id)
    );
create table tb_budget (
          id bigserial not null,
          company varchar(30) not null,
          country varchar(20) not null,
          date date,
          email varchar(35) not null,
          name varchar(25) not null,
          phone varchar(15) not null,
          primary key (id)
        );
create table tb_budget_product (
        budget_id bigint not null,
        product_id bigint not null
        );
create table tb_category (
        id bigserial not null,
        name varchar(30) not null,
        primary key (id)
        );
create table tb_culture (
        id bigserial not null,
        name varchar(20),
        primary key (id)
        );
create table tb_image (
       id bigserial not null,
        imagedata oid,
        name varchar(30),
        type varchar(30),
        primary key (id)
    );
create table tb_product (
       id bigserial not null,
        active boolean,
        date date,
        description Text not null,
        name varchar(50) not null,
        primary key (id)
    );
 create table tb_product_area (
       product_id bigint not null,
        area_id bigint not null
    );
 create table tb_product_category (
       product_id bigint not null,
        category_id bigint not null
    );
 create table tb_product_culture (
       product_id bigint not null,
        culture_id bigint not null
    );
create table tb_product_image (
       product_id bigint not null,
        image_id bigint not null
    );
alter table if exists tb_category
       add constraint UK_4gpl7afmaxiecnv7fv7unn2mp unique (name);
alter table if exists tb_culture
       add constraint UK_4m89i9d34k8pf6oyri79fipyg unique (name);
alter table if exists tb_product
       add constraint UK_lovy3681ry0dl5ox28r6679x6 unique (name);
alter table if exists tb_product_category
       add constraint UK_jq01bgpv8xbutbf9p3bm2bhlt unique (category_id);
alter table if exists tb_product_image
       add constraint UK_tj8xj0pipu5cmrl47xuw58a3u unique (image_id);
alter table if exists tb_budget_product
       add constraint FKdxafxq1mvnd0eaa2pbmw0hxpx
       foreign key (product_id)
       references tb_product;
 alter table if exists tb_budget_product
       add constraint FK6cugse6nya5og7oe00n5rl04
       foreign key (budget_id)
       references tb_budget;
 alter table if exists tb_product_area
       add constraint FK7c29tkobfhftnna6qir42nhgg
       foreign key (area_id)
       references tb_area;
alter table if exists tb_product_area
       add constraint FK8tb0w2t1l11tm57ydf76akfc1
       foreign key (product_id)
       references tb_product;
alter table if exists tb_product_category
       add constraint FK5r4sbavb4nkd9xpl0f095qs2a
       foreign key (category_id)
       references tb_category;
alter table if exists tb_product_category
       add constraint FKgbof0jclmaf8wn2alsoexxq3u
       foreign key (product_id)
       references tb_product;
alter table if exists tb_product_culture
       add constraint FKkaq7dyg3hpyc3llsgiofsh7en
       foreign key (culture_id)
       references tb_culture;
alter table if exists tb_product_culture
       add constraint FKs6pd7hqgil6cib7g0y6llsw4f
       foreign key (product_id)
       references tb_product;
alter table if exists tb_product_image
       add constraint FKccd9pejd18sb2s19upjqyt4ii
       foreign key (image_id)
       references tb_image;
alter table if exists tb_product_image
       add constraint FK6p9nu1bnwh5bhcpe1d0d3xnjm
       foreign key (product_id)
       references tb_product;
