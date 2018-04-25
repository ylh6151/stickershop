package com.boot.stickershop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {
    public Product() { regtime = LocalDateTime.now(); }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String price;
    private String size;
    private String imagePath;

    @ManyToOne(targetEntity = ProductCategory.class,fetch=FetchType.LAZY)
    @JoinColumn(name="category_id")
    private int categoryId;
    private LocalDateTime regtime;
    private LocalDateTime edittime;  // 수정날짜

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ProductFile> productFiles = new ArrayList<>();

    public void addProductFile(ProductFile productFile){
        this.productFiles.add(productFile);
        if(productFile.getProduct()!=this){
            productFile.setProduct(this);
        }
    }

}
