package com.example.scriptreviewtool.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Role {
    @Id
    private String name;

    public Role() {
        // Constructeur par défaut requis par JPA
    }

    public Role(String name) {
        this.name = name;
    }

    public boolean hasPermission(String permission) {
        return name.equals("ADMIN") || name.equals("REVIEWER");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}