package com.br.studysecurity.controller;

import com.br.studysecurity.entity.ProductDTO;
import com.br.studysecurity.entity.Products;
import com.br.studysecurity.repository.ProductRepository;
import com.br.studysecurity.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/teste")
public class ProductsController {

    private final ProductRepository productRepository;
    private final ProductService productService;

    public ProductsController(ProductRepository productRepository, ProductService productService) {
        this.productRepository = productRepository;
        this.productService = productService;
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping
    public ResponseEntity<List<Products>> getProducts(){
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Products> getProduct(@PathVariable  UUID id){
        return ResponseEntity.ok().body(productService.getProduct(id));
    }

    @PostMapping
    public ResponseEntity<Products> createProduct(@RequestBody ProductDTO product, UriComponentsBuilder ucb){
        var product1 = new Products();
        BeanUtils.copyProperties(product, product1);
        productService.saveProduct(product1);

        URI locationOfNewProduct = ucb.path("teste/{id}")
                .buildAndExpand(product1.getId())
                .toUri();

        return ResponseEntity.created(locationOfNewProduct).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Products> updateProduct(@PathVariable UUID id, @RequestBody ProductDTO product) {
        return productRepository.findById(id)
                .map(product1 -> {BeanUtils.copyProperties(product, product1);
                    return new ResponseEntity<>(productService.saveProduct(product1), HttpStatus.OK);})
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable UUID id){
        Optional<Products> produtoO = productRepository.findById(id);
        if (produtoO.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.delete(produtoO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted!");
    }
}
