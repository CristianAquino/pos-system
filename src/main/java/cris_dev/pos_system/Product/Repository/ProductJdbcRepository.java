package cris_dev.pos_system.Product.Repository;

import cris_dev.pos_system.Product.Model.Entity.ProductEntity;

import java.util.List;

public interface ProductJdbcRepository {
    public List<ProductEntity> productsJdbc(
            String sku,
            String upc,
            String sName);
}
