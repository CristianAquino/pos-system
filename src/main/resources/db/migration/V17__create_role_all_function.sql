CREATE OR REPLACE FUNCTION sf_role_all()
RETURNS SETOF pos_system_roles
AS
$$
BEGIN
    RETURN query
    SELECT *
    FROM pos_system_roles
    WHERE status = 'A';
END;
$$
LANGUAGE plpgsql;
