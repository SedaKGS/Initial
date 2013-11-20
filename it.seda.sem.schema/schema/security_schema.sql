create table users (
	username varchar(128) not null,
	firstname varchar(64) not null,
	lastname varchar(64) not null,
	email varchar(128) not null,
	enabled smallint not null with default 1,
 
	registration date not null with default current date,
	expiration date not null,
	credentialsExpiration date not null with default '1900-01-01',
	
	locked smallint not null with default 0,
	attempts smallint not null with default 0,
	constraint pk_users primary key(username)
);

create table signon (
	password varchar(256) not null,
	username varchar(128) not null,
	constraint pk_sigon primary key(username),
	constraint fk_sigon_users foreign key(username) references users(username) on delete cascade	
);
create table groups (
	id bigint generated by default as identity(start with 0),
	group_name varchar(128) not null,
	group_description varchar(256) not null,
	constraint pk_groups primary key(id)
);

create table group_authorities (
	group_id bigint not null,
	authority varchar(256) not null,
	constraint pk_group_authorities primary key(group_id, authority),
	constraint fk_group_authorities_group foreign key(group_id) references groups(id) on delete cascade
);


create table group_members (
	username varchar(128) not null,
	group_id bigint not null,
	constraint pk_group_members primary key(username, group_id),	
	constraint fk_group_members_group foreign key(group_id) references groups(id) on delete cascade,
    constraint fk_group_members_users foreign key(username) references users(username) on delete cascade	
);

