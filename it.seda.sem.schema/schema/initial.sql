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

-- Map the users to Groups
-- at application startup