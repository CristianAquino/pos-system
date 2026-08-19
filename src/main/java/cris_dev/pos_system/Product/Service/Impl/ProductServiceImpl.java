package cris_dev.pos_system.Product.Service.Impl;

import cris_dev.pos_system.Product.Model.DTO.Request.ProductCreateRequest;
import cris_dev.pos_system.Product.Model.DTO.Request.ProductPatchRequest;
import cris_dev.pos_system.Product.Model.DTO.Response.ProductResponse;
import cris_dev.pos_system.Product.Model.Entity.ProductEntity;
import cris_dev.pos_system.Product.Repository.ProductRepository;
import cris_dev.pos_system.Product.Service.ProductService;
import cris_dev.pos_system.Product.Utils.ProductFunctions;
import cris_dev.pos_system.Product.Utils.ProductTransform;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    final ProductRepository productRepository;
    private ProductTransform productTransform;
    private ProductFunctions productFunctions;

    @Override
    public Page<ProductResponse> allProducts(
            String name,
            Pageable pageable) {
        if (name != null && !name.isBlank()) {
            String sn = productFunctions.searchName(name);
            return productRepository.findBySearchNameContaining(
                    sn,
                    pageable).map(ProductTransform::product);
        }
        return productRepository.findAll(pageable).map(ProductTransform::product);

    }

    @Override
    public List<ProductResponse> searchProducts(String search) {
        String sku = null;
        String upc = null;
        String sn = null;

        if (search.toLowerCase().startsWith("sku")) {
            sku = search;
        } else if (search.matches("\\d+")) {
            upc = search;
        } else {
            sn = "%" + productFunctions.searchName(search) + "%";
        }

        List<ProductEntity> products = productRepository.searchProducts(
                sku,
                upc,
                sn);
        return productTransform.allProducts(products);
    }

    @Override
    public ProductResponse getProduct(String slug) {
        ProductEntity product = productRepository.findBySlug(slug).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "producto " + slug + " no encontrado"));
        return productTransform.product(product);
    }

    @Override
    public String generateProduct() {
        List<ProductEntity> products = productRepository.findBySlugIsNull();
        for (ProductEntity product : products) {
            product.setSlug(productFunctions.slugProduct(product.getName()));
            product.setSearchName(productFunctions.searchName(product.getName()));
        }
        productRepository.saveAll(products);
        return "hecho";
    }

    @Transactional
    @Override
    public String createProduct(ProductCreateRequest payload) {
        String productName = productFunctions.searchName(payload.name());

        if (productRepository.existsBySearchName(productName)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Ya existe el producto " + payload.name());
        }

        ProductEntity product = new ProductEntity();

        product.setName(payload.name());
        product.setDescription(payload.description());
        product.setSalePrice(Objects.requireNonNullElse(
                payload.salePrice(),
                BigDecimal.ONE));
        product.setStock(Objects.requireNonNullElse(
                payload.stock(),
                0));
        product.setMinimumStock(Objects.requireNonNullElse(
                payload.minimumStock(),
                1));
        product.setSlug(productFunctions.slugProduct(payload.name()));
        product.setSearchName(productName);
        product.setSku(payload.sku());
        product.setUpc(payload.upc());
        product.setStatus(Objects.requireNonNullElse(
                payload.status(),
                "A"));

        productRepository.save(product);

        return "producto " + payload.name() + " creado con exito";
    }

    @Transactional
    @Override
    public ProductResponse updateProduct(
            ProductPatchRequest payload) {
        ProductEntity product = productRepository.findById(payload.id()).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "producto no encontrado"));

        if (payload.name() != null) {
            product.setName(payload.name());
            product.setSlug(productFunctions.slugProduct(payload.name()));
        }
        if (payload.description() != null) {
            product.setDescription(payload.description());
        }
        if (payload.salePrice() != null) {
            product.setSalePrice(payload.salePrice());
        }
        if (payload.stock() != null) {
            if (payload.stock().equals(0)) {
                product.setStatus("I");
            } else if (payload.stock() > 0 && product.getStatus().equals("I")) {
                product.setStatus("A");
            }
            product.setStock(payload.stock());
        }
        if (payload.minimumStock() != null) {
            product.setMinimumStock(payload.minimumStock());
        }
        if (payload.sku() != null) {
            product.setSku(payload.sku());
        }
        if (payload.upc() != null) {
            product.setUpc(payload.upc());
        }
        if (payload.status() != null) {
            product.setStatus(payload.status());
        }

        ProductEntity nProduct = productRepository.save(product);

        return productTransform.product(nProduct);
    }
}
