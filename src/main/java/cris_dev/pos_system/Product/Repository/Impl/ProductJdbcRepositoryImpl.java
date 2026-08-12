package cris_dev.pos_system.Product.Repository.Impl;

import cris_dev.pos_system.Product.Model.Entity.ProductEntity;
import cris_dev.pos_system.Product.Repository.ProductJdbcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ProductJdbcRepositoryImpl implements ProductJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<ProductEntity> productsJdbc(
            String sku,
            String upc,
            String sName) {
        String sql = """
                SELECT *
                FROM fn_search_product(?, ?, ?)
                """;

        return jdbcTemplate.query(
                sql,
                BeanPropertyRowMapper.newInstance(ProductEntity.class),
                sku,
                upc,
                sName);
    }
}
