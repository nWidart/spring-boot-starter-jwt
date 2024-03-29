--liquibase formatted sql

--changeset admin:changelog-003-import-memo-data logicalFilePath:changelog-003-import-memo-data.sql failOnError:true

INSERT INTO memo (id, title, description, done, updated)
VALUES (1, 'memo shopping', 'memo1 description', false, '2018-01-04 12:01:00');
INSERT INTO memo (id, title, description, done, updated)
VALUES (2, 'memo job', 'memo2 description', false, '2018-01-04 13:02:10');
INSERT INTO memo (id, title, description, done, updated)
VALUES (3, 'memo private', 'memo3 description', false, '2018-01-04 14:03:21');
INSERT INTO memo (id, title, description, done, updated)
VALUES (4, 'memo job', 'memo4 description', false, '2018-01-04 15:04:32');
INSERT INTO memo (id, title, description, done, updated)
VALUES (5, 'memo private', 'memo5 description', false, '2018-01-04 16:05:43');
INSERT INTO memo (id, title, description, done, updated)
VALUES (6, 'memo travel', 'memo6 description', false, '2018-01-04 17:06:54');
INSERT INTO memo (id, title, description, done, updated)
VALUES (7, 'memo travel', 'memo7 description', false, '2018-01-04 18:07:05');
INSERT INTO memo (id, title, description, done, updated)
VALUES (8, 'memo shopping', 'memo8 description', false, '2018-01-04 19:08:16');
INSERT INTO memo (id, title, description, done, updated)
VALUES (9, 'memo private', 'memo9 description', false, '2018-01-04 20:09:27');
INSERT INTO memo (id, title, description, done, updated)
VALUES (10, 'memo hospital', 'memoA description', false, '2018-01-04 21:10:38');
