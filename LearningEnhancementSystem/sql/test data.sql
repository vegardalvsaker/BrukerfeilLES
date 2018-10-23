insert into Roles values 
('vegard@uia.no', default), 
('Hallgeir@uia.no', 'Teacher'), 
('Even@uia.no', 'Teacher'), 
('Shivan@uia.no', 'Teacher'), 
('Eirik@uia.no', 'Teacher'), 
('ingve@uia.no', default), 
('espen@uia.no', default), 
('gorm-erik@uia.no', default);


create table Users(
user_id integer not null auto_increment,
user_name varchar(60),
user_email varchar (70),
user_isTeacher bool not null default 0,
user_password varchar(20),

constraint user_pk primary key (user_id)
);


insert into Users values 
(default, 'Vegard Alvsaker', 'vegard@uia.no', default, sha2(vegarda)