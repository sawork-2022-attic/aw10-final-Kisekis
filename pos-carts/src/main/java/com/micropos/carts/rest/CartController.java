
package com.micropos.carts.rest;

import com.micropos.carts.api.CartsApi;
import com.micropos.carts.dto.CartDto;
import com.micropos.carts.dto.ItemDto;
import com.micropos.carts.mapper.CartMapper;
import com.micropos.carts.model.Cart;
import com.micropos.carts.model.Item;
import com.micropos.carts.service.CartService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class CartController implements CartsApi {

    private final CartMapper CartMapper;

    private final CartService CartService;

    @Autowired
    private Cart cart;


    public CartController(CartService cartService, CartMapper cartMapper) {
        this.CartService = cartService;
        this.CartMapper = cartMapper;
    }

    @Override
    public Mono<ResponseEntity<CartDto>> listItems(ServerWebExchange exchange){
        CartDto cartDto = CartMapper.toCartDto(this.cart);
        if (cartDto == null) {
            return Mono.just(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
        return Mono.just(new ResponseEntity<>(cartDto, HttpStatus.OK));
    }


    @Override
    public Mono<ResponseEntity<CartDto>> deleteItemById(String id, ServerWebExchange exchange){

        CartService.delete(cart,id);
        return Mono.just(new ResponseEntity<>(CartMapper.toCartDto(this.cart), HttpStatus.OK));
    }

    @Override
    public Mono<ResponseEntity<CartDto>> cartsCancelDelete(ServerWebExchange exchange) {
        CartService.cancel(cart);
        return Mono.just(new ResponseEntity<>(CartMapper.toCartDto(this.cart), HttpStatus.OK));

    }

    @Override
    public Mono<ResponseEntity<CartDto>> cartsCheckoutGet(ServerWebExchange exchange) {
        CartService.checkout(cart);
        return Mono.just(new ResponseEntity<>(CartMapper.toCartDto(this.cart), HttpStatus.OK));
    }



    @Override
    public Mono<ResponseEntity<CartDto>> addItemById(String id,ServerWebExchange exchange) {
        CartService.add(cart,id, 1);
        return Mono.just(new ResponseEntity<>(CartMapper.toCartDto(this.cart),HttpStatus.OK));
    }

}


