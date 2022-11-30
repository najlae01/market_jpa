package fstt.org.market.entities.persistence;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table
public class OrderC implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, unique = true )
	private Long orderId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = true)
	private Date orderDate;

	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;

	@OneToMany(mappedBy="orderlineOrder")
	private List<Orderline> orderlines;

	public OrderC(Long orderId, Client client) {
		super();
		this.orderId = orderId;
		this.orderDate = new Date();
		this.orderlines = new ArrayList<Orderline>();
		this.client = client;
	}

	public OrderC(Long orderId, Date orderDate, Client client) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.client = client;
	}

	public OrderC(Long orderId, Date orderDate, ArrayList<Orderline> orderlines, Client client) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.orderlines = orderlines;
		this.client = client;
	}

	public OrderC(Client client) {
		super();
		this.orderDate = new Date();
		this.client = client;
	}

	public OrderC() {
		super();
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Orderline> getOrderlines() {
		return orderlines;
	}

	public void setOrderlines(List<Orderline> orderlines) {
		this.orderlines = orderlines;
	}

	public void addOrderline(Orderline orderline) {
		this.orderlines.add(orderline);
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderDate=" + orderDate + ", client=" + client + "]";
	}

}
