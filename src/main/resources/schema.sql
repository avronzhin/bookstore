create table if not exists Book (
    id identity,
    name varchar not null,
    author varchar not null,
    publish_year int not null
);

create table if not exists Genre (
    id varchar,
    title varchar
);

create table if not exists Genre_Ref (
    book_id int,
    genre_id varchar
);