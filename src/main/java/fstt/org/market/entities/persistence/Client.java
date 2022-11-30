package fstt.org.market.entities.persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Client implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, unique = true )
	private Long clientId;
	
	@Column(nullable = false, length =  255 )
	private String clientName;
	
	@Column(nullable = true )
	private String clientAddress;
	
	@Column(nullable = true )
	private String clientPhone;
	
	@Column(nullable = true )
	private String clientCity;
	
	@OneToMany(mappedBy="client")
	private List<OrderC> orders ;
	

	public Client() {
		super();
	}	

	public Client(String clientName, String clientAddress, String clientPhone, String clientCity) {
		super();
		this.clientName = clientName;
		this.clientAddress = clientAddress;
		this.clientPhone = clientPhone;
		this.clientCity = clientCity;
	}


	public Client(Long clientId, String clientName, String clientAddress, String clientPhone, String clientCity) {
		super();
		this.clientId = clientId;
		this.clientName = clientName;
		this.clientAddress = clientAddress;
		this.clientPhone = clientPhone;
		this.clientCity = clientCity;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientAddress() {
		return clientAddress;
	}

	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}

	public String getClientPhone() {
		return clientPhone;
	}

	public void setClientPhone(String clientPhone) {
		this.clientPhone = clientPhone;
	}

	public String getClientCity() {
		return clientCity;
	}

	public void setClientCity(String clientCity) {
		this.clientCity = clientCity;
	}

	public List<OrderC> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderC> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "Client [clientId=" + clientId + ", clientName=" + clientName + ", clientAddress=" + clientAddress
				+ ", clientPhone=" + clientPhone + ", clientCity=" + clientCity + "]";
	}

	
}