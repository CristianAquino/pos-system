DROP FUNCTION IF EXISTS fn_expiration_tax();
CREATE OR REPLACE FUNCTION fn_expiration_tax()
RETURNS TRIGGER AS $$
BEGIN
    UPDATE pos_system_tax_rates
    SET effective_to = NOW(), status = 'I'
    WHERE effective_to IS NULL AND status = 'A';

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;