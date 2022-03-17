package com.rest.onetomany.controller;

import com.rest.onetomany.model.Dto.ItemDto;
import com.rest.onetomany.model.Item;
import com.rest.onetomany.repo.ItemRepo;
import com.rest.onetomany.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.ldap.Rdn;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @PostMapping
    public ResponseEntity<ItemDto> addItem(@RequestBody final ItemDto itemDto) {
        //cái này cần có @Valid để check và nếu có gì không ổn thì BadRequestException
        Item item = itemService.addItem(Item.from(itemDto));
        //itemDto se la requestDto(có thể chứa id của Cart để chuyển thành Item)
        //nếu không thì cũng sẽ truyền riêng id theo kiều (dto, id) và trong mapper cũng sẽ phải (dto, id)->bad
        //hoặc là dùng Map<> thì lại dài dòng.

        //Cái này đúng ra return về ItemDto Response ResonseItemDto
        return new ResponseEntity<>(ItemDto.from(item), HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<ItemDto>> getItems() {
        List<Item> items = itemService.getItems();
        List<ItemDto> itemsDto = items.stream().map(ItemDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(itemsDto, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<ItemDto> getItem(@PathVariable final Long id) {
        Item item = itemService.getItem(id);
        return new ResponseEntity<>(ItemDto.from(item), HttpStatus.OK);

    }

    @DeleteMapping
    public ResponseEntity<Void> deleteItem(@RequestBody final Long[] ids) {
        for (Long idi : ids) {
            itemService.deleteItem(idi);
        }

        //render se don request neu status la 200 thi tra ve cho ngdung la thanh , nguoc lai thi la that bai
        //Không cần mesage rườm rà trong TH này
        //tat cả những gì cần bắt ta đã bắt or getOne trong service và post và put trong controller rồi
        //exeption khi lấy ra và exception khi đưa vào ta đã bắt hết
        //phát sinh lỗi khác thì sẽ là lỗi hệ thống cũng sẽ có handler lo chuyện đó.
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<ItemDto> deleteItems(@RequestBody final Long id) {
        Item item = itemService.deleteItem(id);
        return new ResponseEntity<>(ItemDto.from(item), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<ItemDto> editItem(@PathVariable final Long id, @RequestBody final ItemDto itemDto) {
        Item editItem = itemService.editItem(id, Item.from(itemDto));
        return new ResponseEntity<>(ItemDto.from(editItem), HttpStatus.OK);

    }

}
