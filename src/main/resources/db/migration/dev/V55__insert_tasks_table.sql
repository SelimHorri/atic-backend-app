
INSERT INTO tasks (worker_id, reservation_id, start_date, end_date, worker_description, manager_description) VALUES
(1, 1, '2022-06-04 12:30:25', NULL, NULL, 'We need endup with this very quickly'),
(1, 2, '2022-06-05 12:00:30', CURRENT_TIMESTAMP(), 'finished success', 'great'),
(1, 3, '2022-06-06 11:00:15', NULL, 'On my way to finish', 'waiting u to complete'),
(1, 4, '2022-06-06 17:00:20', NULL, 'On my way to finish erger', 'waiting u to completeerger'),
(2, 5, '2022-06-07 16:30:41', CURRENT_TIMESTAMP(), 'finished successrft', 'great!'),
(2, 1, '2022-06-04 12:31:40', CURRENT_TIMESTAMP(), 'finished successrft', 'great!'),
(2, 2, '2022-06-05 12:00:43', CURRENT_TIMESTAMP(), 'finished successrft', 'great!'),
(2, 3, '2022-06-06 11:00:32', CURRENT_TIMESTAMP(), 'finished successrft', 'great!'),
(2, 6, '2022-06-08 18:30:05', NULL, NULL, 'starts very soon!'),
(2, 7, '2022-06-09 09:02:25', NULL, NULL, 'waiting u to completedf'),
(5, 8, '2022-06-09 11:00:32', CURRENT_TIMESTAMP(), 'finished success fgh', 'great!!'),
(5, 9, CURRENT_TIMESTAMP(), NULL, NULL, NULL),
(5, 10, CURRENT_TIMESTAMP(), NULL, NULL, 'start asap plz!'),
(5, 11, '2022-05-31 13:30:43', CURRENT_TIMESTAMP(), 'finished success!!', 'great!!'),
(6, 12, '2022-05-31 15:00:08', CURRENT_TIMESTAMP(), 'finished successdfg', 'greatdf'),
(5, 13, NULL, NULL, NULL, 'we need great job'),
(6, 13, NULL, NULL, NULL, 'we need a very great job'),
(6, 14, NULL, NULL, NULL, NULL),
-- (6, 14, '2022-06-02 09:30:55', NULL, 'On my way to finish', NULL),
(7, 15, '2022-06-02 10:00:06', NULL, 'On my way to finish', 'waiting u to complete'),
(8, 16, CURRENT_TIMESTAMP(), NULL, NULL, 'we need awesome work on this'),
(8, 17, CURRENT_TIMESTAMP(), NULL, NULL, NULL);



