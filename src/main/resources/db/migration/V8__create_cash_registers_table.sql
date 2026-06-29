CREATE TABLE pos_system_cash_registers (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    name VARCHAR(10) NOT NULL UNIQUE,
    status CHAR(1) NOT NULL DEFAULT 'A' CHECK(status IN ('A','I')),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT NULL
);

COMMENT ON TABLE pos_system_cash_registers IS
'Caja registradora del sistema POS';

COMMENT ON COLUMN pos_system_cash_registers.id IS
'Identificador único de la caja registradora';

COMMENT ON COLUMN pos_system_cash_registers.name IS
'Nombre de la caja registradora';

COMMENT ON COLUMN pos_system_cash_registers.status IS
'Estado de la caja registradora: A = activo, I = inactivo';

COMMENT ON COLUMN pos_system_cash_registers.created_at IS
'Fecha de creacion de la caja registradora';

COMMENT ON COLUMN pos_system_cash_registers.updated_at IS
'Fecha de actualizacion de la caja registradora';