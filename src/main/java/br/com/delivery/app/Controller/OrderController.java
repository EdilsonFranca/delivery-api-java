package br.com.delivery.app.Controller;

import br.com.delivery.app.Dto.OrderDTO;
import br.com.delivery.app.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping(value = "{id}")
    public ResponseEntity<OrderDTO> findById(@PathVariable("id") long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> orders() {
        List<OrderDTO> list = service.allByStatus();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<OrderDTO> create(@RequestBody @Valid OrderDTO orderDTO) {
        service.create(orderDTO);
        return ResponseEntity.ok(orderDTO);
    }

    @PutMapping(value = "{id}/status")
    public ResponseEntity<OrderDTO> update(@PathVariable("id") long id, @RequestBody @Valid OrderDTO orderDTO) {
        OrderDTO order = service.update(id, orderDTO);
        return ResponseEntity.ok(order);
    }
}
