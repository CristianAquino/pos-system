-- relacion en la tabla intermedia user_role
ALTER TABLE pos_system_user_role
ADD CONSTRAINT fk_user_role_user
FOREIGN KEY (user_id)
REFERENCES pos_system_users(id);

ALTER TABLE pos_system_user_role
ADD CONSTRAINT fk_user_role_role
FOREIGN KEY (role_id)
REFERENCES pos_system_roles(id);

-- relacion en la tabla sales
ALTER TABLE pos_system_sales
ADD CONSTRAINT fk_sale_user
FOREIGN KEY (user_id)
REFERENCES pos_system_users(id);

ALTER TABLE pos_system_sales
ADD CONSTRAINT fk_sale_cash_opening
FOREIGN KEY (cash_opening_id)
REFERENCES pos_system_cash_openings(id);

ALTER TABLE pos_system_sales
ADD CONSTRAINT fk_sale_payment_method
FOREIGN KEY (payment_method_id)
REFERENCES pos_system_payment_methods(id);

-- relacion en la tabla sale detail
ALTER TABLE pos_system_sale_details
ADD CONSTRAINT fk_sale_detail_sale
FOREIGN KEY (sale_id)
REFERENCES pos_system_sales(id);

ALTER TABLE pos_system_sale_details
ADD CONSTRAINT fk_sale_detail_product
FOREIGN KEY (product_id)
REFERENCES pos_system_products(id);

-- relacion en la tabla cash opening
ALTER TABLE pos_system_cash_openings
ADD CONSTRAINT fk_cash_openings_cash_register
FOREIGN KEY (cash_register_id)
REFERENCES pos_system_cash_registers(id);

ALTER TABLE pos_system_cash_openings
ADD CONSTRAINT fk_cash_openings_user
FOREIGN KEY (user_id)
REFERENCES pos_system_users(id);

-- relacion en la tabla cash movements
ALTER TABLE pos_system_cash_movements
ADD CONSTRAINT fk_cash_movement_cash_openings
FOREIGN KEY (cash_opening_id)
REFERENCES pos_system_cash_openings(id);

-- relacion en la tabla inventory movements
ALTER TABLE pos_system_inventory_movements
ADD CONSTRAINT fk_inventory_movement_product
FOREIGN KEY (product_id)
REFERENCES pos_system_products(id);

ALTER TABLE pos_system_inventory_movements
ADD CONSTRAINT fk_inventory_movement_user
FOREIGN KEY (user_id)
REFERENCES pos_system_users(id);