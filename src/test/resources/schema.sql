drop table if exists bill;
drop table if exists delivery;
drop table if exists material;
drop table if exists shipment;
drop table if exists orders;
drop table if exists size;
drop table if exists status;
drop table if exists town;
drop table if exists region;
drop table if exists country;
drop table if exists user;
drop table if exists weight;



create table country
(
    id   int(5) auto_increment,
    name varchar(45) null,
    constraint id_UNIQUE
        unique (id),
    constraint name_UNIQUE
        unique (name)
);

alter table country
    add primary key (id);

create table delivery
(
    name  varchar(45) not null,
    price float       not null,
    constraint delivery_name_uindex
        unique (name)
);

alter table delivery
    add primary key (name);

create table material
(
    name varchar(45) not null,
    coefficient float       not null,
    constraint material_name_uindex
        unique (name),
    constraint material_name_uindex_2
        unique (name)
);

alter table material
    add primary key (name);

create table region
(
    id      int(5) auto_increment,
    name    varchar(45) not null,
    country int(5)      not null,
    constraint id_region_UNIQUE
        unique (id),
    constraint name_region_UNIQUE
        unique (name),
    constraint region_country_id_fk
        foreign key (country) references country (id)
            on update cascade on delete cascade
);

alter table region
    add primary key (id);

create table size
(
    name        varchar(45) not null primary key,
    coefficient float       not null,
    constraint size_name_uindex
        unique (name)
);



create table status
(
    name varchar(45) not null primary key ,
    constraint status_name_uindex
        unique (name)
);


create table town
(
    id     int auto_increment,
    name   varchar(45) null,
    region int(5)      not null,
    constraint id_town_UNIQUE
        unique (id),
    constraint town_region_id_fk
        foreign key (region) references region (id)
            on update cascade on delete cascade
);

alter table town
    add primary key (id);

create table user
(
    id       int auto_increment primary key ,
    name     varchar(45)          not null,
    surname  varchar(45)          null,
    password varchar(255)          not null,
    phone    varchar(19)          null,
    email    varchar(45)          not null,
    role     tinyint(1) default 0 null,
    constraint email_UNIQUE
        unique (email),
    constraint id_user_UNIQUE
        unique (id)
);

create table orders
(
    id          int(5) auto_increment primary key ,
    user        int(5)                            not null,
    destination int(5)                            not null,
    status     varchar(45) default 'NEW'         null,
    address     varchar(50) default 'main office' not null,
    constraint id_order_UNIQUE
        unique (id),
    constraint order_status_name_fk
        foreign key (status) references status (name)
            on update cascade on delete cascade,
    constraint order_town_id_fk_2
        foreign key (destination) references town (id)
            on update cascade on delete cascade,
    constraint order_user_id_fk
        foreign key (user) references user (id)
            on update cascade on delete cascade

);

create table shipment
(
    id      int(5) auto_increment primary key ,
    weight  float  not null,
    height  float  not null,
    width   float  not null,
    length  float  not null,
    orders int(5) not null,
    constraint id_shipment_UNIQUE
        unique (id),
    constraint shipment_order_id_fk
        foreign key (orders) references orders (id)
            on update cascade on delete cascade
);

create table weight
(
    name        varchar(45) not null primary key ,
    coefficient float       not null,
    constraint weight_name_uindex
        unique (name)
);

create table bill
(
    id       int(5) auto_increment primary key,
    shipment int(5)               not null,
    payment  tinyint(1) default 0 null,
    delivery varchar(45)          null,
    size     varchar(45)          null,
    weight   varchar(45)          null,
    material varchar(45)          null,
    constraint id_bill_UNIQUE
        unique (id),
    constraint bill_delivery_name_fk
        foreign key (delivery) references delivery (name)
            on update cascade on delete cascade,
    constraint bill_material_name_fk
        foreign key (material) references material (name)
            on update cascade on delete cascade,
    constraint bill_shipment_id_fk
        foreign key (shipment) references shipment (id)
            on update cascade on delete cascade,
    constraint bill_size_name_fk
        foreign key (size) references size (name)
            on update cascade on delete cascade,
    constraint bill_weight_name_fk
        foreign key (weight) references weight (name)
            on update cascade on delete cascade

);

create index bill_status_id_fk
    on bill (payment);



