insert into Users values 
(default, 'Vegard Alvsaker', 'vegard@uia.no', default, 'pw123'),
(default, 'Hallgeir Hallgeirsen', 'Hallgeir@uia.no', 1, 'pw123'),
(default, 'Even Evensen', 'Even@uia.no', 1, 'pw123'),
(default, 'Shivan Shivansen', 'Shivan@uia.no', 1, 'pw123'),
(default, 'Eirik Eiriksen', 'Eirik@uia.no', 1, 'pw123'),
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
(default, '2', default, 'Velkommen', 'Velkommen til årets første forelesning 15.08'),
(default, '3', default, 'Lab', 'Husk å komme på lab hvis dere sliter');

insert into Module values
(default, 'Modul 6', 'Standardbiblioteket og mer avansert funksjonalitet', 'I denne modulen skal du lære å bruke flere av klassene i standardbiblioteket, og å finne fram i dokumentasjonen slik at du utnytter biblioteket best mulig.', 1, false),
(default, 'Modul 7', 'Arrayer', 'I denne modulen skal du lære å bruke arrayer, eller fixed-size collections som de blir kalt i boka.', 1, true),
(default, 'Modul 8', 'Design Classes', 'I denne modulen skal du lære mer om design av klasser, spesielt hva som kjennetegner gode og dårlige design. Du skal også lære om hvordan du kan forbedre designet til et system uten å forandre funksjonaliteten (refactoring).', 1, false),
(default, 'Modul 9', 'Testing og Debugging', 'Du skal lære å lage enhetstester, som sjekker om hver enkelt klasse gjør det den skal, og å gjøre kodegjennomganger. Du skal også lære noen teknikker for å finne feil.', 1, true),
(default, 'Modul 10', 'Arv og Subklasser I', 'I denne modulen skal du lære om arv og subklasser. Arv gjør det mulig å gjenbruke kode på en strukturert måte, og kanskje enda viktigere: Det gjør det mulig å modellere komplekse fenomener uten at koden blir altfor komplisert.', 1, true),
(default, 'Modul 11', 'Arv og Subklasser II', 'Denne modulen tar opp mer avanserte sider ved arv og subklasser. Det kan f.eks. være ønskelig, og noen ganger helt nødvendig, å kunne omdefinere metoder som er arvet fra superklassen. Det gir oss et par nye problemstillinger: Hvordan vet vi at det er den riktige metoden som blir kalt? I noen tilfeller trenger vi tilgang til både den omdefinerte, og den opprinnelige metoden. Hva gjør vi da? Denne modulen gir deg svar på disse spørsmålene', 1, true),
(default, 'Modul 12', 'Abstrakte klasser og Interface', 'Noen ganger vet vi at alle subklasser må ha en bestemt metode, men vi har ikke noe fornuftig denne metoden kan gjøre i superklassen. Et eksempel på dette er utskriftsmetoden i eksemplene til modul 10 og 11.\nDet er her abstrakte klasser og interface kommer inn. De gjør det mulig å definere abstrakte metoder, som bare består av en header. Dvs. at de definerer signaturen til en eller flere metoder, men ikke hvordan metoden gjør det den skal gjøre.', 1, true),
(default, 'Modul 14', 'Feilhåndtering', 'Det oppstår før eller siden en feilsituasjon i nesten alle programmer. I denne modulen skal du lære hvordan du kan flagge at en feilsituasjon har oppstått, og hvordan du kan håndtere situasjonen.', 1, true);


insert into Notification values
(default, 1, 'Møt på rektors kontor', default, default),
(default, 1, 'Ingen forelesning i dag', default, default),
(default, 2, 'Møt på rektors kontor', default, default),
(default, 2, 'Møt på rektors kontor', default, default);

insert into LearningGoal values
##(learn_goal_id, learn_goal_text, learn_goal_points, module_id)
(default, 'Bruke klasser fra standardbiblioteket i din egen kode', 10, 1),
(default, 'Kan finne, lese og forstå dokumentasjonen for klassene i biblioteket. Du skal minst kunne bruke klassene String, Random, HashMap, HashSet og Iterator.', 10, 1),
(default, 'Kan skrive dokumentasjon for egne klasser, som gjør det mulig for andre å bruke klassene', 10, 1),
(default, 'Skjønne "Information hiding", og bruken av private og public', 10, 1),
(default, 'Kan demonstrere bruk av Random, Iterator, static, final', 10, 1),

(default, 'Gjøre rede for når du bør bruke ArrayList, og når det er mer hensiktsmessig å bruke arrayer.', 10, 2),
(default, 'Foreta fornuftige valg mellom ArrayList og array i egen kode', 10, 2),
(default, 'Kan bruke vanlig/tradisjonell for-løkke', 10, 2),

(default, 'Kan forklare code duplication', 10, 3),
(default, 'Kan forklare cohesion', 10, 3),
(default, 'Kan forklare coupling', 10, 3),
(default, 'Kan forklare encapsulation', 10, 3),

