package com.example.item_services.model;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "items") // to map to mongodb collection 
public class Item {
// have unique id in each document in  mongodb 
    @Id 
    private String id;

    @NotNull(message = "Name is required") 
    @NotEmpty(message = "Name cannot be empty") 
    private String name;

    private String description; 

   

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
