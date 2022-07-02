package br.com.delivery.app.Dto;

import br.com.delivery.app.Model.Additional;
import br.com.delivery.app.Model.Category;
import br.com.delivery.app.Model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

	private Long id_category;

	private String name;
	
    private List<Product> products;

    private List<Additional> additional = new ArrayList<>();

    public CategoryDTO(Category category) {
        super();
        this.id_category = category.getId_category();
        this.name        = category.getName();
        this.additional  = category.getAdditional();
        this.products    = category.getProducts();
    }
}
