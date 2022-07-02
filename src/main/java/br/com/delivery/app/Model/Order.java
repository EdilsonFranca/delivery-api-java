package br.com.delivery.app.Model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;

@Getter
@Setter
@Entity
@Table(name = "order_my")

public class Order implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_order;
	
	@OneToMany(
			targetEntity = Items.class,
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL
	)
    private List<Items> items;
	
	@Column(name="delivery_fee")
	private BigDecimal deliveryFee;
	
	@Column(name="sub_total")
	private BigDecimal subTotal;

	@Column(name="total")
	private BigDecimal total;
	
	@Column(name="form_of_payment")
	private String  formOfPayment;
	
	@Column(name="thing")
	private String Thing;
	
	@Column(name="status")
	private int status;
	
	@Column(name="create_data")
	private String create_data;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
			name = "id_client",
			referencedColumnName = "id_client"
	)
    @Valid
	private Client Client;
}
