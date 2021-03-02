--truncate table regions,users,posts;
-- truncate table users;
-- truncate table posts;

-- drop table if exists posts;
-- drop table if exists users;
-- drop table if exists regions;

INSERT INTO regions (name)
VALUES ('UKR');
INSERT INTO regions (name)
VALUES ('IRL');
INSERT INTO users (name, surname, role, region_id)
VALUES ('Max', 'Hayday', 'USER', 1);
INSERT INTO users (name, surname, role, region_id)
VALUES ('Ola', 'Hayday', 'USER', 2);
INSERT INTO posts (content, created, updated, user_id)
VALUES ('Post 1 of Max', '2021-01-03 8:43:10', '2021-01-03 8:43:10', 1);
INSERT INTO posts (content, created, updated, user_id)
VALUES ('Post 1 of Ola', '2021-01-03 8:43:10', '2021-01-03 8:43:10', 2);
