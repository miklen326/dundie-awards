insert into organizations (name) values ('Pikashu');
insert into organizations (name) values ('Squanchy');

insert into employees (first_name,last_name,organization_id) values ('John', 'Doe', select id from organizations where name = 'Pikashu');
insert into employees (first_name,last_name,organization_id) values ('Jane', 'Smith', select id from organizations where name = 'Pikashu');
insert into employees (first_name,last_name,organization_id) values ('Creed', 'Braton', select id from organizations where name = 'Pikashu');

insert into employees (first_name,last_name,organization_id) values ('Michael', 'Scott', select id from organizations where name = 'Squanchy');
insert into employees (first_name,last_name,organization_id) values ('Dwight', 'Schrute', select id from organizations where name = 'Squanchy');
insert into employees (first_name,last_name,organization_id) values ('Jim', 'Halpert', select id from organizations where name = 'Squanchy');
insert into employees (first_name,last_name,organization_id) values ('Pam', 'Beesley', select id from organizations where name = 'Squanchy');

