CREATE TABLE pos_system_user_role (
    user_id UUID NOT NULL,
    role_id BIGINT NOT NULL,

    PRIMARY KEY(user_id,role_id)
);

COMMENT ON TABLE pos_system_user_role IS
'Tabla intermedia user-role del sistema POS';

COMMENT ON COLUMN pos_system_user_role.user_id IS
'Clave foranea del usuario';

COMMENT ON COLUMN pos_system_user_role.role_id IS
'Clave foranea del rol';

