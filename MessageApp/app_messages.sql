create database app_messages character set=utf8;

use app_messages;
drop table if exists `messages`;
create table `messages` (
	`id` int(11) not null auto_increment,
    `text` varchar(128) collate utf8_bin not null default '',
    `created_date` datetime not null default current_timestamp,
    primary key (`id`)
) engine=InnoDB default charset=utf8 collate=utf8_bin;

create user 'spring_vuejs'@'localhost' identified by 'spring_vuejs';
grant all privileges on app_messsages.* to 'spring_vuejs'@'localhost';