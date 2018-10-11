create schema LearningEnhancementSystem;

use LearningEnhancementSystem;

create table Users(
user_id integer not null auto_increment,
user_name varchar(60),
user_email varchar (70),
user_isTeacher bool not null default 0,

constraint user_pk primary key (user_id)
);

insert into Users
values (1, 'Hallgeir', 'hallgeiren@uia.no', true),
(2, 'Kevin', 'kevins16@uia.no', false),
(3, 'Espen', 'Espens17@uia.no', false),
(4, 'Holen', 'Mariushoe16@uia.no', false);

create table Announcement(
ann_id integer not null auto_increment,
teacher_id integer,
ann_timestamp timestamp default current_timestamp,
ann_subject varchar(50) not null,
ann_body text,

constraint ann_pk primary key (ann_id),
constraint ann_fk foreign key (teacher_id) references Users (user_id)
);

create table Module(
module_id integer not null auto_increment,
module_name varchar(30) not null,
module_desc varchar(100),
module_content text,
module_isPublished bool default 0,

constraint module_pk primary key (module_id)
);

insert into Module
values (1, 'Module 1', 'Introduction to BlueJ', 'Save Project', true),
(2, 'Modul 2', 'Continued on blueJ', 'Open project', true),
(3, 'Modul 3', 'Last module', 'Make your own program' , false);

create table LearningGoal(
learn_goal_id integer auto_increment,
learn_goal_text text,
learn_goal_points integer,
module_id integer not null,

constraint learningGoal_pk primary key (learn_goal_id),
constraint learningGoal_fk foreign key (module_id) references Module (module_id)
);

create table Comments(
comment_id integer not null auto_increment,
module_id integer,
user_id integer,
comment_timestamp timestamp default current_timestamp,

constraint comment_pk primary key (comment_id),
constraint comment_fk_1 foreign key (module_id) references Module (module_id),
constraint comment_fk_2 foreign key (user_id) references Users (user_id)
);

create table CommentReply(
reply_id integer not null auto_increment,
comment_id integer,
user_id integer,
reply_timestamp timestamp default current_timestamp,

constraint reply_pk primary key (reply_id),
constraint reply_fk_1 foreign key (comment_id) references Comments (comment_id),
constraint reply_fk_2 foreign key (user_id) references Users (user_id)
);

create table Worklist(
worklist_id integer not null auto_increment,
teacher_id integer not null,

constraint worklist_pk primary key (worklist_id),
constraint worklist_fk foreign key (teacher_id) references Users (user_id)
);

insert into Worklist
values (1, 1), (2,1), (3,1);

create table Delivery(
delivery_id integer not null auto_increment,
student_id integer,
module_id integer,
delivery_content text,
worklist_id integer not null,
delivery_timestamp timestamp default current_timestamp,
<<<<<<< Updated upstream
delivery_isEvaluated boolean default false,
=======
delivery_isEvaluated boolean default 0,
>>>>>>> Stashed changes


constraint delivery_fk_1 foreign key (student_id) references Users (user_id),
constraint delivery_fk_2 foreign key (module_id) references Module (module_id),
constraint delivery_fk_3 foreign key (worklist_id) references Worklist (worklist_id),
constraint unique_student_and_module unique (student_id, module_id),

constraint delivery_pk primary key (delivery_id)
/*constraint delivery_pk primary key (student_id, module_no)*/
);

insert into Delivery (delivery_id, student_id, module_id, delivery_content, worklist_id, delivery_isEvaluated)
values (1, 2, 1, 'Solved task', 1, false), (2, 3, 2, 'Video hand in', 1, false), (3, 4, 3, 'the final one', 1, false);


create table Evaluation(
evaluation_id integer not null auto_increment,
teacher_id integer,
delivery_id integer unique , #En delivery kan kun ha en evaluation
evaluation_comment text,
evaluation_isPublished boolean default false,

constraint evaluation_fk_1 foreign key (teacher_id) references Users (user_id),
constraint evaluation_fk_2 foreign key (delivery_id) references Delivery (delivery_id),
constraint evaluation_pk primary key (evaluation_id)
);

create table Score(
score_id integer not null auto_increment,
learn_goal_id integer,
score_points integer,
evaluation_id integer,

constraint score_fk_1 foreign key (learn_goal_id) references LearningGoal (learn_goal_id),
constraint score_fk_2 foreign key (evaluation_id) references Evaluation (evaluation_id),
constraint score_pk primary key (score_id)
);

create table Inbox(
inbox_id integer not null auto_increment,
user_id integer,

constraint inbox_pk primary key (inbox_id),
constraint inbox_fk foreign key (user_id) references Users (user_id)
);

create table Message(
msg_id integer not null auto_increment,
msg_sender integer,
msg_receiver integer,
msg_subject varchar(50),
msg_text text,
msg_timestamp timestamp default current_timestamp,
msg_read boolean default 0,

constraint message_pk primary key (msg_id),
constraint message_fk_1 foreign key (msg_sender) references Users (user_id),
constraint message_fk_2 foreign key (msg_receiver) references Inbox (inbox_id)
);






