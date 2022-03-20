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

        item.setSerialNumber(itemDto.getSerialNumber());


        return item;
    }

    @ManyToOne
    private Cart cart;

}
