DO $$
BEGIN

    UPDATE pos_system_cash_openings
    SET closed_at = NOW(), status = 'I'
    WHERE closed_at IS NULL;

END $$;