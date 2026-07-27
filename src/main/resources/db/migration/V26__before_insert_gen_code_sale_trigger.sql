DROP TRIGGER IF EXISTS tr_generate_sale_code ON pos_system_sales;
CREATE OR REPLACE TRIGGER tr_sale_code
BEFORE INSERT
ON pos_system_sales
FOR EACH ROW
EXECUTE FUNCTION fn_generate_sale_code();