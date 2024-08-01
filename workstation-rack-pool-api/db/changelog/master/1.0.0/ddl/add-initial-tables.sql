--liquibase formatted sql
--changeset Murilo Andrade (qxz0q3j):add-initial-tables
set search_path to workstation_rack;

CREATE TABLE T_TEAM
(
    ID          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    NAME        VARCHAR(50)     NULL,
    PRODUCT     VARCHAR(100)    NULL,
    VERSION     INTEGER                  NOT NULL,
    CREATED_AT  TIMESTAMP WITH TIME ZONE NOT NULL,
    MODIFIED_AT TIMESTAMP WITH TIME ZONE,
    CREATED_BY  VARCHAR(20),
    MODIFIED_BY VARCHAR(20)
);

CREATE TABLE T_TEAM_MEMBER
(
    ID          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    TEAM_ID     UUID        NOT NULL,
    NAME        VARCHAR(50) NOT NULL,
    CTW_CODE    VARCHAR(8)  NOT NULL,
    ROLE        VARCHAR(20) NOT NULL,
    VERSION     INTEGER                  NOT NULL,
    CREATED_AT  TIMESTAMP WITH TIME ZONE NOT NULL,
    MODIFIED_AT TIMESTAMP WITH TIME ZONE,
    CREATED_BY  VARCHAR(20),
    MODIFIED_BY VARCHAR(20)
);

CREATE TABLE T_WORK_STATION_RACK
(
    ID          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    NAME        VARCHAR(50) NOT NULL,
    VERSION     INTEGER                  NOT NULL,
    CREATED_AT  TIMESTAMP WITH TIME ZONE NOT NULL,
    MODIFIED_AT TIMESTAMP WITH TIME ZONE,
    CREATED_BY  VARCHAR(20),
    MODIFIED_BY VARCHAR(20)
);

CREATE TABLE T_BOOKING
(
    ID                      UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    TEAM_MEMBER_ID          UUID NOT NULL,
    WORK_STATION_RACK_ID    UUID NOT NULL,
    STATUS                  VARCHAR(10) NOT NULL,
    BOOKING_FROM            TIMESTAMP WITH TIME ZONE NOT NULL,
    BOOKING_TO              TIMESTAMP WITH TIME ZONE NOT NULL,
    VERSION                 INTEGER                  NOT NULL,
    CREATED_AT              TIMESTAMP WITH TIME ZONE NOT NULL,
    MODIFIED_AT             TIMESTAMP WITH TIME ZONE,
    CREATED_BY              VARCHAR(20),
    MODIFIED_BY             VARCHAR(20)
);
