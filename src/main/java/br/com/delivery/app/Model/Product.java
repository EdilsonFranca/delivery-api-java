package br.com.delivery.app.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private BigDecimal price;

    private BigDecimal promotion_price;

    private String description;

    private boolean spotlight;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "id_category",
            referencedColumnName = "id_category",
            insertable = true,
            updatable = true
    )
    private Category category;

    @Column(nullable = true, length = 64)
    private String photo;

}
