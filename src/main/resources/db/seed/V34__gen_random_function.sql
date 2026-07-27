DROP FUNCTION IF EXISTS fn_gen_random(min,max);
CREATE OR REPLACE FUNCTION fn_gen_random(min INTEGER, max INTEGER)
RETURNS INTEGER AS $$
DECLARE
    v_random INTEGER;
BEGIN
    v_random := FLOOR(RANDOM()*(max-min)+min)::INT;
    RETURN v_random;
END;
$$ LANGUAGE plpgsql;