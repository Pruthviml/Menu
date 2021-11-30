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
            if(menuRepo.existsById(item.getId()))
                return null;
          return  menuRepo.save(item);
    }

    public Menu find(Long id)
    {
        return menuRepo.findById(id).orElse(null);
    }

    public boolean remove(Long id) {
        if(menuRepo.existsById(id)) {
           menuRepo.deleteById(id);
           return true;
        }
        else
            return false;

    }

    public Optional<Menu> update(Long id, Menu item) {
        if(menuRepo.existsById(id)) {
            return menuRepo.findById(id)
                    .map(oldItem -> {
                        Menu updated = oldItem.updateWith(item);
                        return menuRepo.save(updated);
                    });

        }
        return null;
    }
}
