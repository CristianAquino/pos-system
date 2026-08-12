DROP FUNCTION IF EXISTS fn_generate_sale_code();
CREATE OR REPLACE FUNCTION fn_generate_sale_code()
RETURNS TRIGGER
AS $$
DECLARE
    v_tax INTEGER := 0;
    v_code VARCHAR := '';
BEGIN

    DECLARE
        v_count INTEGER := 0;
    BEGIN
        SELECT COUNT(id)
        INTO v_count
        FROM pos_system_sales;

        v_count := v_count + 1;
        v_code := 'S'||LPAD(v_count::TEXT,6,'0');
    END;

    BEGIN
        SELECT rate
        INTO v_tax
        FROM pos_system_tax_rates
        WHERE status = 'A' AND effective_to IS NULL;
    END;

    NEW.sale_code := v_code;
    NEW.ptax := v_tax;

    RETURN NEW;

END;
$$ LANGUAGE plpgsql;