-- Create the Groups
insert into groups(group_name,group_description) values ('users','users group');
insert into groups(group_name,group_description) values ('administrators','adminitrators group');
-- Map the Groups to Roles
insert into group_authorities(group_id, authority)
select id,'ROLE_USER' from groups where group_name='users';

insert into group_authorities(group_id, authority)
select id,'ROLE_USER' from groups where
group_name='administrators';
insert into group_authorities(group_id, authority)
select id,'ROLE_ADMIN' from groups where
group_name='administrators';

insert into customers(id, customer_name, customer_code) values ('SEDA','Seda spa Gruppo KGS','SEDA');
insert into applications(id, application_name) values ('SCAS','Seda Servizi di Autenticatione Centralizzata');
insert into customer_applications (customer_id, application_id) values ('SEDA','SCAS');
-- insert into customer_users (customer_id, username) values ('SEDA','admin');
-- Map the users to Groups
-- at application startup