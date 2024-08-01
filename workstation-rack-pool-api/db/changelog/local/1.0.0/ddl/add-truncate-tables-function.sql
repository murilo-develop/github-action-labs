------------------------------------------------------------------------------------------------------------------
-- Author: Murilo Andrade (qxz0q3j)
-- Reason: Create function to truncate tables after tests
------------------------------------------------------------------------------------------------------------------
set search_path to workstation_rack;

CREATE OR REPLACE FUNCTION truncate_tables() RETURNS VOID AS
$$
DECLARE
tables RECORD;
    command
text = '';
BEGIN
FOR tables IN
SELECT
    oid::regclass::text
FROM
    pg_class
WHERE
        relkind = 'r' -- only tables
  AND relname not in ('databasechangelog', 'databasechangeloglock')
  AND relnamespace = 'workstation_rack'::regnamespace LOOP command := 'TRUNCATE TABLE ' || tables.OID || ' CASCADE';
EXECUTE command;
END LOOP;
RETURN;
END;
$$
LANGUAGE plpgsql;
