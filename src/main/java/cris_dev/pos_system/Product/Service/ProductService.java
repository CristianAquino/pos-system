package cris_dev.pos_system.Product.Service;

import cris_dev.pos_system.Product.Model.DTO.Request.ProductCreateRequest;
import cris_dev.pos_system.Product.Model.DTO.Request.ProductPatchRequest;
import cris_dev.pos_system.Product.Model.DTO.Response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    public Page<ProductResponse> allProducts(
            String name,
            Pageable pageable);

    public List<ProductResponse> searchProducts(String search);

    public ProductResponse getProduct(String slug);

    public String generateProduct();

    public String createProduct(ProductCreateRequest payload);

    public ProductResponse updateProduct(
            ProductPatchRequest payload);

}
