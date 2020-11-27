package com.vyasswaroop.dropwizard.introduction.domain;

public class Brand {
    private final Long id;
    private final String name;

    public Brand(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
