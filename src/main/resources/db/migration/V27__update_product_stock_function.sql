DROP FUNCTION IF EXISTS fn_upd_product_stock();
CREATE OR REPLACE FUNCTION fn_upd_product_stock()
RETURNS TRIGGER
AS $$
BEGIN

    UPDATE pos_system_products
    SET stock = NEW.new_stock, status = case WHEN NEW.new_stock <= 0 THEN 'I' ELSE 'A' END
    WHERE id = NEW.product_id;

    RETURN NEW;

END;
$$ LANGUAGE plpgsql;