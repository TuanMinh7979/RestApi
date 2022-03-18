package com.rest.onetomany.model.Dto;

import com.rest.onetomany.model.Cart;
import com.rest.onetomany.model.Item;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class CartDto {
    private Long id;
    private String name;
    //(-1) Đây sẽ là lúc ta lấy thêm itemList với Dto(và do 2 undirectional dto nên không bị lỗi parse Json)
    private List<ItemDto> itemsDto = new ArrayList<>();

    public static CartDto from(Cart cart) {
        //chỉ dùng đến khi lưu nên không cần id

        CartDto cartDto = new CartDto();
        cartDto.setId(cart.getId());
        cartDto.setName(cart.getName());
        //itemDto >< item
      //  System.out.println("SZE LAF" + cart.getItemList());
        //Khong lay len dc do lazy, muon lay ta phai viet service ben many theo cartid

        cartDto.setItemsDto(cart.getItemList().stream().map(ItemDto::from).collect(Collectors.toList()));
        return cartDto;
    }


}
