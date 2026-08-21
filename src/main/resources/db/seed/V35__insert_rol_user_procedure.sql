DO $$
DECLARE
    v_user pos_system_users%rowtype;
    v_contador INTEGER:=0;
BEGIN
    FOR v_user IN
        SELECT *
        FROM pos_system_users
        WHERE soft_delete IS NULL
        ORDER BY RANDOM()
    LOOP
        v_contador:=v_contador+1;

        IF v_contador = 1 THEN
            INSERT INTO pos_system_user_role(user_id,role_id) VALUES(v_user.id,1);
        ELSE
            INSERT INTO pos_system_user_role(user_id,role_id) VALUES(v_user.id,2);
        END IF;
    END LOOP;
END $$;