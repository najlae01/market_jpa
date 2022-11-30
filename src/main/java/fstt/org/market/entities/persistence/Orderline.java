package fstt.org.market.entities.persistence;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table
public class Orderline implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, unique = true )
	private Long orderlineId;

	@Column(nullable = false )
	private int orderlineQuantity;

	@ManyToOne
	@JoinColumn(name = "order_id")
	private OrderC orderlineOrder;

	@ManyToOne
	private Product orderlineProduct;

	public Orderline(Long orderlineId, int orderlineQuantity, OrderC orderlineOrder, Product orderlineProduct) {
		super();
		this.orderlineId = orderlineId;
		this.orderlineQuantity = orderlineQuantity;
		this.orderlineOrder = orderlineOrder;
		this.orderlineProduct = orderlineProduct;
	}

	public Orderline(int orderlineQuantity, OrderC orderlineOrder, Product orderlineProduct) {
		super();
		this.orderlineQuantity = orderlineQuantity;
		this.orderlineOrder = orderlineOrder;
		this.orderlineProduct = orderlineProduct;
	}

	public Orderline(Long orderlineId, int orderlineQuantity, Product orderlineProduct) {
		super();
		this.orderlineId = orderlineId;
		this.orderlineQuantity = orderlineQuantity;
		this.orderlineProduct = orderlineProduct;
	}

	public Orderline() {
		super();
	}

	public Long getOrderlineId() {
		return orderlineId;
	}

	public void setOrderlineId(Long orderlineId) {
		this.orderlineId = orderlineId;
	}

	public int getOrderlineQuantity() {
		return orderlineQuantity;
	}

	public void setOrderlineQuantity(int orderlineQuantity) {
		this.orderlineQuantity = orderlineQuantity;
	}

	public OrderC getOrderlineOrder() {
		return orderlineOrder;
	}

	public void setOrderlineOrder(OrderC orderlineOrder) {
		this.orderlineOrder = orderlineOrder;
	}

	public Product getOrderlineProduct() {
		return orderlineProduct;
	}

	public void setOrderlineProduct(Product orderlineProduct) {
		this.orderlineProduct = orderlineProduct;
	}

	@Override
	public String toString() {
		return "Orderline [orderlineId=" + orderlineId + ", orderlineQuantity=" + orderlineQuantity
				+ ", orderlineOrder=" + orderlineOrder + ", orderlineProduct=" + orderlineProduct + "]";
	}

}
