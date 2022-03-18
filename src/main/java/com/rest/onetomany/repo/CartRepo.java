package com.rest.onetomany.repo;

import com.rest.onetomany.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long> {
    @Query("from Cart c left outer join c.itemList")
    List<Cart> findWithItemList();
}
