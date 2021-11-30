package com.restaurant.main.menu;

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
    public ResponseEntity<Menu> create(@RequestBody Menu item) {
        Menu created = service.create(item);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
                return ResponseEntity.created(location).body(created);
    }

    @GetMapping("/{id}")
    public Menu get(@PathVariable("id") Long id)
    {
        return service.find(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        service.remove(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Menu> update(
            @PathVariable("id") Long id,
            @RequestBody Menu updatedItem) {

        Optional<Menu> updated = service.update(id, updatedItem);

        return updated
                .map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> {
                    Menu created = service.create(updatedItem);
                    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(created.getId())
                            .toUri();
                    return ResponseEntity.created(location).body(created);
                });
    }

}
