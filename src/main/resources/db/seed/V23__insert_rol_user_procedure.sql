DO $$
DECLARE
    v_user pos_system_users%rowtype;
    v_roles BIGINT[];
    v_rol_id BIGINT;
BEGIN
    SELECT ARRAY(
        SELECT id
        FROM pos_system_roles
    )
    INTO v_roles;

    FOR v_user IN
        SELECT *
        FROM pos_system_users
    LOOP
        v_rol_id:=v_roles[
            FLOOR(RANDOM()*array_length(v_roles,1)+1)::INT
        ];

		INSERT INTO pos_system_user_role(user_id,role_id) VALUES(v_user.id,v_rol_id);
    END LOOP;
END $$;