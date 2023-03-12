
INSERT INTO reservations (code, start_date, description, status, customer_id, saloon_id) VALUES
('86d1523e-2788-4ef0-8849-fe26c396360b', '2022-06-04 12:30:00', 'ASAP', 'NOT_STARTED', 5, 2),
('925e935e-3d06-4576-9ffe-02fe485c2cd2', '2022-06-05 12:00:00', 'ASAP', 'NOT_STARTED', 5, 2),
('71d58140-35b7-4f1b-82c7-548ffa6742ab', '2022-06-06 11:00:00', 'ASAP', 'NOT_STARTED', 5, 2),
('02b232b2-5422-48f4-afdb-3a24c6f97fe7', '2022-06-06 17:00:00', 'ASAP', 'NOT_STARTED', 5, 2),
('7d852e04-92fe-4dba-b151-b2de5b153692', '2022-06-07 16:30:00', 'ASAP', 'NOT_STARTED', 5, 2),
('3f0893c0-ea1f-46ad-bf4c-34aaa41426a7', '2022-06-08 18:30:00', 'ASAP', 'NOT_STARTED', 5, 2),
('3479a057-3309-4421-955f-69f067b12abf', '2022-06-09 09:00:00', 'ASAP', 'NOT_STARTED', 5, 2),
('fdd062a1-a44b-486d-8ab5-be71ff7e074c', '2022-06-09 11:00:00', 'ASAP', 'NOT_STARTED', 5, 1),
('d31cfc5c-fd55-4887-9b8c-951a42f5b00d', '2022-05-30 09:30:00', 'done!!', 'COMPLETED', 6, 1),
('6276666a-eff5-43fa-aadf-cf7f5cda1da9', '2022-05-30 12:30:00', NULL, 'IN_PROGRESS', 1, 2),
('a70f1e5a-9b6a-41e1-aad4-79ba78610a4b', '2022-05-31 13:30:00', 'on going!!', 'IN_PROGRESS', 1, 2),
('566996bf-179f-4db5-960e-e0351babd194', '2022-05-31 15:00:00', 'done', 'COMPLETED', 5, 2),
('d0f37d34-8608-4127-b704-2d2a21386f18', '2022-06-20 15:30:00', NULL, 'NOT_STARTED', 5, 2),
('81bc3955-a57b-4c62-96cd-2d2e0194bc99', '2022-06-02 09:30:00', 'ASAP', 'IN_PROGRESS', 4, 2),
('29d51409-720b-4e38-a9f1-5eb8e81dd079', '2022-06-02 10:00:00', NULL, 'COMPLETED', 8, 4),
('64c91a2e-804d-4f76-8374-0b61095860d0', '2022-06-02 10:30:00', 'i need to do this!', 'NOT_STARTED', 1, 4),
('2f58a400-ca68-49ab-a005-6ddcce163796', '2022-06-02 11:30:00', 'alot of quick!', 'NOT_STARTED', 1, 5),
('39c2fb12-4a6d-4c1f-b8c8-6f049e169501', '2022-06-02 13:00:00', NULL, 'COMPLETED', 3, 6),
('52a123f1-436c-4428-99f6-d57fb06835b1', '2022-06-03 10:30:00', NULL, 'COMPLETED', 3, 7),
('a09c32d8-cece-44cd-b30a-8f0b20a050b6', '2022-06-03 13:30:00', NULL, 'COMPLETED', 4, 7),
('e65e223e-c7cb-4590-b9a4-5fe9ed934c93', '2022-06-03 14:00:00', NULL, 'IN_PROGRESS', 5, 8),
('4126efd8-c456-45ae-b4ce-be589d2ffde6', '2022-06-04 10:30:00', NULL, 'IN_PROGRESS', 5, 7),
('90c9b98b-347e-4380-a517-f504a70124d0', '2022-06-04 17:00:00', NULL, 'NOT_STARTED', 5, 2),
('cfa8dea1-3126-4e23-8c8a-850857d06cd4', '2022-06-05 12:30:00', NULL, 'NOT_STARTED', 6, 1),
('759ed6e3-44fd-48fa-b8dc-06b181de3175', '2022-06-05 13:00:00', NULL, 'IN_PROGRESS', 6, 2),
('b465317c-43df-41bb-9127-d0d75f8e1ad5', '2022-06-05 17:00:00', NULL, 'IN_PROGRESS', 7, 3),
('d41728ab-f7d2-432f-b6a5-7486b89decee', '2022-06-06 18:00:00', NULL, 'NOT_STARTED', 7, 9),
('9bfec1a9-42a3-4985-8905-61df4c8c6321', '2022-06-07 19:00:00', NULL, 'IN_PROGRESS', 8, 10),
('0c6da640-79fd-451a-8e87-3614b57e8fef', '2022-06-08 12:00:00', NULL, 'NOT_STARTED', 8, 4);

UPDATE reservations
SET complete_date = '2022-05-30 10:00:00'
WHERE code = 'd31cfc5c-fd55-4887-9b8c-951a42f5b00d';

UPDATE reservations
SET complete_date = '2022-05-31 15:26:00'
WHERE code = '566996bf-179f-4db5-960e-e0351babd194';

UPDATE reservations
SET complete_date = '2022-06-02 10:15:00'
WHERE code = '29d51409-720b-4e38-a9f1-5eb8e81dd079';

UPDATE reservations
SET complete_date = '2022-06-02 13:20:00'
WHERE code = '39c2fb12-4a6d-4c1f-b8c8-6f049e169501';

UPDATE reservations
SET complete_date = '2022-06-03 10:50:00'
WHERE code = '52a123f1-436c-4428-99f6-d57fb06835b1';

UPDATE reservations
SET complete_date = '2022-06-03 13:40:00'
WHERE code = 'a09c32d8-cece-44cd-b30a-8f0b20a050b6';


