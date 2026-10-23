package cris_dev.pos_system.Product.Utils;

import cris_dev.pos_system.Product.Model.DTO.Response.ProductResponse;
import cris_dev.pos_system.Product.Model.Entity.ProductEntity;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class ProductTransform {
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
                entity.getStatus());
    }

    public static List<ProductResponse> productList(List<ProductEntity> entity) {
        return entity.stream().map(ProductTransform::product).toList();
    }
}
