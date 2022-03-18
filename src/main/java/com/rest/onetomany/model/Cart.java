package com.rest.onetomany.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rest.onetomany.model.Dto.CartDto;
import com.rest.onetomany.model.Dto.PlainCartDto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
@Data

public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    @JsonIgnore
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