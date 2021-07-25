package the.wuxjian.feign.service;

import org.springframework.web.bind.annotation.*;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import the.wuxjian.feign.domain.Product;

@ReactiveFeignClient(name = "product-service", fallback = FeignServiceCallback.class)
public interface FeignService {

    @GetMapping("product")
    Flux<Product> getProducts();

    @GetMapping("product/{id}")
    Mono<Product> getProductById(@PathVariable String id);

    @PostMapping("product")
    Mono<Product> createProduct(@RequestBody Mono<Product> product);

    @DeleteMapping("product/{id}")
    Mono<Product> delete(@PathVariable String id);

}
