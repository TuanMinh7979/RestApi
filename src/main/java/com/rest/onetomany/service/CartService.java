package com.rest.onetomany.service;

import com.rest.onetomany.exception.ItemIsAssignedException;
import com.rest.onetomany.exception.ResourceNotFoundException;
import com.rest.onetomany.model.Cart;
import com.rest.onetomany.model.Item;
import com.rest.onetomany.repo.CartRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class
CartService {
    private final CartRepo cartRepo;
    private final ItemService itemService;

    public Cart addCart(Cart newCart) {
        //post mapppin bên controller nếu có gì không gì không hợp lý
        //ta sẽ quăng exeption ở ngoài dto luôn ở đây ta chỉ save thôi
        return cartRepo.save(newCart);
    }

    public List<Cart> getCarts() {
        //TH la json ignore len k can lay them ItemList(1)
        return cartRepo.findAll();
    }

    public Cart getCart(Long id) {
        return cartRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Cart deleteCart(Long id) {
        Cart carttodel = getCart(id);
        cartRepo.delete(carttodel);
        return carttodel;
    }

    @Transactional
    public Cart edit(Long id, Cart cart) {
        Cart cartToEdit = getCart(id);
        cartToEdit.setName(cart.getName());
        cartToEdit.setItemList(cart.getItemList());
        return cartToEdit;

    }

    @Transactional
    public Cart addItemtoCart(Long cartId, Long itemId) {
        //ont to many thi co the thiet ket add many to one trong Service cua one luon
        Cart cart = getCart(cartId);
        //gọi service chứ repo sẽ không nên nằm ở service khác ngoài service của nó
        Item item = itemService.getItem(itemId);
        cart.addItem(item);
        return cart;
    }

    @Transactional
    public Cart removeItemFromCart(Long cartId, Long itemId) {
        Cart cart = getCart(cartId);
        Item item = itemService.getItem(itemId);
        if (Objects.nonNull(item.getCart())) {
            throw new ItemIsAssignedException(itemId, item.getCart().getId());
        }
        cart.removeItem(item);
        return cart;
    }


}
