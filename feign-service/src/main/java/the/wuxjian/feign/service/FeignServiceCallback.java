package the.wuxjian.feign.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import the.wuxjian.feign.domain.Product;

@Service
public class FeignServiceCallback implements FeignService{
    Product defaultProduct = Product.builder().id("-1").build();

    @Override
    public Flux<Product> getProducts() {
        return Flux.just(defaultProduct);
    }

    @Override
    public Mono<Product> getProductById(String id) {
        return Mono.just(defaultProduct);
    }

    @Override
    public Mono<Product> createProduct(Mono<Product> product) {
        return Mono.just(defaultProduct);
    }

    @Override
    public Mono<Product> delete(String id) {
        return Mono.just(defaultProduct);
    }
}
