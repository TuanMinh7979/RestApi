package com.rest.onetomany.service;

import com.rest.onetomany.exception.ResourceNotFoundException;
import com.rest.onetomany.model.Item;
import com.rest.onetomany.repo.ItemRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepo itemRepo;
    private final CartService cartService;

    public Item addItem(Item item, Long minorCartId) {
        Item itemsaved = itemRepo.save(item);
        //V là đảm k lặp code và nhất quán
        cartService.addItemtoCart(minorCartId, itemsaved.getId());

        return item;
    }

    public List<Item> getItems() {

        return itemRepo.findAll();
    }

    public Item getItem(Long id) {
        //get put delete exception handle thì bắt đầu tử hàm này
        return itemRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }
    //post thì là badRequestException() là đủ cho cả 4
    //getAll, không cần bắt cũng k sao , getBy(bắt)->put, delete, post(bắt)

    public Item deleteItem(Long id) {
        Item item = getItem(id);
        itemRepo.delete(item);
        return item;
        //để còn map về dto
    }

    @Transactional
    public Item editItem(Long id, Item item) {
        Item item1 = getItem(id);
        item1.setSerialNumber(item.getSerialNumber());
        //auto save voi
        return item1;
    }


}
