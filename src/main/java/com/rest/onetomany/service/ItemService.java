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
//    private final CartService cartService;

    public Item addItem(Item item) {
        Item itemsaved = itemRepo.save(item);


        return item;
    }

    public List<Item> getItems() {

        return itemRepo.findAll();
    }

    public Item getItem(Long id) {

        return itemRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }


    public Item deleteItem(Long id) {
        Item item = getItem(id);
        itemRepo.delete(item);
        return item;

    }

    @Transactional
    public Item editItem(Long id, Item item) {
        Item item1 = getItem(id);
        item1.setSerialNumber(item.getSerialNumber());
        //auto save voi
        return item1;
    }


}
