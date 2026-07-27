DROP FUNCTION IF EXISTS fn_close_cash_op();
CREATE OR REPLACE FUNCTION fn_close_cash_op()
RETURNS TRIGGER
AS $$
DECLARE
    v_total_efectivo NUMERIC(10,2);
    v_total_yape NUMERIC(10,2);
    v_total_plin NUMERIC(10,2);
    v_total_tarjeta NUMERIC(10,2);
    v_total NUMERIC(10,2);
    v_sale pos_system_sales%rowtype;
BEGIN
    v_total_efectivo:=0;
    v_total_yape:=0;
    v_total_plin:=0;
    v_total_tarjeta:=0;
    v_total:=0;

    -- inactivando caja registradora
    UPDATE pos_system_cash_registers
    SET status = 'I'
    WHERE id = NEW.cash_register_id;

    -- obteniendo totales de las ventas
    FOR v_sale IN
        SELECT * FROM pos_system_sales
        WHERE cash_opening_id = NEW.id
    LOOP
        CASE v_sale.payment_method_id
            WHEN 1 THEN v_total_efectivo := v_total_efectivo + v_sale.total;
            WHEN 2 THEN v_total_yape := v_total_yape + v_sale.total;
            WHEN 3 THEN v_total_plin := v_total_plin + v_sale.total;
            WHEN 4 THEN v_total_tarjeta := v_total_tarjeta + v_sale.total;
        END CASE;
    END LOOP;

    v_total:= v_total_efectivo+v_total_yape+v_total_plin+v_total_tarjeta;

    -- actualizando montos de la caja
    UPDATE pos_system_cash_openings
    SET cash_amount = v_total_efectivo, yape_amount = v_total_yape, plin_amount = v_total_plin, card_amount =
    v_total_tarjeta, closing_amount = v_total, expect_cash_amount = v_total_efectivo
    WHERE id = NEW.id;

    RETURN NEW;

END;
$$ LANGUAGE plpgsql;