drop table if exists posts;
drop table if exists users;
drop table if exists regions;
-- create sequence regions_seq start with 1;
-- create sequence posts_seq start with 1;
-- create sequence users_seq start with 1;
CREATE TABLE public.regions
(
    id   serial      not null,
    name VARCHAR(45) NOT NULL,
    CONSTRAINT region_pkey PRIMARY KEY (id)
);
CREATE TABLE public.users
(
    id        serial       not null,
    name      VARCHAR(255) NOT NULL,
    surname   VARCHAR(255) NOT NULL,
    role      VARCHAR(45)  NOT NULL,
    region_id BIGINT       NULL,
    CONSTRAINT user_pkey PRIMARY KEY (id),
    CONSTRAINT fk_user FOREIGN KEY (region_id)
        REFERENCES "regions" (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE TABLE public.posts
(
    id      serial                                                       not null,
    content VARCHAR(255)                                                 NOT NULL,
    created TIMESTAMP(0) DEFAULT date_trunc('second', current_timestamp) NOT NULL,
    updated TIMESTAMP(0) DEFAULT date_trunc('second', current_timestamp) NOT NULL,
    user_id BIGINT                                                       NULL,
    CONSTRAINT post_pkey PRIMARY KEY (id),
    CONSTRAINT fk_post FOREIGN KEY (user_id)
        REFERENCES "users" (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE CASCADE
);
-- create sequence HIBERNATE_SEQUENCE
--     minvalue 1
--     maxvalue 9999999999999999
--     start with 1
--     increment by 1
--     cache 20;