(default, 'Kan lage og kjøre enhetstester i BlueJ', 10, 4),
(default, 'Kan gjennomføre en manuell kodegjennomgang', 10, 4),
(default, 'Kan bruke utskrifter/logging til å finne feil', 10, 4),
(default, 'Kan bruke assert-setninger til å bekrefte at programmeet virker som det skal', 10, 4),

(default, 'kjenner betydningen av begrepene arv, superklasse, subklasse, subtype og substitusjon', 10, 5),
(default, 'kan lage en subklasse av en annen klasse', 10, 5),
(default, 'kan bruke superklassens constructor', 10, 5),
(default, 'vet hvordan du bruker metoder og felt som er arvet fra superklassen', 10, 5),

(default, 'Kan omdefinere metoder som er arvet fra en superklasse', 10, 6),
(default, 'Kan kalle den opprinnelige metoden når det er nødvendig', 10, 6),
(default, 'Kan kalle constructoren til superklassen.', 10, 6),
(default, 'Kan forklare forskjellen på statisk og dynamisk type', 10, 6);

insert into Comments values
(default, 1, 1, default, 'Jeg skjønner ikke helt denne.'),
(default, 1, 3, default, 'Dette kan vi ta opp i en forelesning'),
(default, 2, 2, default, 'Kanskje litt dårlig formulert læringsmål'),
(default, 2, 6, default, 'Dette var mer forståelig etter å ha lest kapittelet i boka'),
(default, 3, 7, default, 'Kan noen forklare meg ArrayLists?');

insert into CommentReply values
(default, 1, 4, default, 'Vi kan møtes for å finne ut av dette :)'),
(default, 1, 3, default, 'Dere finner ut av det'),
(default, 4, 5, default, 'Enig!'),
(default, 4, 4, default, 'Så klart...'),
(default, 3, 2, default, 'Har rettet opp i det nå');

insert into Worklist values
(1, 2),  ##Hallgeir
(2, 3),  ##Even
(3, 4),  ##Shivan
(4, 5);  ##Eirik

insert into Delivery values
(default, 6, 1, 'https://www.youtube.com/watch?v=fiZQjQd-D1o', 1, default, 1),
(default, 6, 2, '', 1, default, 1),   ##!Evaluated
(default, 6, 3, 'https://www.youtube.com/watch?v=fiZQjQd-D1o', 2, default, 1),

(default, 7, 1, 'https://www.youtube.com/watch?v=fiZQjQd-D1o', 2, default, 1),
(default, 7, 2, '', 2, default, 1),
(default, 7, 3, 'https://www.youtube.com/watch?v=fiZQjQd-D1o', 2, default, 1),
(default, 7, 4, '', 2, default, 1),

(default, 8, 1, 'https://www.youtube.com/watch?v=fiZQjQd-D1o', 2, default, 1),
(default, 8, 2, '', 2, default, 1),
(default, 8, 3, 'https://www.youtube.com/watch?v=fiZQjQd-D1o', 2, default, 1),
(default, 8, 4, '', 2, default, 0),
(default, 8, 5, '', 1, default, 0);    

insert into Evaluation values
## evaluation_id, teacher_id integer, delivery_id, evaluation_comment, evaluation_isPublished,
(default, 2, 1, 'Veldig bra', 1),
(default, 3, 2, 'Veldig bra', 1),
(default, 4, 3, 'Veldig bra', 1),

(default, 5, 4, 'Veldig bra', 1),
(default, 5, 5, 'Veldig bra', 1),  
(default, 5, 6, 'Veldig bra', 1),
(default, 5, 7, 'Veldig bra', 1),

(default, 5, 8, 'Veldig bra', 1),  
(default, 5, 9, 'Veldig bra', 1),  
(default, 5, 10, 'Veldig bra', 1);           ##!Published

insert into Score values
##(score_id, learn_goal_id, score_points, evaluation_id)
(default, 1, 10, 1), #Modul 6
(default, 2, 10, 1),
(default, 3, 10, 1),
(default, 4, 10, 1),
(default, 5, 10, 1),

(default, 1, 10, 4),
(default, 2, 10, 4),
(default, 3, 10, 4),
(default, 4, 10, 4),
(default, 5, 10, 4),

(default, 1, 10, 8),
(default, 2, 10, 8),
(default, 3, 10, 8),
(default, 4, 10, 8),
(default, 5, 10, 8),


(default, 6, 10, 2),  #Modul 7
(default, 7, 10, 2),  
(default, 8, 10, 2),

(default, 6, 10, 5),  
(default, 7, 10, 5),  
(default, 8, 10, 5),

(default, 6, 10, 9),  
(default, 7, 10, 9),  
(default, 8, 10, 9),

(default, 9, 10, 3),  #Modul 8
(default, 10, 10, 3),  
(default, 11, 10, 3),
(default, 12, 10, 3),

(default, 9, 10, 6),  
(default, 10, 10, 6),  
(default, 11, 10, 6),
(default, 12, 10, 6),

(default, 9, 10, 10),  
(default, 10, 10, 10),  
(default, 11, 10, 10),
(default, 12, 10, 10),

(default, 13, 10, 7),  #Modul 9
(default, 14, 10, 7),  
(default, 15, 10, 7),
(default, 16, 10, 7),
(default, 17, 10, 7);


























