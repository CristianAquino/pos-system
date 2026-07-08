CREATE OR REPLACE FUNCTION fn_call_generate_code()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.code IS NULL THEN
        NEW.code := fn_random_code();
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;