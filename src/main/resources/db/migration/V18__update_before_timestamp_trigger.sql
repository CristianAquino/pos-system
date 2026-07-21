DROP TRIGGER IF EXISTS tr_users_updated_at ON pos_system_users;
CREATE TRIGGER tr_users_updated_at
BEFORE UPDATE
ON pos_system_users
FOR EACH ROW
EXECUTE FUNCTION fn_update_timestamp();

DROP TRIGGER IF EXISTS tr_roles_updated_at ON pos_system_roles;
CREATE TRIGGER tr_roles_updated_at
BEFORE UPDATE
ON pos_system_roles
FOR EACH ROW
EXECUTE FUNCTION fn_update_timestamp();

DROP TRIGGER IF EXISTS tr_products_updated_at ON pos_system_products;
CREATE TRIGGER tr_products_updated_at
BEFORE UPDATE
ON pos_system_products
FOR EACH ROW
EXECUTE FUNCTION fn_update_timestamp();

DROP TRIGGER IF EXISTS tr_sales_updated_at ON pos_system_sales;
CREATE TRIGGER tr_sales_updated_at
BEFORE UPDATE
ON pos_system_sales
FOR EACH ROW
EXECUTE FUNCTION fn_update_timestamp();

DROP TRIGGER IF EXISTS tr_payment_methods_updated_at ON pos_system_payment_methods;
CREATE TRIGGER tr_payment_methods_updated_at
BEFORE UPDATE
ON pos_system_payment_methods
FOR EACH ROW
EXECUTE FUNCTION fn_update_timestamp();

DROP TRIGGER IF EXISTS tr_cash_registers_updated_at ON pos_system_cash_registers;
CREATE TRIGGER tr_cash_registers_updated_at
BEFORE UPDATE
ON pos_system_cash_registers
FOR EACH ROW
EXECUTE FUNCTION fn_update_timestamp();

DROP TRIGGER IF EXISTS tr_cash_openings_updated_at ON pos_system_cash_openings;
CREATE TRIGGER tr_cash_openings_updated_at
BEFORE UPDATE
ON pos_system_cash_openings
FOR EACH ROW
EXECUTE FUNCTION fn_update_timestamp();

DROP TRIGGER IF EXISTS tr_inventory_movements_updated_at ON pos_system_inventory_movements;
CREATE TRIGGER tr_inventory_movements_updated_at
BEFORE UPDATE
ON pos_system_inventory_movements
FOR EACH ROW
EXECUTE FUNCTION fn_update_timestamp();

