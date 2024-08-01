------------------------------------------------------------------------------------------------------------------
-- Author: Murilo Andrade (qxz0q3j)
-- Reason: Create Schema and the required extensions
------------------------------------------------------------------------------------------------------------------
create schema if not exists workstation_rack;

set search_path to public;
create extension if not exists pgcrypto schema public;
