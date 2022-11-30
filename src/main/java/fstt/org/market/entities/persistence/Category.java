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
public class Category implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, unique = true )
	private Long categoryId;

	@Column(nullable = false, length =  255 )
	private String categoryName;

	@Column(nullable = true )
	private String categoryDescription;

	@OneToMany(mappedBy="productCategory")
	private List<Product> products = new ArrayList<Product>();
	
	

	public Category() {
		super();
	}


	public Category(Long categoryId, String categoryName, String categoryDescription, List<Product> products) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.categoryDescription = categoryDescription;
		this.products = products;
	}
	

	public Category(String categoryName, String categoryDescription, ArrayList<Product> products) {
		super();
		this.categoryName = categoryName;
		this.categoryDescription = categoryDescription;
		this.products = products;
	}

	

	public Category(String categoryName, String categoryDescription) {
		super();
		this.categoryName = categoryName;
		this.categoryDescription = categoryDescription;
		this.products = new ArrayList<Product>();
	}

	public Category(Long categoryId, String categoryName, String categoryDescription) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.categoryDescription = categoryDescription;
		this.products = new ArrayList<Product>();
	}
	
	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	

}
