package com.rest.onetomany.model;

import com.rest.onetomany.model.Dto.ItemDto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "items")
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String serialNumber;


    public static Item from(ItemDto itemDto) {
        Item item = new Item();
//        item.setId(itemDto.getId());
        //chỉ cần tử Dto sang entity khi mà tạo mới hoặc edit cái đang tọa mới mà thôi
        //nói chung tạo mới nên id tự tăng
        item.setSerialNumber(itemDto.getSerialNumber());
        //
        //viec nay can co 1 lop mapper dam nhiem de khong phai lien quan hay inject cac class khac
        //trong Entity Item nay
//        item.setCart(CartService.getCart(itemDto.getPlainCartDto().getId()));

        return item;
    }

    @ManyToOne
    private Cart cart;

}
