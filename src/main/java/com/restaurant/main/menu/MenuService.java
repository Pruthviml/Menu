package com.restaurant.main.menu;

import org.springframework.data.map.repository.config.EnableMapRepositories;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public Optional<Menu> update(Long id, Menu item) {
        return menuRepo.findById(id)
                .map(oldItem -> {
                    Menu updated = oldItem.updateWith(item);
                    return menuRepo.save(updated);
                });
    }
}
