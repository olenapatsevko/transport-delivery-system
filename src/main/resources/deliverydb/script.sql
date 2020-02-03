create table if not exists country
(
    id   int(5) auto_increment,
    name varchar(45) null,
    constraint id_UNIQUE
        unique (id),
    constraint name_UNIQUE
        unique (name)
);

alter table country
    add primary key(id);

create table if not exists delivery_type
(
    id    int auto_increment
        primary key,
    name  varchar(50) default 'city' not null,
    price float       default 30 null,
    constraint delivery_type_name_uindex
        unique (name)
);

create table if not exists region
(
    id      int(5) auto_increment,
    name    varchar(45) not null,
    country int(5)      not null,
    constraint id_UNIQUE
        unique (id),
    constraint name_UNIQUE
        unique (name),
    constraint region_country_id_fk
        foreign key (country) references country (id)
            on update cascade on delete cascade
);

alter table region
    add primary key(id);

create table if not exists shipment_material
(
    id             int(5) auto_increment
        primary key,
    name           varchar(45) not null,
    ` coefficient` float default 1 not null,
    constraint shipment_material_name_uindex
        unique (name)
);

create table if not exists shipment_size
(
    id             int(5) auto_increment
        primary key,
    name           varchar(20) null,
    ` coefficient` float default 1 not null,
    constraint shipment_size_name_uindex
        unique (name)
);

create table if not exists shipment_weight
(
    id             int(5) auto_increment
        primary key,
    name           varchar(20) null,
    ` coefficient` float default 1 not null,
    constraint shipment_weight_name_uindex
        unique (name)
);

create table if not exists status
(
    id   int auto_increment
        primary key,
    name varchar(20) null
);

create table if not exists town
(
    id     int auto_increment,
    name   varchar(45) null,
    region int(5)      not null,
    constraint id_UNIQUE
        unique (id),
    constraint town_region_id_fk
        foreign key (region) references region (id)
            on update cascade on delete cascade
);

alter table town
    add primary key(id);

create table if not exists user
(
    id       int auto_increment,
    name     varchar(45)          not null,
    surname  varchar(45)          null,
    password varchar(20)          not null,
    phone    varchar(19)          null,
    email    varchar(45)          not null,
    role     tinyint(1) default 0 null,
    constraint email_UNIQUE
        unique (email),
    constraint iduser_UNIQUE
        unique (id)
) collate = utf8_unicode_ci;

alter table user
    add primary key(id);

create table if not exists `order`
(
    id          int(5) auto_increment,
    user        int(5)           not null,
    dispatch    int(5)           not null,
    destination int(5)           not null,
    receiver    int(5)           not null,
    status      int(5) default 3 null,
    constraint id_UNIQUE
        unique (id),
    constraint order_status_id_fk
        foreign key (status) references status (id)
            on update set null on delete set null,
    constraint order_town_id_fk
        foreign key (dispatch) references town (id),
    constraint order_town_id_fk_2
        foreign key (destination) references town (id),
    constraint order_user_id_fk
        foreign key (user) references user (id)
            on update cascade on delete cascade,
    constraint order_user_id_fk_2
        foreign key (receiver) references user (id)
);

create index country_idx
    on `order` (dispatch, destination);

create index town_idx
    on `order` (dispatch, destination);

alter table `order`
    add primary key(id);

create table if not exists shipment
(
    id      int(5) auto_increment,
    weight  float  not null,
    height  float  not null,
    width   float  not null,
    length  float  not null,
    `order` int(5) not null,
    constraint id_UNIQUE
        unique (id),
    constraint shipment_order_id_fk
        foreign key (`order`) references `order` (id)
            on update cascade on delete cascade
);

alter table shipment
    add primary key(id);

create table if not exists bill
(
    id       int(5) auto_increment,
    shipment int(5)               not null,
    paid     tinyint(1) default 0 null,
    delivery int(5)               null,
    size     int(5)               null,
    weight   int(5)               null,
    material int(5)               null,
    constraint id_UNIQUE
        unique (id),
    constraint bill_delivery_type_id_fk
        foreign key (delivery) references delivery_type (id)
            on update cascade on delete cascade,
    constraint bill_shipment_id_fk
        foreign key (shipment) references shipment (id)
            on update cascade on delete cascade,
    constraint bill_shipment_material_id_fk
        foreign key (material) references shipment_material (id)
            on update cascade on delete cascade,
    constraint bill_shipment_size_id_fk
        foreign key (size) references shipment_size (id)
            on update cascade on delete cascade,
    constraint bill_shipment_weight_id_fk
        foreign key (weight) references shipment_weight (id)
            on update cascade on delete cascade
);

create index bill_status_id_fk
    on bill (paid);

alter table bill
    add primary key(id);


