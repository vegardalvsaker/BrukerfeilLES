create schema moduleevaluation;

use moduleevaluation;

create table Module(
module_id integer not null primary key,
module_name varchar(100),
module_description text
);

create table Student(
student_id integer not null primary key,
student_name varchar(50) 
);

create table Evaluation(
evaluation_id integer not null primary key auto_increment,
student_id integer,
module_id integer not null,

constraint evaluation_fk_1 foreign key (module_id) references Module (module_id),
constraint evaluation_fk_2 foreign key (student_id) references Student (student_id)
);

create table LearningGoal(
learn_goal_id integer not null primary key,
goaltext text,
points numeric(1) not null,
module_id integer,

constraint learningGoal_fk foreign key (module_id) references Module (module_id)
);

create table LearningGoalPoints(
lgp_id integer not null primary key,
learn_goal_id integer,
points numeric(1) not null,
evaluation_id integer,

constraint learning_goal_points_1 foreign key (learn_goal_id) references LearningGoal (learn_goal_id),
constraint learning_goal_points_2 foreign key (evaluation_id) references Evaluation (evaluation_id)
);

alter table Evaluation add unique unique_index (student_id, module_id);
select * from Student;

insert into Student
values (1001,'Vegard Alvsaker'), (1002, 'Ingve Fosse'), (1003, 'Espen Oftedal');

insert into Module
values (1, 'Module 6', 'Installing NetBeans and TomCat'), (2, 'Module 7','Looping with for each and while loops'), (3, 'Module 8','Create HashMaps'), (4, 'Module 9', 'Create strings');

insert into Evaluation
values (3, 1001, 1);

insert into LearningGoal
values (101, 'Can show that you have installed NetBeans', 2, 1), (102, 'Can show that you have installed TomCat', 2, 1),
(103, 'Can show how a for each loop works', 2, 2), (104, 'Can show how a while loop works.', 2, 2),
(105, 'Can import a hashmap', 2, 3), (106, 'Can loop a hashmap', 2, 3),
(107, 'Can declare a string variable', 2, 4), (108, 'Can print a string', 2, 4);

select goaltext, points from LearningGoal
where module_id = 1;

select * from Module;

delete from Module
where module_id = 10;

