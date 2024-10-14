INSERT INTO public.m_country
(id, created_by, created_date, last_modified_by, last_modified_date, code, description, "name")
VALUES(1, 'system', NULL, NULL, NULL, 'ID', NULL, 'INDONESIA');
INSERT INTO public.m_country
(id, created_by, created_date, last_modified_by, last_modified_date, code, description, "name")
VALUES(2, 'system', NULL, NULL, NULL, 'MY', NULL, 'MALAYSIA');

insert into m_province(country_id, code, name, created_by) VALUES
(1, '01','Aceh', 'system'),
(1, '02','Sumatera Utara', 'system'),
(1, '03','Sumatera Barat', 'system'),
(1, '04','Riau', 'system'),
(1, '05','Jambi', 'system'),
(1, '06','Sumatera Selatan', 'system'),
(1, '07','Bengkulu', 'system'),
(1, '08','Lampung', 'system'),
(1, '09','Kepulauan Bangka Belitung', 'system'),
(1, '10','Kepulauan Riau', 'system'),
(1, '11','DKI Jakarta', 'system'),
(1, '12','Jawa Barat', 'system'),
(1, '13','Jawa Tengah', 'system'),
(1, '14','DI Yogyakarta', 'system'),
(1, '15','Jawa Timur', 'system'),
(1, '16','Banten', 'system'),
(1, '17','Bali', 'system'),
(1, '18','Nusa Tenggara Barat', 'system'),
(1, '19','Nusa Tenggara Timur', 'system'),
(1, '20','Kalimantan Barat', 'system'),
(1, '21','Kalimantan Tengah', 'system'),
(1, '22','Kalimantan Selatan', 'system'),
(1, '23','Kalimantan Timur', 'system'),
(1, '24','Kalimantan Utara', 'system'),
(1, '25','Sulawesi Utara', 'system'),
(1, '26','Sulawesi Tengah', 'system'),
(1, '27','Sulawesi Selatan', 'system'),
(1, '28','Sulawesi Tenggara', 'system'),
(1, '29','Gorontalo', 'system'),
(1, '30','Sulawesi Barat', 'system'),
(1, '31','Maluku', 'system'),
(1, '32','Maluku Utara', 'system'),
(1, '33','Papua Barat', 'system'),
(1, '34','Papua', 'system');
