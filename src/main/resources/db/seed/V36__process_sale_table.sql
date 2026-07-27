DO $$
DECLARE
    v_max_prod INTEGER :=4;
    v_min INTEGER :=1;
    v_random_products INTEGER;
    v_products JSONB;
    v_item JSONB;
    v_cash_op UUID;
    v_user_id UUID;
    v_payment_method INTEGER;
    v_sale_id UUID;
    v_total NUMERIC(10,2);
BEGIN
    -- iniciando ventas
    FOR _ IN 1..5
    LOOP
    -- eligiendo productos
        v_random_products := fn_gen_random(v_min,v_max_prod);
        WITH selected_rows AS (
            SELECT id, stock, sale_price,q
            FROM (
                SELECT id,
                       stock,
                       sale_price,
                       fn_gen_random(
                           1,
                           GREATEST(1, FLOOR(minimum_stock/2)::INTEGER)
                       ) AS q
                FROM pos_system_products
                WHERE status = 'A'
            ) t
            WHERE stock >= q
            ORDER BY RANDOM()
            LIMIT v_random_products
        )
        SELECT jsonb_agg(
            jsonb_build_object(
                'id', id,
                'unit',sale_price,
                'stock',stock,
                'quantity', q
            )
        )
        INTO v_products
        FROM selected_rows;

        -- elegiendo metodo de pago
        SELECT id
        INTO v_payment_method
        FROM pos_system_payment_methods
        WHERE status = 'A'
        ORDER BY RANDOM()
        LIMIT 1;

        -- generando venta
        SELECT id, user_id
        INTO v_cash_op, v_user_id
        FROM pos_system_cash_openings
        WHERE status = 'A'
        ORDER BY RANDOM()
        LIMIT 1;

        INSERT INTO pos_system_sales(user_id,cash_opening_id,payment_method_id)
        VALUES(v_user_id,v_cash_op, v_payment_method)
        RETURNING id INTO v_sale_id;

        -- product details
        v_total:=0;
        FOR v_item IN
        SELECT * FROM jsonb_array_elements(v_products)
        LOOP
            v_total:= v_total + (v_item->>'quantity')::numeric*(v_item->>'unit')::numeric;
            -- detalle de la venta
            INSERT INTO pos_system_sale_details(sale_id, product_id, quantity, unit_price, subtotal)
            VALUES(v_sale_id,(v_item->>'id')::numeric,(v_item->>'quantity')::numeric,(v_item->>'unit')::numeric,
            (v_item->>'quantity')::numeric*(v_item->>'unit')::numeric);

            -- inventario del producto
            INSERT INTO pos_system_inventory_movements(product_id, user_id, sale_id, quantity, previous_stock,
            new_stock)
            VALUES((v_item->>'id')::numeric,v_user_id,v_sale_id,(v_item->>'quantity')::numeric,(v_item->>'stock')::numeric,
            (v_item->>'stock')::numeric - (v_item->>'quantity')::numeric);

        END LOOP;

        -- actualizamos la venta
        UPDATE pos_system_sales s
        SET total = v_total, subtotal = FLOOR(v_total*(100-s.tax)/100)
        WHERE id = v_sale_id;

        -- moviento de la venta
        INSERT INTO pos_system_cash_movements(cash_opening_id,amount)
        VALUES(v_cash_op,v_total);

    END LOOp;

END $$;
