package br.com.delivery.app.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.delivery.app.Model.Category;
import br.com.delivery.app.Repository.CategoryRepository;
import br.com.delivery.app.Util.FileUploadUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.delivery.app.Dto.ProductDTO;
import br.com.delivery.app.Model.Product;
import br.com.delivery.app.Repository.ProductRepository;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;

@Service
public class ProductService {
    private static String UPLOADED_FOLDER = "src/main/resources/static/photos";
    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryRepository repository_category;
    @Autowired
    private ModelMapper modelMapper;

    public Page<ProductDTO> getAll(Pageable pagination) {
        return repository.findAll(pagination).map(p -> modelMapper.map(p, ProductDTO.class));
    }

    public List<ProductDTO> find() {
        return repository.findAllSpotlight().stream().map(ProductDTO::new).collect(Collectors.toList());
    }

    public List<ProductDTO> findAll() {
        return repository.findAll().stream().map(ProductDTO::new).collect(Collectors.toList());
    }

    public ProductDTO findById(long id) {
        Product product = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        return modelMapper.map(product, ProductDTO.class);
    }

    public ProductDTO create(ProductDTO productDto, MultipartFile multipartFile) throws IOException {

        if (multipartFile != null && !multipartFile.isEmpty()) {
            String fileName = new Date().getTime() + StringUtils.cleanPath(multipartFile.getOriginalFilename());
            productDto.setPhoto(fileName);
            FileUploadUtil.saveFile(UPLOADED_FOLDER, fileName, multipartFile);
        }

        Optional<Category> category = repository_category.findById(productDto.getId_category());
        Product product = modelMapper.map(productDto, Product.class);
        product.setCategory(category.get());
        repository.save(product);
        return modelMapper.map(product, ProductDTO.class);
    }

    public ProductDTO update(Long id, ProductDTO productDto, MultipartFile multipartFile) throws IOException {

        if (multipartFile != null && !multipartFile.isEmpty()) {
            String fileName = new Date().getTime() + StringUtils.cleanPath(multipartFile.getOriginalFilename());
            productDto.setPhoto(fileName);
            FileUploadUtil.saveFile(UPLOADED_FOLDER, fileName, multipartFile);
        }

        Optional<Category> category = repository_category.findById(productDto.getId_category());
        Product product = modelMapper.map(productDto, Product.class);
        product.setCategory(category.get());
        product.setId(id);
        product = repository.save(product);
        return modelMapper.map(product, ProductDTO.class);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}