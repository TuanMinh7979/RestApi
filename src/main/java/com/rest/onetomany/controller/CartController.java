package com.rest.onetomany.controller;

import com.rest.onetomany.model.Cart;
import com.rest.onetomany.model.Dto.CartDto;
import com.rest.onetomany.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping
    public ResponseEntity<CartDto> addCart(@RequestBody CartDto cartDto) {
        //if not valid throw exception ngay tại đây.
        Cart cart = cartService.addCart(Cart.from(cartDto));
        //các việc xử lý logic hay liên quan đến service khác thì nằm trong service
        return new ResponseEntity<>(CartDto.from(cart), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CartDto>> getCarts() {
        List<Cart> carts = cartService.getCarts();
        List<CartDto> cartDtos = carts.stream().map(CartDto::from).collect(Collectors.toList());

        return new ResponseEntity<>(cartDtos, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<CartDto> getCart(@PathVariable Long id) {
        // đã bat ngoại lệ bên trong service ta chỉ việc trả về
        return new ResponseEntity<>(CartDto.from(cartService.getCart(id)), HttpStatus.OK);

    }


    @DeleteMapping(value = "{id}")
    public ResponseEntity<CartDto> deleteCart(@PathVariable Long id) {
        // đã bat ngoại lệ bên trong service ta chỉ việc trả về
        return new ResponseEntity<>(CartDto.from(cartService.deleteCart(id)), HttpStatus.OK);

    }

    @DeleteMapping
    public ResponseEntity<Void> deleteCarts(@RequestBody Long[] ids) {
        for (Long idi : ids) {
            cartService.deleteCart(idi);
        }
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<CartDto> editCart(@PathVariable Long id, @RequestBody CartDto cartDto) {
        Cart cart = cartService.edit(id, Cart.from(cartDto));
        return new ResponseEntity<>(CartDto.from(cart), HttpStatus.OK);
    }

    @PostMapping(value = "{cartId}/items/{itemId}/add")
    public ResponseEntity<CartDto> addItemToCart(
            @PathVariable final Long cartId,
            @PathVariable final Long itemId
    ) {
        Cart cart = cartService.addItemtoCart(cartId, itemId);
        return new ResponseEntity<>(CartDto.from(cart), HttpStatus.OK);
    }

    @PostMapping(value = "{cartId}/items/{itemId}/remove")
    public ResponseEntity<CartDto> removeItemFromCart(
            @PathVariable final Long cartId,
            @PathVariable final Long itemId
    ) {
        Cart cart = cartService.removeItemFromCart(cartId, itemId);
        return new ResponseEntity<>(CartDto.from(cart), HttpStatus.OK);
    }


}
