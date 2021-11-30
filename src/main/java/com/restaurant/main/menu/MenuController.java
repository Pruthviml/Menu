package com.restaurant.main.menu;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/menu")
public class MenuController {


    private final MenuService service;

    public MenuController(MenuService service)
    {
        this.service =service;
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Menu item) {
        Menu created = service.create(item);
        if(created == null)
            return ResponseEntity.status(HttpStatus.IM_USED).body("Id already exists");

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Id created");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable("id") Long id)
    {
        Menu item = service.find(id);
        if(item == null )
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Menu item for the particular Id doesnot exists");

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(item);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        if(service.remove(id))
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Menu item deleted");
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Menu item not found, hence deletion operation not processed");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(
            @PathVariable("id") Long id,
            @RequestBody Menu updatedItem) {

        if(service.update(id, updatedItem) != null )
            return ResponseEntity.status(HttpStatus.OK).body("Menu item updated");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("ID not found, hence update operation not processed");
    }

}
