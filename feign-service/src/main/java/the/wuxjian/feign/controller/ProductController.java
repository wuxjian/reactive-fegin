package the.wuxjian.feign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import the.wuxjian.feign.domain.Product;
import the.wuxjian.feign.service.FeignService;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private FeignService feignService;

    @GetMapping("")
    public Flux<Product> getProducts() {
        return feignService.getProducts();
    }

    @GetMapping("/{id}")
    public Mono<Product> getProductById(@PathVariable String id) {
        return feignService.getProductById(id);
    }

    @PostMapping("")
    public Mono<Product> createProduct(@RequestBody Product product) {
        return feignService.createProduct(Mono.just(product));
    }

    @DeleteMapping("/{id}")
    public Mono<Product> delete(@PathVariable String id) {
        return feignService.delete(id);
    }
}
