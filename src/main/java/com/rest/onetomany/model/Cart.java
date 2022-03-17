package com.rest.onetomany.model;

import com.rest.onetomany.model.Dto.CartDto;
import com.rest.onetomany.model.Dto.PlainCartDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
@Getter
@Setter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
//    @JoinColumn(name="card_id")
    private List<Item> itemList = new ArrayList<>();

    //ĐỂ DÀNH DÙNG BÊN SERVICE VÀ CÁC BÊN KHÁC
    public void addItem(Item item) {
        itemList.add(item);
    }

    //ĐỂ DÀNH DÙNG BÊN SERVICE VÀ CÁC BÊN KHÁC
    public void removeItem(Item item) {
        itemList.remove(item);
    }

    public static Cart from(CartDto cartDto) {
        Cart cart = new Cart();
        cart.setName(cartDto.getName());
        return cart;
    }


}