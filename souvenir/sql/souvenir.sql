drop database if exists souvenir_store;

create database souvenir_store;

use souvenir_store;

create table user_table(
	`id` int primary key auto_increment,
	`username` varchar(20) not null unique,
	`password` varchar(32) not null,
	`email` varchar(200)
);

insert into user_table(`username`,`password`,`email`) values('admin','admin','admin@souvenirstore.com');

select * from user_table;

create table souvenir_table(
	`id` int primary key auto_increment,
	`name` varchar(100),
	`price` decimal(11,2),
	`sales` int,
	`img_path` varchar(200)
);

insert into souvenir_table(`id` , `name` , `price` , `sales` , `img_path`)
values(null , 'Souvenir Q' , 12.8 , 182 , 'static/img/default.jpg');

insert into souvenir_table(`id` , `name` , `price` , `sales` , `img_path`)
values(null , 'Souvenir W' , 8.7 , 218 , 'static/img/default.jpg');

insert into souvenir_table(`id` , `name` , `price` , `sales` , `img_path`)
values(null , 'Souvenir E' , 16.2 , 165 , 'static/img/default.jpg');

insert into souvenir_table(`id` , `name` , `price` , `sales` , `img_path`)
values(null , 'Souvenir R' , 7.6 , 191 , 'static/img/default.jpg');

insert into souvenir_table(`id` , `name` , `price` , `sales` , `img_path`)
values(null , 'Souvenir T' , 21.6 , 103 , 'static/img/default.jpg');

insert into souvenir_table(`id` , `name` , `price` , `sales` , `img_path`)
values(null , 'Souvenir Y' , 33.5 , 59 , 'static/img/default.jpg');

insert into souvenir_table(`id` , `name` , `price` , `sales` , `img_path`)
values(null , 'Souvenir U' , 19.2 , 156 , 'static/img/default.jpg');

insert into souvenir_table(`id` , `name` , `price` , `sales` , `img_path`)
values(null , 'Souvenir I' , 28.5 , 97 , 'static/img/default.jpg');

insert into souvenir_table(`id` , `name` , `price` , `sales` , `img_path`)
values(null , 'Souvenir O' , 9.2 , 175 , 'static/img/default.jpg');

insert into souvenir_table(`id` , `name` , `price` , `sales` , `img_path`)
values(null , 'Souvenir P' , 11.7 , 158 , 'static/img/default.jpg');

insert into souvenir_table(`id` , `name` , `price` , `sales` , `img_path`)
values(null , 'Souvenir A' , 18.6 , 179 , 'static/img/default.jpg');

insert into souvenir_table(`id` , `name` , `price` , `sales` , `img_path`)
values(null , 'Souvenir S' , 19.6 , 155 , 'static/img/default.jpg');

insert into souvenir_table(`id` , `name` , `price` , `sales` , `img_path`)
values(null , 'Souvenir D' , 39.3 , 43 , 'static/img/default.jpg');

insert into souvenir_table(`id` , `name` , `price` , `sales` , `img_path`)
values(null , 'Souvenir F' , 17.5 , 195 , 'static/img/default.jpg');

insert into souvenir_table(`id` , `name` , `price` , `sales` , `img_path`)
values(null , 'Souvenir G' , 29.5 , 92 , 'static/img/default.jpg');

insert into souvenir_table(`id` , `name` , `price` , `sales` , `img_path`)
values(null , 'Souvenir H' , 60 , 32 , 'static/img/default.jpg');

insert into souvenir_table(`id` , `name` , `price` , `sales` , `img_path`)
values(null , 'Souvenir J' , 15 , 170 , 'static/img/default.jpg');

insert into souvenir_table(`id` , `name` , `price` , `sales` , `img_path`)
values(null , 'Souvenir K' , 18.6 , 179 , 'static/img/default.jpg');

insert into souvenir_table(`id` , `name` , `price` , `sales` , `img_path`)
values(null , 'Souvenir L' , 93 , 26 , 'static/img/default.jpg');

insert into souvenir_table(`id` , `name` , `price` , `sales` , `img_path`)
values(null , 'Souvenir Z' , 168 , 18 , 'static/img/default.jpg');

insert into souvenir_table(`id` , `name` , `price` , `sales` , `img_path`)
values(null , 'Souvenir X' , 23.6 , 150 , 'static/img/default.jpg');

insert into souvenir_table(`id` , `name` , `price` , `sales` , `img_path`)
values(null , 'Souvenir C' , 3.6 , 433 , 'static/img/default.jpg');

insert into souvenir_table(`id` , `name` , `price` , `sales` , `img_path`)
values(null , 'Souvenir V' , 118.6 , 21 , 'static/img/default.jpg');

insert into souvenir_table(`id` , `name` , `price` , `sales` , `img_path`)
values(null , 'Souvenir B' , 59.5 , 37 , 'static/img/default.jpg');

insert into souvenir_table(`id` , `name` , `price` , `sales` , `img_path`)
values(null , 'Souvenir N' , 72.9 , 26 , 'static/img/default.jpg');

insert into souvenir_table(`id` , `name` , `price` , `sales` , `img_path`)
values(null , 'Souvenir M' , 36.3 , 67 , 'static/img/default.jpg');

select * from souvenir_table;

use souvenir_store;

create table order_table(
	`order_id` varchar(50) primary key,
	`create_time` date,
	`price` decimal(11,2),
	`status` int,
	`user_id` int,
	foreign key(`user_id`) references user_table(`id`)
);

select * from order_table;

create table order_item_table(
	`id` int primary key auto_increment,
	`name` varchar(100),
	`count` int,
	`price` decimal(11,2),
	`total_price` decimal(11,2),
	`order_id` varchar(50),
	foreign key(`order_id`) references order_table(`order_id`)
);

select * from order_item_table;
