package br.com.delivery.app.Controller;

import br.com.delivery.app.Dto.AdditionalDTO;
import br.com.delivery.app.Service.AdditionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@CrossOrigin(origins="*",maxAge = 3600)
@RestController
@RequestMapping({"/additional/"})
public class AdditionalController {
	
	@Autowired
	private AdditionalService service;

	@PutMapping("/{id}")
	public ResponseEntity<AdditionalDTO> update(@PathVariable @NotNull Long id,
												@RequestBody @Valid AdditionalDTO dto) {
		AdditionalDTO Additional = service.update(id, dto);
		return ResponseEntity.ok(Additional);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable @NotNull Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
