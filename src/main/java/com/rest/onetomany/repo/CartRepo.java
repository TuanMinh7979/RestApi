package com.rest.onetomany.repo;

import com.rest.onetomany.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long> {
}
