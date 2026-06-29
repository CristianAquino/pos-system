CREATE TABLE pos_system_user_role (
    user_id UUID NOT NULL,
    role_id BIGINT NOT NULL,
    assigned_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY(user_id,role_id)
);

COMMENT ON TABLE pos_system_user_role IS
'Tabla intermedia user-role del sistema POS';

COMMENT ON COLUMN pos_system_user_role.user_id IS
'Clave foranea del usuario';

COMMENT ON COLUMN pos_system_user_role.role_id IS
'Clave foranea del rol';

COMMENT ON COLUMN pos_system_user_role.assigned_at IS
'Fecha que se realiza la asignacion del rol al usuario';

