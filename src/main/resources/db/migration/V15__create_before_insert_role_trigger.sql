CREATE OR REPLACE TRIGGER tr_code_generate
BEFORE INSERT
ON pos_system_roles
FOR EACH ROW
EXECUTE FUNCTION fn_call_generate_code();