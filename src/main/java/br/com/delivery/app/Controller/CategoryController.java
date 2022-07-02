package br.com.delivery.app.Controller;

import java.net.URI;
import java.util.List;

import br.com.delivery.app.Dto.AdditionalDTO;
import br.com.delivery.app.Dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.delivery.app.Service.CategoryService;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping({"/category"})
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping()
    public ResponseEntity<List<CategoryDTO>> category() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}/product")
    public ResponseEntity<List<CategoryDTO>> findAllByCategory() {
        List<CategoryDTO> list = service.findCategory();
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable @NotNull Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/name")
    public ResponseEntity<List<CategoryDTO>> findNameCategory() {
        List<CategoryDTO> list = service.findNameCategory();
        return ResponseEntity.ok(list);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable @NotNull Long id,
                                              @RequestBody @Valid CategoryDTO dto) {
        CategoryDTO category = service.update(id, dto);
        return ResponseEntity.ok(category);
    }


    @PutMapping("/{id}/additional")
    public ResponseEntity<CategoryDTO> add_additional(@PathVariable("id") long id,
                                                      @RequestBody AdditionalDTO dto) {

        CategoryDTO categoryDTO = service.update_additional(id, dto);
        return ResponseEntity.ok(categoryDTO);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> create(@RequestBody CategoryDTO categoryDto, UriComponentsBuilder uriBuilder) {

        CategoryDTO category = service.create(categoryDto);

        URI address = uriBuilder.path("/category/{id}").buildAndExpand(category.getId_category()).toUri();

        return ResponseEntity.created(address).body(category);
    }

    @GetMapping("{id}/additional")
    public ResponseEntity<List<AdditionalDTO>> all_additional(@PathVariable("id") long id) {
        List<AdditionalDTO> list = service.get_additional(id);
        return ResponseEntity.ok(list);
    }
}
