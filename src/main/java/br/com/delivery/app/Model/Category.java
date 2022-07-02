package br.com.delivery.app.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "categorys")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id_category;

    @NotBlank(message = "Por favor entre com o nome")
    private String name;

    @JsonIgnore
    @OneToMany(
            targetEntity = Product.class,
            mappedBy = "category", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Product> products;

    @OneToMany(
            targetEntity = Additional.class,
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    private List<Additional> additional;

    public void addAdditional(Additional additional) {
        this.additional.add(additional);
    }
}
