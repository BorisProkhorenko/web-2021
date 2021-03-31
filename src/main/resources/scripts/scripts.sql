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
    photo      varchar(50),
    contacts   varchar(50),
    education  varchar(255),
    experience varchar(255),
    skills     varchar(255)
);

create table Vacancy
(
    id             bigint primary key,
    name           varchar(45),
    salary         varchar(45),
    description    varchar(255),
    responsibility varchar(255),
    requirements   varchar(255)
);

create table User_Vacancy
(
    user_id            bigint,
    vacancy_id         bigint,
    state              enum("New", "Preliminary", "Technical", "Hired", "Rejected" ),
    preliminary_points int,
    technical_points   int,
    primary key (vacancy_id, user_id)
);

create table Response
(
    id         bigint primary key,
    date       timestamp,
    main       varchar(45),
    details    varchar(255),
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