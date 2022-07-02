package br.com.delivery.app.Controller;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import br.com.delivery.app.Dto.ProductDTO;
import br.com.delivery.app.Service.ProductService;
import org.springframework.web.util.UriComponentsBuilder;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping()
    public ResponseEntity<List<ProductDTO>> list() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/spotlight")
    public ResponseEntity<List<ProductDTO>> findAllBySpotlight() {
        return ResponseEntity.ok(service.find());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable @NotNull Long id) {
        ProductDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> create(@RequestParam(value = "photo", required = false) MultipartFile multipartFile,
                                             @Valid @RequestBody ProductDTO productDto,
                                             UriComponentsBuilder uriBuilder) throws IOException {

        ProductDTO productDTO = service.create(productDto, multipartFile);

        URI address = uriBuilder.path("/product/{id}").buildAndExpand(productDTO.getId()).toUri();

        return ResponseEntity.created(address).body(productDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable @NotNull Long id,
                                             @RequestParam(value = "photo", required = false) MultipartFile multipartFile,
                                             @RequestBody @Valid ProductDTO dto) throws IOException {
        ProductDTO productDTO = service.update(id, dto, multipartFile);
        return ResponseEntity.ok(productDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable @NotNull Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
