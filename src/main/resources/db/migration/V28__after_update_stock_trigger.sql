DROP TRIGGER IF EXISTS tr_upd_product_stock ON pos_system_inventory_movements;
CREATE OR REPLACE TRIGGER tr_upd_product_stock
AFTER INSERT
ON pos_system_inventory_movements
FOR EACH ROW
EXECUTE FUNCTION fn_upd_product_stock();