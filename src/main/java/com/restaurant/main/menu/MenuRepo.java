package com.restaurant.main.menu;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface MenuRepo extends CrudRepository<Menu,Long>{
}
