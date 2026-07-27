DROP TRIGGER IF EXISTS tr_expiration_tax ON pos_system_tax_rates;
CREATE OR REPLACE TRIGGER tr_expiration_tax
BEFORE INSERT
ON pos_system_tax_rates
FOR EACH ROW
EXECUTE FUNCTION fn_expiration_tax();