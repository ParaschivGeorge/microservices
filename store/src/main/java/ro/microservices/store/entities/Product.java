package ro.microservices.store.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String code;
    private String name;
    private Boolean isPublished;

    @ManyToOne
    private Category category;

}
