package com.micropos.products.rest;

import com.micropos.products.api.ProductsApi;
import com.micropos.products.dto.ProductDto;
import com.micropos.products.mapper.ProductMapper;
import com.micropos.products.service.ProductService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController implements ProductsApi {

    private final ProductMapper productMapper;

    private final ProductService productService;


    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productMapper = productMapper;
        this.productService = productService;
    }


    @Override
    public Mono<ResponseEntity<Flux<ProductDto>>> listProducts(ServerWebExchange exchange){
        List<ProductDto> products = new ArrayList<>(productMapper.toProductsDto(this.productService.products()));
        if (products.isEmpty()) {
            return Mono.just(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
        return Mono.just(new ResponseEntity<>(Flux.fromIterable(products), HttpStatus.OK));
    }

    @Override
    public Mono<ResponseEntity<Flux<ProductDto>>> searchProducts(String name,
            ServerWebExchange exchange) {
        List<ProductDto> products = new ArrayList<>(productMapper.toProductsDto(this.productService.searchProducts(name)));
        if (products.isEmpty()) {
            return Mono.just(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
        return Mono.just(new ResponseEntity<>(Flux.fromIterable(products), HttpStatus.OK));
    }


    @Override
    public Mono<ResponseEntity<ProductDto>> showProductById(String id, ServerWebExchange exchange){
        ProductDto product = productMapper.toProductDto(this.productService.getProduct(id));
        if (product == null) {
            return Mono.just(new ResponseEntity<>(HttpStatus.NOT_FOUND)) ;
        }
        return Mono.just(new ResponseEntity<>(product, HttpStatus.OK));
    }
}
