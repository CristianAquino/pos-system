CREATE OR REPLACE FUNCTION fn_random_code()
RETURNS text
AS
$$
DECLARE
    v_aleatorio text;
BEGIN
    LOOP
        v_aleatorio:=FLOOR(random() * 1000000)::int;
        EXIT WHEN NOT EXISTS(
            SELECT 1
    		FROM pos_system_roles
    		WHERE code = v_aleatorio
    	);
	END LOOP;

    RETURN v_aleatorio;
END;
$$ LANGUAGE plpgsql;