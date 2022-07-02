package br.com.delivery.app.Service;

import br.com.delivery.app.Dto.OrderDTO;
import br.com.delivery.app.Model.Order;
import br.com.delivery.app.Repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    public OrderDTO update(Long id, OrderDTO orderDto) {

        Order order = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());

        order.setStatus(orderDto.getStatus());
        repository.save(order);
        return modelMapper.map(order, OrderDTO.class);
    }

    public OrderDTO create(OrderDTO orderDto) {
        Order order = modelMapper.map(orderDto, Order.class);
        repository.save(order);
        return modelMapper.map(order, OrderDTO.class);
    }

    public List<OrderDTO> allByStatus() {
        return repository.allByStatus().stream().map(OrderDTO::new).collect(Collectors.toList());
    }

    public OrderDTO findById(long id) {
        Order order = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        return modelMapper.map(order, OrderDTO.class);
    }
}


