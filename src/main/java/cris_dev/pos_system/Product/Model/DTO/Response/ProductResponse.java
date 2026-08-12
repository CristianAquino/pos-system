package cris_dev.pos_system.Product.Model.DTO.Response;

import java.math.BigDecimal;

public record ProductResponse(
        Long id,
        String name,
        String description,
        BigDecimal salePrice,
        Integer stock,
        Integer minimumStock,
        String slug,
        String searchName,
        String sku,
        String upc,
        String status
) {
}
