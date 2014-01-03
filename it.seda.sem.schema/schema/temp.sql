


DELETE FROM SIGNON;
DELETE FROM USERS;
DELETE FROM GROUP_MEMBERS;
DELETE FROM GROUP_AUTHORITIES;
DELETE FROM GROUPS;

insert into groups(group_name,group_description) values ('users','users group');
insert into groups(group_name,group_description) values ('administrators','adminitrators group');

insert into group_authorities(group_id, authority)
select id,'ROLE_USER' from groups where group_name='users';

insert into group_authorities(group_id, authority)
select id,'ROLE_USER' from groups where
group_name='administrators';
insert into group_authorities(group_id, authority)
select id,'ROLE_ADMIN' from groups where
group_name='administrators';


ALTER TABLE CLIENTE RENAME COLUMN REGITRAZIONE TO REGISTRAZIONE



SELECT * FROM USERS
SELECT COUNT(USERS.USERNAME)
       FROM USERS
       WHERE USERS.USERNAME!='admin'
       


CREATE TABLE SOCIETA (
		id bigint generated by default as identity(start with 0),
		nome VARCHAR(64) NOT NULL UNIQUE,
		descrizione VARCHAR(256)  WITH DEFAULT,
		constraint pk_societa primary key(id)
	);
CREATE UNIQUE INDEX societa_uidx ON server (nome ASC);


insert into societa (nome, descrizione)
select old.nome, old.descrizione from sem.responsabili old;
       