package com.rest.onetomany.repo;

import com.rest.onetomany.model.Cart;
import com.rest.onetomany.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepo extends JpaRepository<Item, Long> {

}
