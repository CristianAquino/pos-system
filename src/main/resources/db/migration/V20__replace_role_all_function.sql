DROP FUNCTION IF EXISTS sf_role_all();
CREATE OR REPLACE FUNCTION sf_role_all(
p_status CHAR DEFAULT NUll
)
RETURNS SETOF pos_system_roles
AS
$$
BEGIN
    RETURN query
    SELECT *
    FROM pos_system_roles
    WHERE COALESCE(TRIM(p_status), '') = '' OR status = p_status;
END;
$$
LANGUAGE plpgsql;
