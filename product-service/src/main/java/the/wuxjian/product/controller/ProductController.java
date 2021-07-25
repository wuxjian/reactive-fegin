package the.wuxjian.product.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import the.wuxjian.product.domain.Product;
import the.wuxjian.product.service.ProductService;

import java.time.Duration;

@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public Flux<Product> getProducts() {
        log.info("getProducts");
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public Mono<Product> getProductById(@PathVariable String id) {
        log.info("getProductById");
        // 3秒后返回
        return Mono.delay(Duration.ofSeconds(3))
                .then(productService.getProductById(id));
    }

    @PostMapping("")
    public Mono<Product> createProduct(@RequestBody Mono<Product> product) {
        log.info("createProduct");
        return productService.createOrUpdateProduct(product);
    }

    @DeleteMapping("/{id}")
    public Mono<Product> delete(@PathVariable String id) {
        log.info("delete");
        return productService.deleteProduct(id);
    }
}
