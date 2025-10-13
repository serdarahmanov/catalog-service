CREATE TABLE book
(
    id                 Bigserial primary key not null,
    author             varchar(255)          not null,
    isbn               varchar(255) unique   not null,
    title              varchar(255)          not null,
    created_date       timestamp             not null,
    last_modified_date timestamp             not null,
    price              float                 not null,
    version            integer               not null
);