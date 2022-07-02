package br.com.delivery.app.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "items")
public class Items implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_item;

    private String name;

    private BigDecimal price;

    private Integer quantity;

    private String observation;

    @OneToMany(
            targetEntity = ItemAdditional.class,
            fetch = FetchType.EAGER, cascade = CascadeType.ALL
    )
    private Set<ItemAdditional> item_additional;
}
