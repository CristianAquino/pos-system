CREATE TRIGGER tr_users_updated_at
BEFORE UPDATE
ON pos_system_users
FOR EACH ROW
EXECUTE FUNCTION fn_update_timestamp();

CREATE TRIGGER tr_products_updated_at
BEFORE UPDATE
ON pos_system_products
FOR EACH ROW
EXECUTE FUNCTION fn_update_timestamp();

CREATE TRIGGER tr_sales_updated_at
BEFORE UPDATE
ON pos_system_sales
FOR EACH ROW
EXECUTE FUNCTION fn_update_timestamp();

CREATE TRIGGER tr_payment_methods_updated_at
BEFORE UPDATE
ON pos_system_payment_methods
FOR EACH ROW
EXECUTE FUNCTION fn_update_timestamp();

CREATE TRIGGER tr_cash_registers_updated_at
BEFORE UPDATE
ON pos_system_cash_registers
FOR EACH ROW
EXECUTE FUNCTION fn_update_timestamp();

CREATE TRIGGER tr_cash_openings_updated_at
BEFORE UPDATE
ON pos_system_cash_openings
FOR EACH ROW
EXECUTE FUNCTION fn_update_timestamp();

CREATE TRIGGER tr_inventory_movements_updated_at
BEFORE UPDATE
ON pos_system_inventory_movements
FOR EACH ROW
EXECUTE FUNCTION fn_update_timestamp();

