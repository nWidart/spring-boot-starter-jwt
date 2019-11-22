--liquibase formatted sql

--changeset admin:changelog-001-create-memos-table logicalFilePath:changelog-001-changelog-001-create-memos-table.sql failOnError:true

CREATE TABLE IF NOT EXISTS memo
(
    id          BIGINT AUTO_INCREMENT,
    title       VARCHAR(255) NOT NULL,
    description TEXT         NOT NULL,
    done        BOOLEAN      NOT NULL DEFAULT FALSE,
    updated     TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    PRIMARY KEY (id)
)
