CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
insert into c_security_authority(name) VALUES
('SUPERADMIN'),
('HR'),
('TU'),
('PENDIDIKAN'),
('PENGASUHAN'),
('USTADZ'),
('KESEHATAN');

INSERT INTO c_security_user(
	id, created_by, created_date, activated, email, first_name, lang_key, last_name, login, password_hash, reset_date, reset_key)
VALUES('31e1bf78-0019-4fcd-9222-d928b709e346', 'system', now(), true, 'admin@alandalus.com', 'Superadmin', 'id', '', 'superadmin',
'$2a$10$YBKoQoq3YhlFJqRnPG2MuOCe5i0XUshdU7cxq/Mj3.Km9cjDFzmrm', now(), '77941475607411089176');
INSERT INTO c_security_user_authority(user_id, authority_name) VALUES
('31e1bf78-0019-4fcd-9222-d928b709e346', 'SUPERADMIN'),
('31e1bf78-0019-4fcd-9222-d928b709e346', 'HR'),
('31e1bf78-0019-4fcd-9222-d928b709e346', 'TU'),
('31e1bf78-0019-4fcd-9222-d928b709e346', 'PENDIDIKAN'),
('31e1bf78-0019-4fcd-9222-d928b709e346', 'PENGASUHAN'),
('31e1bf78-0019-4fcd-9222-d928b709e346', 'USTADZ'),
('31e1bf78-0019-4fcd-9222-d928b709e346', 'KESEHATAN');


