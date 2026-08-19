package cris_dev.pos_system.Product.Controller;

import cris_dev.pos_system.Product.Model.DTO.Request.ProductCreateRequest;
import cris_dev.pos_system.Product.Model.DTO.Request.ProductPatchRequest;
import cris_dev.pos_system.Product.Model.DTO.Response.ProductResponse;
import cris_dev.pos_system.Product.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("product")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<Page<ProductResponse>> allProducts(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(
                page - 1,
                size);
        Page<ProductResponse> products = productService.allProducts(
                name,
                pageable);
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> searchProducts(@RequestParam String q) {
        List<ProductResponse> products = productService.searchProducts(q);
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/{slug}")
    public ResponseEntity<ProductResponse> productById(@PathVariable String slug) {
        ProductResponse product = productService.getProduct(slug);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    // metodo auxiliar
    @GetMapping("/generate")
    public ResponseEntity<String> generateSlug() {
        String msg = productService.generateProduct();
        return ResponseEntity.status(HttpStatus.OK).body(msg);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createProduct(@Valid @RequestBody ProductCreateRequest payload) {
        String msg = productService.createProduct(payload);
        return ResponseEntity.status(HttpStatus.CREATED).body(msg);
    }

    @PatchMapping("/update")
    public ResponseEntity<ProductResponse> patchProduct(
            @Valid @RequestBody ProductPatchRequest payload) {
        ProductResponse product = productService.updateProduct(payload);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(product);
    }
}
