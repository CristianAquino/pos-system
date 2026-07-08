DO $$
DECLARE
    rol TEXT;
    roles TEXT[];
	cod TEXT;
BEGIN
	roles := ARRAY['ADMIN','CAJA'];

    FOREACH rol IN ARRAY roles LOOP
		LOOP
			cod := random_code();
			EXIT WHEN NOT EXISTS(
				SELECT 1
				FROM pos_system_roles
				WHERE code = cod
			);
	    END LOOP;
		INSERT INTO pos_system_roles(code,description) VALUES(cod,rol);
    END LOOP;
END $$;