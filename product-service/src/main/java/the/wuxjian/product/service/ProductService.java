package the.wuxjian.product.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import the.wuxjian.product.domain.Product;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ProductService {
    private final Map<String, Product> products = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        Product p1 = Product.builder().id("1").productName("手机").price(1000F).build();
        products.put("1", p1);
        Product p2 = Product.builder().id("2").productName("pc").price(600F).build();
        products.put("2", p2);
    }

    public Flux<Product> getProducts() {
        return Flux.fromIterable(this.products.values());
    }

    public Flux<Product> getProductsByIds(Flux<String> ids) {
        // return ids.map(products::get);
        return ids.flatMap(id -> Mono.justOrEmpty(this.products.get(id)));
    }

    public Mono<Product> getProductById(String id) {
        return Mono.justOrEmpty(products.get(id));
    }

    public Mono<Product> createOrUpdateProduct(Mono<Product> productMono) {
        return productMono
                .doOnNext(p -> products.put(p.getId(), p));
    }

    public Mono<Product> deleteProduct(String id) {
        return Mono.justOrEmpty(products.remove(id));
    }
}
