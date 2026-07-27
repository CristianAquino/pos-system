DROP FUNCTION IF EXISTS fn_expiration_tax();
CREATE OR REPLACE FUNCTION fn_expiration_tax()
RETURNS TRIGGER AS $$
BEGIN
    UPDATE pos_system_tax_rates
    SET effective_to = NOW()
    WHERE effective_to IS NULL;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;