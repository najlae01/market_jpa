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
public class Product implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, unique = true )
	private Long productId;

	@Column(nullable = false, length =  255 )
	private String productName;

	@Column(nullable = true )
	private String productDescription;

	@Column(nullable = true )
	private double productPrice;

	@Column(nullable = true )
	private int productStockQuantity;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category productCategory;

	public Product() {
		super();
	}

	public Product(String productName, String productDescription, double productPrice,
			int productStockQuantity, Category productCategory) {
		super();
		this.productName = productName;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
		this.productStockQuantity = productStockQuantity;
		this.productCategory = productCategory;
	}

	public Product(Long productId, String productName, String productDescription, double productPrice,
			int productStockQuantity, Category productCategory) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
		this.productStockQuantity = productStockQuantity;
		this.productCategory = productCategory;
	}



	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public int getProductStockQuantity() {
		return productStockQuantity;
	}

	public void setProductStockQuantity(int productStockQuantity) {
		this.productStockQuantity = productStockQuantity;
	}

	public Category getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(Category productCategory) {
		this.productCategory = productCategory;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productDescription="
				+ productDescription + ", productPrice=" + productPrice + ", productStockQuantity="
				+ productStockQuantity + ", productCategory=" + productCategory + "]";
	}

	

}
