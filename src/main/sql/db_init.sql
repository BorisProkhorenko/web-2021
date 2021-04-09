create
database hr_database;

SET
GLOBAL time_zone = '+0:00';

create table User
(
    id         bigint primary key,
    username   varchar(45),
    password   varchar(255),
    role       enum("Admin", "HR", "Applicant"),
    is_blocked boolean,
    name       varchar(45),
    gender     enum("male","female"),
    age        int,
    photo      varchar(255),
    contacts   varchar(1000),
    education  varchar(1000),
    experience varchar(1000),
    skills     varchar(1000)
);

create table Vacancy
(
    id             bigint primary key,
    name           varchar(255),
    salary         varchar(50),
    description    varchar(1000),
    responsibility varchar(1000),
    requirements   varchar(1000)
);

create table User_Vacancy
(
    user_id            bigint,
    vacancy_id         bigint,
    state              enum("New", "Preliminary", "Technical", "Hired", "Rejected" ),
    preliminary_points int,
    primary key (vacancy_id, user_id)
);

create table Response
(
    id         bigint primary key,
    date       timestamp DEFAULT CURRENT_TIMESTAMP,
    subject    varchar(255),
    details    varchar(1000),
    user_id    bigint,
    vacancy_id bigint
);

alter table user_vacancy
    add foreign key (vacancy_id) references vacancy (id),
add foreign key(user_id) references user(id);

alter table response
    add foreign key (vacancy_id) references user_vacancy (vacancy_id),
add foreign key(user_id) references user_vacancy(user_id);

insert into user(id, username, password, role, is_blocked)
values (1, 'admin', md5('admin'), 'admin', false);

insert into vacancy(id, name, salary, responsibility, description, requirements)
values (1, 'Java Developer', '-', 'responsibility', 'Description',
        'We are looking for engineers who are motivated and can take ownership to drive innovative features with an emphasis on quality. Strong technical expertise in Java;');
insert into vacancy(id, name, salary, responsibility, description, requirements)
values (2, 'Python Developer', '3000-4000$', 'responsibility', 'Description',
        'Good English communication skills. 4+ years of experience in web Back-end development Strong fundamental programming skills such as solid coding standard, design patterns.');

insert into vacancy(id, name, salary, responsibility, description, requirements)
values (3, 'GUI Designer', '1500$', 'responsibility', 'Description',
        'Bachelor with UX/Visual Design, Human-Computer Interaction, Industrial Design or similar degrees preferred A minimum of 3 years of professional experience working in cross-disciplinary teams.');

insert into vacancy(id, name, salary, responsibility, description, requirements)
values (4, 'Salesforce Consultant', '-', 'responsibility', 'Description',
        'excellent communication skills and proficiency in both English and Russian (C1 level)');

insert into vacancy(id, name, salary, responsibility, description, requirements)
values (5, 'HR Coordinator', '1500$', 'responsibility', 'Description',
        'Proven experience as an HR coordinator or relevant human resources position');

insert into user_vacancy(user_id, vacancy_id, state, preliminary_points)
values (2, 1, 'New', 100);

insert into response(id, subject, details, vacancy_id, user_id)
values (1, 'Some offer', 'details', 1, 2);