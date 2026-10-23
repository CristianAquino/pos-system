package cris_dev.pos_system.Product.Service;

import cris_dev.pos_system.Product.Model.DTO.Request.ProductCreateRequest;
import cris_dev.pos_system.Product.Model.DTO.Request.ProductPatchRequest;
import cris_dev.pos_system.Product.Model.DTO.Response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    Page<ProductResponse> allProducts(
            String name,
            Pageable pageable);

    Page<ProductResponse> allProductsSoftDelete(
            Pageable pageable);

    List<ProductResponse> searchProducts(String search);

    ProductResponse getProduct(String slug);

    String generateProduct();

    String createProduct(ProductCreateRequest payload);

    ProductResponse updateProduct(
            ProductPatchRequest payload);

}
