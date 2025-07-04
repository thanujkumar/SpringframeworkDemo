-- for h2 database
DROP TABLE IF EXISTS stocks;

CREATE TABLE stocks
(
    id    varchar(255) not null ,
    name  varchar(255) not null ,
    price double not null
);