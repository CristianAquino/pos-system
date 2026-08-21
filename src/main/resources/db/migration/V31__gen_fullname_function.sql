DROP FUNCTION IF EXISTS fn_generate_fullname();
CREATE OR REPLACE FUNCTION fn_generate_fullname()
RETURNS TRIGGER
AS $$
BEGIN

    NEW.full_name := NEW.name ||' '|| NEW.father_last_name ||' '|| NEW.mother_last_name;

    RETURN NEW;

END;
$$ LANGUAGE plpgsql;