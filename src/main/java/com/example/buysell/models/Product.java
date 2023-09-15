package com.example.buysell.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "SERIAL")
    private Long id;

    private String title;
    @Column(name = "description", columnDefinition = "text")
    private String descriptions;
    private double price;
    private String city;
    private String author;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
    private List<Image> images = new ArrayList<>();

    private Long previewImageId;

    @CreatedDate
    private LocalDateTime createdAt;



    public void addImage(Image image) {
        image.setProduct(this);
        images.add(image);
    }
}
