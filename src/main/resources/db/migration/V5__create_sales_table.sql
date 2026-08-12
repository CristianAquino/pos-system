CREATE TABLE pos_system_sales (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    user_id UUID NOT NULL,
    cash_opening_id UUID NOT NULL,
    payment_method_id BIGINT NOT NULL,
    sale_code VARCHAR(200) DEFAULT NULL,
    subtotal NUMERIC(10,2) NOT NULL DEFAULT 0 CHECK(subtotal>=0),
    ptax INTEGER NOT NULL DEFAULT 0 CHECK(ptax>=0),
    tax NUMERIC(10,2) NOT NULL DEFAULT 0 CHECK(tax>=0),
    total NUMERIC(10,2) NOT NULL DEFAULT 0 CHECK(total>=0),
    status VARCHAR(1) NOT NULL DEFAULT 'A' CHECK(status IN ('A','I')),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT NULL
);

COMMENT ON TABLE pos_system_sales IS
'Ventas del sistema POS';

COMMENT ON COLUMN pos_system_sales.id IS
'Identificador único de la venta';

COMMENT ON COLUMN pos_system_sales.user_id IS
'Relacion con el usuario que realiza la venta';

COMMENT ON COLUMN pos_system_sales.cash_opening_id IS
'Relacion con la apertura de caja';

COMMENT ON COLUMN pos_system_sales.payment_method_id IS
'Relacion con el metodo de pago de la venta';

COMMENT ON COLUMN pos_system_sales.sale_code IS
'Codigo de venta, debe empezar con S<numeros_secuenciales>';

COMMENT ON COLUMN pos_system_sales.subtotal IS
'Subtotal de la venta realizada';

COMMENT ON COLUMN pos_system_sales.ptax IS
'Porcentaje de descuento a la venta, valor debe estar en entero';

COMMENT ON COLUMN pos_system_sales.tax IS
'Total a pagar por impuestos';

COMMENT ON COLUMN pos_system_sales.total IS
'Total a pagar por la venta, incluye subtotal + tax';

COMMENT ON COLUMN pos_system_sales.status IS
'Estado de la venta, valores permitidos son A = activo, I = anulada';

COMMENT ON COLUMN pos_system_sales.created_at IS
'Fecha de creacion de la venta';

COMMENT ON COLUMN pos_system_sales.updated_at IS
'Fecha de actualizacion de la venta';