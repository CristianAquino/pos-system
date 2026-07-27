DO $$
DECLARE
    rol TEXT;
    roles TEXT[];
	cod TEXT;
BEGIN
	roles := ARRAY['ADMIN','CAJA'];

    FOREACH rol IN ARRAY roles LOOP
		INSERT INTO pos_system_roles(description) VALUES(rol);
    END LOOP;
END $$;