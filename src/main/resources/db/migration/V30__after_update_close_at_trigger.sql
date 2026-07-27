DROP TRIGGER IF EXISTS tr_close_cash_op ON pos_system_cash_openings;
CREATE OR REPLACE TRIGGER tr_close_cash_op
AFTER UPDATE OF closed_at
ON pos_system_cash_openings
FOR EACH ROW
WHEN(NEW.closed_at IS NOT NULL)
EXECUTE FUNCTION fn_close_cash_op();