CREATE TABLE c_security_profile(id integer primary key, name varchar(30) not null);
insert into c_security_profile (id, name) values
(0, 'SYSADMIN'),
(1, 'EMPLOYEE'),
(2, 'GUARDIAN');


INSERT INTO public.c_security_authority
("name", enable_in_academic, enable_in_pos, enable_in_printing)
VALUES('ROLE_WALISANTRI', false, false, false);
