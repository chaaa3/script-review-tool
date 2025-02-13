package com.example.scriptreviewtool.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Status {
    @Id
    private String name;

    public Status() {
        // Constructeur par défaut requis par JPA
    }

    public Status(String name) {
        this.name = name;
    }

    public boolean isFinal() {
        return name.equals("REVIEWED");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}