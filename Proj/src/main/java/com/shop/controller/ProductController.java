package com.shop.controller;

import com.shop.dto.ProductDTO;
import com.shop.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        ProductDTO created = productService.createProduct(productDTO);
        return ResponseEntity.created(URI.create("/products/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDTO productDTO) {
        return productService.updateProduct(id, productDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @GetMapping("/search/by-name")
    public List<ProductDTO> searchByName(@RequestParam String name) {
        return productService.searchByName(name);
    }

    @GetMapping("/category/{categoryId}")
    public List<ProductDTO> getProductsByCategory(@PathVariable Long categoryId) {
        return productService.getProductsByCategory(categoryId);
    }

    @GetMapping("/search/by-price")
    public List<ProductDTO> searchByPriceRange(
            @RequestParam Double minPrice,
            @RequestParam Double maxPrice) {
        return productService.searchByPriceRange(minPrice, maxPrice);
    }

    @GetMapping("/stock/low")
    public List<ProductDTO> getLowStockProducts(@RequestParam(defaultValue = "10") Integer threshold) {
        return productService.getLowStockProducts(threshold);
    }
}
