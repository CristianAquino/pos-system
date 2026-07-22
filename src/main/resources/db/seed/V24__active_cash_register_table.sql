DO $$
DECLARE
    v_max INTEGER :=4;
    v_min INTEGER :=2;
    v_random INTEGER;
BEGIN
    v_random := FLOOR(RANDOM()*(v_max-v_min+1)+v_min)::INT;

    UPDATE pos_system_cash_registers
    SET status = 'A'
    WHERE id IN(
        SELECT id
        FROM pos_system_cash_registers
        WHERE status = 'I'
        ORDER BY RANDOM()
        LIMIT v_random
    );
END $$;