insert into user (id, name, surname, password, phone, email, role) values (1, 'Morgen', 'Wealleans', 'MGsLW6mbazzs', '+46-519-735-0229', 'mwealleans0@godaddy.com', false);
insert into user (id, name, surname, password, phone, email, role) values (2, 'Tiler', 'Mettrick', 'JGG5Xqt1Z9', '+46-588-123-3461', 'tmettrick1@blinklist.com', false);
insert into user (id, name, surname, password, phone, email, role) values (3, 'Pietrek', 'Bernardotte', 'czr2BtexHFIx', '+62-623-102-9202', 'pbernardotte2@cbslocal.com', true);
insert into user (id, name, surname, password, phone, email, role) values (4, 'Daffy', 'Allsupp', '80GHDl7Vu1', '+7-774-424-4454', 'dallsupp3@tumblr.com', true);
insert into user (id, name, surname, password, phone, email, role) values (5, 'Jere', 'Gotobed', 'OmEO60M', '+55-981-658-2640', 'jgotobed4@ca.gov', true);
insert into user (id, name, surname, password, phone, email, role) values (6, 'Selene', 'McBayne', 'GjmfFsOrm', '+64-223-976-2100', 'smcbayne5@dailymail.co.uk', true);
insert into user (id, name, surname, password, phone, email, role) values (7, 'Godard', 'Sibbald', 'lHv4s7R', '+1-973-866-7238', 'gsibbald6@51.la', false);
insert into user (id, name, surname, password, phone, email, role) values (8, 'Claudelle', 'Seys', 'M6FWOwahilPC', '+355-359-352-2578', 'cseys7@washingtonpost.com', true);
insert into user (id, name, surname, password, phone, email, role) values (9, 'Wolfgang', 'Dudderidge', 'pT1L3WLtx', '+996-687-976-5443', 'wdudderidge8@skype.com', true);
insert into user (id, name, surname, password, phone, email, role) values (10, 'Robin', 'BoHlingolsen', 'gs0tfm3UtRZ8', '+62-826-764-1702', 'rbohlingolsen9@shareasale.com', true);
insert into user (id, name, surname, password, phone, email, role) values (11, 'Terri-jo', 'Stuke', 'kkd2Hq', '+255-550-334-3224', 'tstukea@list-manage.com', true);
insert into user (id, name, surname, password, phone, email, role) values (12, 'Nealson', 'Sauvain', 'zzWdPgziCKt', '+351-566-623-2647', 'nsauvainb@google.com.au', true);
insert into user (id, name, surname, password, phone, email, role) values (13, 'Dorolisa', 'Boyall', 'Ocnx9Bts0x', '+226-889-169-2289', 'dboyallc@e-recht24.de', true);
insert into user (id, name, surname, password, phone, email, role) values (14, 'Dacie', 'Le Count', 'ovEBgI2Vjx', '+86-312-464-7029', 'dlecountd@exblog.jp', false);
insert into user (id, name, surname, password, phone, email, role) values (15, 'Evie', 'McKitterick', 'gxT0lAuV', '+86-552-164-3845', 'emckittericke@intel.com', false);
insert into user (id, name, surname, password, phone, email, role) values (16, 'Tracey', 'Bredbury', 'yEmqIuobt6v', '+227-587-771-3574', 'tbredburyf@simplemachines.org', true);
insert into user (id, name, surname, password, phone, email, role) values (17, 'Jacquenette', 'Really', 'BvycRug', '+66-404-799-5277', 'jreallyg@uiuc.edu', false);
insert into user (id, name, surname, password, phone, email, role) values (18, 'Colette', 'Toffolo', 'XBcqC8', '+351-884-687-1183', 'ctoffoloh@ca.gov', false);
insert into user (id, name, surname, password, phone, email, role) values (19, 'Anthea', 'Colliver', 'pLsfdVz', '+1-812-676-5935', 'acolliveri@cnn.com', false);
insert into user (id, name, surname, password, phone, email, role) values (20, 'Amelina', 'Elcome', 'Shn5YpGN6', '+995-707-111-9656', 'aelcomej@illinois.edu', false);

insert into weight(name, coefficient) values ('HEAVY', 1.2);
insert into weight(name, coefficient) values ('MEDIUM', 1.4);
insert into weight(name, coefficient) values ('ENORMOUS', 2.1);
insert into weight(name, coefficient) values ('LIGHT', 1.0);



insert into country(id, name) values (1, 'Ukraine');
insert into country(id, name) values (2, 'Poland');
insert into country(id, name) values (3, 'England');

insert into region(id, name , country) values (1, 'Osad', 1);
insert into region(id, name , country) values (2, 'Darad', 3);
insert into region(id, name , country) values (3, 'Lombok', 2);
insert into region(id, name , country) values (4, 'Mikar', 2);

insert into town(id, name, region) values (1, 'Lviv', 1);
insert into town(id, name, region) values (2, 'Oslo', 2);
insert into town(id, name, region) values (3, 'Warsaw', 4);
insert into town(id, name, region) values (4, 'Lublin', 3);


INSERT INTO status (name) VALUES ('ACTIVE');
INSERT INTO status (name) VALUES ( 'CANCELED');
INSERT INTO status (name) VALUES ('NEW');


INSERT INTO  size (name , coefficient) values ('SMALL', 1.1);
INSERT INTO  size (name , coefficient) values ('MEDIUM', 1.2);
INSERT INTO  size (name , coefficient) values ('BIG', 1.3);

INSERT INTO  material (name , coefficient) values ('GLASS', 1.1);
INSERT INTO  material (name , coefficient) values ('STANDARD', 1.2);


INSERT INTO  delivery (name , price) values ('TOWN', 10);
INSERT INTO  delivery (name , price) values ('REGION', 30);
INSERT INTO  delivery (name , price) values ('COUNTRY', 90);

INSERT INTO orders(id,user,destination,status,address) values (1, 1, 1, 'ACTIVE', 'lime');
INSERT INTO orders(id,user,destination,status,address) values (2, 3, 2, 'NEW', 'cherry');

INSERT INTO  shipment(id, weight, height, width, length, orders) values (1, 1.23, 2.12, 1.1 , 0.2, 1);
INSERT INTO  shipment(id, weight, height, width, length, orders) values (2, 0.3, 2.12, 0.45 , 3.2, 2);

INSERT INTO bill(id, shipment, payment, delivery, size, weight, material) values (1, 1, true, 'TOWN', 'SMALL', 'HEAVY', 'GLASS');
INSERT INTO bill(id, shipment, payment, delivery, size, weight, material) values (2, 2, false, 'COUNTRY', 'BIG', 'MEDIUM', 'STANDARD');