DROP TRIGGER IF EXISTS tr_generate_fullname ON pos_system_users;
CREATE OR REPLACE TRIGGER tr_generate_fullname
BEFORE INSERT OR UPDATE
ON pos_system_users
FOR EACH ROW
EXECUTE FUNCTION fn_generate_fullname();