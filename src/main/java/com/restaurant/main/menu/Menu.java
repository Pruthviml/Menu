package com.restaurant.main.menu;

import org.springframework.data.annotation.Id;

public class Menu {
    @Id
    private final Long id;
    private final String name;
    private final String description;
    private final Long price;

    public Menu(Long id, String name, String description, Long price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Long getPrice() {
        return price;
    }

    public Menu updateWith(Menu menu) {
        return new Menu(
                this.id,
                menu.name,
                menu.description,
                menu.price
        );
    }
}
