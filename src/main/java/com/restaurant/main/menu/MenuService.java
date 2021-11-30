package com.restaurant.main.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.map.repository.config.EnableMapRepositories;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@EnableMapRepositories
public class MenuService {

    private final MenuRepo menuRepo;

    public MenuService(MenuRepo repo)
    {
        this.menuRepo =repo;
    }

    public Menu create(Menu item) {

          return  menuRepo.save(item);
    }

    public Menu find(Long id)
    {
        return menuRepo.findById(id).orElse(null);
    }

    public void remove(Long id) {
        menuRepo.deleteById(id);
    }

}
