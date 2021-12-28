create table products(
                         product_id INTEGER AUTO_INCREMENT,
                         product_name VARCHAR(50),
                         product_desc VARCHAR(100),
                         unit INTEGER,
                         price DOUBLE,
                         primary key (product_id)
);

insert into products(product_name, product_desc, unit, price) values ('apple','apple cell from db',10,325.12);
insert into products(product_name, product_desc, unit, price) values ('dell','dell cell from db',30,625.12);
