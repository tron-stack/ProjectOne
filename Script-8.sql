create table users (
	user_id int primary key generated always as identity,
	username varchar(128) unique not null,
	password varchar(128) not null,
	first_name varchar(128),
	last_name varchar(128),
	email varchar(128) unique not null,
	role int references user_roles(role_id)
);

create table user_roles (
	role_id int primary key generated always as identity,
	role varchar(128) unique
);

create table reimbursement_type(
	type_id int primary key generated always as identity,
	type varchar(64)
);

create table reimbursement_status(
	status_id int primary key generated always as identity,
	status varchar(128)
);

create table reimbursement (
	reimbursement_id int primary key generated always as identity,
	amount numeric,
	submitted_date date,
	resolved_date date,
	description varchar(256),
	reimbursement_author int references users(user_id),
	reimbursement_resolver int references users(user_id),
	reimbursement_type int references reimbursement_type(type_id),
	reimbursement_status int references reimbursement_status(status_id)
);



drop table user_roles;
drop table users;


insert into user_roles (role) values ('employee');
insert into user_roles (role) values ('manager');
insert into reimbursement_type (type) values ('LODGING');
insert into reimbursement_type (type) values ('TRAVEL');
insert into reimbursement_type (type) values ('FOOD');
insert into reimbursement_type (type) values ('OTHER');
insert into reimbursement_status (status) values ('PENDING');
insert into reimbursement_status (status) values ('APPROVED');
insert into reimbursement_status (status) values ('DENIED');


select * from reimbursement_status rs ;
select * from reimbursement_type rt ;
select * from user_roles ur ;
