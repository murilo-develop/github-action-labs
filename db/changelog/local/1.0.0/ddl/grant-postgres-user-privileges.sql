--liquibase formatted sql
--changeset Murilo Andrade (qxz0q3j):grant-postgres-user-privileges
set search_path to workstation_rack;

GRANT USAGE ON SCHEMA workstation_rack TO postgres;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA workstation_rack TO postgres;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA workstation_rack TO postgres;
GRANT ALL PRIVILEGES ON ALL FUNCTIONS IN SCHEMA workstation_rack TO postgres;
GRANT ALL PRIVILEGES ON ALL PROCEDURES IN SCHEMA workstation_rack TO postgres;
GRANT ALL PRIVILEGES ON ALL ROUTINES IN SCHEMA workstation_rack TO postgres;
