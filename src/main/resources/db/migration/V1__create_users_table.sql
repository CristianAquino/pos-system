CREATE TABLE pos_system_users (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    name VARCHAR(80) NOT NULL,
    father_last_name VARCHAR(60) NOT NULL,
    mother_last_name VARCHAR(60) NOT NULL,
    full_name VARCHAR(220) NOT NULL,
    username VARCHAR(60) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    status CHAR(1) NOT NULL DEFAULT 'A' CHECK(status IN ('A','I')),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT NULL
);

COMMENT ON TABLE pos_system_users IS
'Usuarios del sistema POS';

COMMENT ON COLUMN pos_system_users.id IS
'Identificador único del usuario';

COMMENT ON COLUMN pos_system_users.name IS
'Nombres del usuario';

COMMENT ON COLUMN pos_system_users.father_last_name IS
'Apellido paterno del usuario';

COMMENT ON COLUMN pos_system_users.mother_last_name IS
'Apellido materno del usuario';

COMMENT ON COLUMN pos_system_users.full_name IS
'Nombres completos del usuario';

COMMENT ON COLUMN pos_system_users.username IS
'Nombre de usuario utilizado para iniciar sesión, el cual consistira en C_<tres_letras_apellido> <tres_letras_nombre>';

COMMENT ON COLUMN pos_system_users.password IS
'Hash de la contraseña utilizando BCrypt';

COMMENT ON COLUMN pos_system_users.status IS
'Estado del usuario: A = activo, I = inactivo';

COMMENT ON COLUMN pos_system_users.created_at IS
'Fecha de creacion de usuario';

COMMENT ON COLUMN pos_system_users.updated_at IS
'Fecha de actualizacion de usuario';