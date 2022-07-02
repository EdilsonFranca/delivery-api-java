package br.com.delivery.app.Service;

import java.util.List;
import java.util.stream.Collectors;

import br.com.delivery.app.Dto.AdditionalDTO;
import br.com.delivery.app.Dto.CategoryDTO;
import br.com.delivery.app.Model.Additional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.delivery.app.Model.Category;
import br.com.delivery.app.Repository.CategoryRepository;

import javax.persistence.EntityNotFoundException;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    public List<CategoryDTO> findCategory() {
        List<Category> list = repository.findAllByCategory();
        return list.stream().map(CategoryDTO::new).collect(Collectors.toList());
    }

    public List findNameCategory() {
        return repository.findNameCategory();
    }

    public List<CategoryDTO> findAll() {
        List<Category> list = repository.findAllByCategory();
        return list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public CategoryDTO update(Long id, CategoryDTO dto) {
        Category category = modelMapper.map(dto, Category.class);
        category.setId_category(id);
        category = repository.save(category);
        return modelMapper.map(category, CategoryDTO.class);
    }

    public CategoryDTO create(CategoryDTO categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        repository.save(category);
        return modelMapper.map(category, CategoryDTO.class);
    }

    public List<AdditionalDTO> get_additional(long id) {
        Category category = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        return category.getAdditional().stream().map(AdditionalDTO::new).collect(Collectors.toList());
    }

    public CategoryDTO update_additional(long id, AdditionalDTO dto) {
        Category category = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        category.addAdditional(modelMapper.map(dto, Additional.class));
        repository.save(category);
        return modelMapper.map(category, CategoryDTO.class);
    }
}


