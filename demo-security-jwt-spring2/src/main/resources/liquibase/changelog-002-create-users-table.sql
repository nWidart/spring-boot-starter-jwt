--liquibase formatted sql

--changeset admin:changelog-002-create-users-table logicalFilePath:changelog-002-create-users-table.sql failOnError:true

CREATE TABLE IF NOT EXISTS user
(
    id         BIGINT AUTO_INCREMENT,
    name       VARCHAR(128) NOT NULL,
    password   VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL,
    admin_flag BOOLEAN      NOT NULL DEFAULT FALSE,
    PRIMARY KEY (id),
    UNIQUE KEY (email)
)
