package cris_dev.pos_system.Product.Utils;

import cris_dev.pos_system.Product.Model.DTO.Response.ProductResponse;
import cris_dev.pos_system.Product.Model.Entity.ProductEntity;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class ProductTransform {
    public static List<ProductResponse> allProducts(List<ProductEntity> entity) {
        return entity
                .stream()
                .map(product -> new ProductResponse(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getSalePrice(),
                        product.getStock(),
                        product.getMinimumStock(),
                        product.getSlug(),
                        product.getSearchName(),
                        product.getSku(),
                        product.getUpc(),
                        product.getStatus()
                )).toList();
    }

    public static ProductResponse product(ProductEntity entity) {
        return new ProductResponse(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getSalePrice(),
                entity.getStock(),
                entity.getMinimumStock(),
                entity.getSlug(),
                entity.getSearchName(),
                entity.getSku(),
                entity.getUpc(),
                entity.getStatus()
        );
    }
}
