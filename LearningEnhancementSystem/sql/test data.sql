use learningenhancementsystem;
## drop schema learningenhancementsystem;

insert into Users values 
(default, 'Vegard Alvsaker', 'vegard@uia.no', default, 'pw123'),
(default, 'Hallgeir Hallgeirsen', 'Hallgeir@uia.no', 1, 'pw123'),
(default, 'Even Fallkurs', 'Even@uia.no', 1, 'pw123'),
(default, 'Shivan Realboi', 'Shivan@uia.no', 1, 'pw123'),
(default, 'Eirik SnakkeItimen', 'Eirik@uia.no', 1, 'pw123'),
(default, 'Ingve Fosse', 'ingve@uia.no', default, 'pw123'),
(default, 'Espen Oftedal', 'espen@uia.no', default, 'pw123'),
(default, 'Gorm-Erik Aarseheim', 'gorm-erik@uia.no', default, 'pw123');

insert into Roles values 
('vegard@uia.no', default), 
('Hallgeir@uia.no', 'Teacher'), 
('Even@uia.no', 'Teacher'), 
('Shivan@uia.no', 'Teacher'), 
('Eirik@uia.no', 'Teacher'), 
('ingve@uia.no', default), 
('espen@uia.no', default), 
('gorm-erik@uia.no', default);

insert into Announcement values
(default, '2', default, 'Subject TEST Hallgeir', 'Body TEST Hallgeir'),
(default, '3', default, 'Subject TEST Even', 'Body TEST Even'),
(default, '4', default, 'Subject TEST Shivan', 'Body TEST Shivan'),
(default, '5', default, 'Subject TEST Eirik', 'Body TEST Eirik');

insert into Module values
(default, 'Modul 1', 'Module 1 description', 'Module 1 content is very long', 1),
(default, 'Modul 2', 'Module 2 description', 'Module 2 content is very long', 1),
(default, 'Modul 3', 'Module 3 description', 'Module 3 content is very long', 0),
(default, 'Modul 4', 'Module 4 description', 'Module 4 content is very long', 0);

insert into LearningGoal values
##(learn_goal_id, learn_goal_text, learn_goal_points, module_id)
(default, 'To complete learninggoal 1 you have to ~~', 10, 1),
(default, 'To complete learninggoal 2 you have to ~~', 10, 1),
(default, 'To complete learninggoal 3 you have to ~~', 10, 2),
(default, 'To complete learninggoal 4 you have to ~~', 10, 3),
(default, 'To complete learninggoal 5 you have to ~~', 10, 4);

insert into Comments values
(default, 1, 1, default, 'This is Vegards(1) comment on module 1'),
(default, 1, 3, default, 'This is Evens(3) comment on module 1'),
(default, 2, 2, default, 'This is Hallgeirs(2) comment on module 2'),
(default, 2, 6, default, 'This is Ingves(6) comment on module 2'),
(default, 3, 7, default, 'This is Espens(7) comment on module 3');

insert into CommentReply values
(default, 1, 4, default, 'This is Shivans(4) response to Vegards comment(1) on module 1'),
(default, 1, 3, default, 'This is Evens(3) response to Vegards comment(1) on module 1'),
(default, 4, 5, default, 'This is Eiriks(5) response to Ingves comment(4) on module 2'),
(default, 4, 4, default, 'This is Shivans(4) response to Ingves comment(4) on module 2'),
(default, 3, 2, default, 'This is Hallgeirs(2) response to Hallgeirs comment(3) on module 2');

insert into Worklist values
(1, 2),  ##Hallgeir
(2, 3),  ##Even
(3, 4),  ##Shivan
(4, 5);  ##Eirik

insert into Delivery values
(default, 1, 1, 'Vegards Delivery content for module 1', 1, default, 1),
(default, 6, 1, 'Ingves Delivery content for module 1', 1, default, 0),   ##!Evaluated
(default, 7, 2, 'Espens Delivery content for module 2', 2, default, 1),
(default, 8, 3, 'Gorms Delivery content for module 3', 2, default, 0);    ##!Evaluated

insert into Evaluation values
## evaluation_id, teacher_id integer, delivery_id, evaluation_comment, evaluation_isPublished,
(default, 2, 1, 'Hallgeirs evaluation of Vegards delivery module 1', 1),
(default, 3, 2, 'Evens evaluation of Ingves delivery module 1', 1),
(default, 4, 3, 'Shivans evaluation of Espens delivery module 2', 0),     ##!Published
(default, 5, 4, 'Eiriks evaluation of Gorms delivery module 3', 0);       ##!Published

insert into Score values
##(score_id, learn_goal_id, score_points, evaluation_id)
(default, 1, 10, 1),  ##Hallgeir - Vegard Modul 1
(default, 2, 10, 2),  ##Even     - Ingve  Modul 1
(default, 3, 10, 3),  ##Shivan   - Espen  Modul 2
(default, 4, 10, 4);  ##Erik     - Gorm   Modul 3



























