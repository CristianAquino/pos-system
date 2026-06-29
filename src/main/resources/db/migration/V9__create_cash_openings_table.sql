CREATE TABLE pos_system_cash_openings (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    cash_register_id UUID NOT NULL,
    user_id UUID NOT NULL,
    opening_amount NUMERIC(10,2) NOT NULL DEFAULT 0 CHECK(opening_amount>=0),
    closing_amount NUMERIC(10,2) DEFAULT NULL CHECK(closing_amount>=0),
    opened_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    closed_at TIMESTAMP DEFAULT NULL,
    status CHAR(1) NOT NULL DEFAULT 'A' CHECK(status IN ('A','I')),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT NULL
);

COMMENT ON TABLE pos_system_cash_openings IS
'Apertura de caja del sistema POS';

COMMENT ON COLUMN pos_system_cash_openings.id IS
'Identificador único de apertura de caja';

COMMENT ON COLUMN pos_system_cash_openings.cash_register_id IS
'Relacion con caja registradora aperturada';

COMMENT ON COLUMN pos_system_cash_openings.user_id IS
'Relacion con usuario activo';

COMMENT ON COLUMN pos_system_cash_openings.opening_amount IS
'Monto con el cual se apertura la caja, debe ser positvo';

COMMENT ON COLUMN pos_system_cash_openings.closing_amount IS
'Monton con el cual se cierra la caja, debe ser positvo';

COMMENT ON COLUMN pos_system_cash_openings.opened_at IS
'Fecha y hora de apertura de caja';

COMMENT ON COLUMN pos_system_cash_openings.closed_at IS
'Fecha y hora de cierre de caja';

COMMENT ON COLUMN pos_system_cash_openings.status IS
'Estado de la apertura de caja: A = abierta, I = cerrada';

COMMENT ON COLUMN pos_system_cash_openings.created_at IS
'Fecha de creacion de la apertura de caja';

COMMENT ON COLUMN pos_system_cash_openings.updated_at IS
'Fecha de actualizacion de la apertura de caja';