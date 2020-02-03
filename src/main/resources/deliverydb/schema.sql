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
    add primary key(id);

create table region
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

create table shipment
(
    id     int(5) auto_increment,
    weight float not null,
    height float not null,
    wigth  float not null,
    length float not null,
    constraint id_UNIQUE
        unique (id)
);

alter table shipment
    add primary key(id);

create table tarifftype
(
    id   int auto_increment,
    name varchar(45)    not null,
    koef float unsigned not null,
    primary key (id, name),
    constraint id_UNIQUE
        unique (id)
);

create table tariff
(
    id         int(5) auto_increment,
    name       varchar(45) not null,
    price      float       not null,
    tarifftype int(5)      not null,
    constraint idf_UNIQUE
        unique (id),
    constraint tariff_tarifftype_id_fk
        foreign key (tarifftype) references tarifftype (id)
            on update cascade on delete cascade
);

alter table tariff
    add primary key(id);

create table town
(
    id     int auto_increment,
    name   varchar(45) null,
    region int         not null,
    constraint id_UNIQUE
        unique (id),
    constraint town_region_id_fk
        foreign key (region) references region (id)
);

alter table town
    add primary key(id);

create table user
(
    id       int auto_increment,
    name     varchar(45) not null,
    email    varchar(45) not null,
    password varchar(20) not null,
    phone    varchar(13) null,
    constraint email_UNIQUE
        unique (email),
    constraint iduser_UNIQUE
        unique (id)
) collate = utf8_unicode_ci;

alter table user
    add primary key(id);

create table `order`
(
    id          int(5) auto_increment,
    user        int(5) not null,
    dispatch    int(5) not null,
    destination int(5) not null,
    shipment    int(5) not null,
    receiver    int(5) not null,
    constraint id_UNIQUE
        unique (id),
    constraint order_shipment_id_fk
        foreign key (shipment) references shipment (id)
            on update cascade on delete cascade,
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

create table bill
(
    id      int(5) auto_increment,
    `order` int(5) not null,
    tariff  int(5) not null,
    constraint id_UNIQUE
        unique (id),
    constraint bill_order_id_fk
        foreign key (`order`) references `order` (id)
            on update cascade on delete cascade,
    constraint bill_tariff_id_fk
        foreign key (tariff) references tariff (id)
            on update cascade on delete cascade
);

alter table bill
    add primary key(id);


