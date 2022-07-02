package br.com.delivery.app.Dto;

import br.com.delivery.app.Model.Items;
import br.com.delivery.app.Model.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long id;

    private List<Items> items;

    private BigDecimal deliveryFee;

    private BigDecimal subTotal;

    private BigDecimal total;

    private String formOfPayment;

    private String Thing;

    private int status;

    private String create_data;

    public OrderDTO(Order order) {
        this.id = order.getId_order();
        this.items = order.getItems();
        this.deliveryFee = order.getDeliveryFee();
        this.subTotal = order.getSubTotal();
        this.total = order.getTotal();
        this.formOfPayment = order.getFormOfPayment();
        this.Thing = order.getThing();
        this.status = order.getStatus();
        this.create_data = order.getCreate_data();
    }
}
