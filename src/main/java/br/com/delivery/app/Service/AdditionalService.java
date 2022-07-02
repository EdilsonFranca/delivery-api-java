package br.com.delivery.app.Service;

import br.com.delivery.app.Dto.AdditionalDTO;
import br.com.delivery.app.Model.Additional;
import br.com.delivery.app.Repository.AdditionalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdditionalService {

    @Autowired
    private AdditionalRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public AdditionalDTO update(Long id, AdditionalDTO dto) {
        Additional Additional = modelMapper.map(dto, Additional.class);
        Additional.setId_additional(id);
        Additional = repository.save(Additional);
        return modelMapper.map(Additional, AdditionalDTO.class);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
