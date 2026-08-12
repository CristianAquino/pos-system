DO $$
DECLARE
    v_max_caja INTEGER :=4;
    v_min_caja INTEGER :=2;
    v_random INTEGER;
    v_data INTEGER;
    v_users_caja UUID[];
    v_cash_register UUID[];
BEGIN
    v_random := fn_gen_random(v_min_caja,v_max_caja);

    BEGIN
        SELECT ARRAY(
            SELECT user_id
            FROM pos_system_user_role
            WHERE role_id = 2
            ORDER BY RANDOM()
            LIMIT v_random
        )
        INTO v_users_caja;
    END;

    BEGIN
        WITH updated_rows AS (
            UPDATE pos_system_cash_registers
            SET status = 'A'
            WHERE id IN (
                SELECT id
                FROM pos_system_cash_registers
                WHERE status = 'I' AND soft_delete IS NULL
                ORDER BY RANDOM()
                LIMIT v_random
            )
            RETURNING id
        )
        SELECT ARRAY_AGG(id)
        INTO v_cash_register
        FROM updated_rows;
    END;

   BEGIN
       FOR v_data IN 1..v_random
       LOOP
            INSERT INTO pos_system_cash_openings(cash_register_id, user_id)
            VALUES(v_cash_register[v_data],v_users_caja[v_data]);
       END LOOP;
   END;
END $$;