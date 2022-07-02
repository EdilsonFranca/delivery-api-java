package br.com.delivery.app.Repository;

import br.com.delivery.app.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("from Order o where o.status < 5 ")
    List<Order> allByStatus();
}
