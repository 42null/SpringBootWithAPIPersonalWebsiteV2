package edu.wctc.wholesale.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private int productId;

	@Column(name = "name")//TODO: ADD MINS AND MAXS
	private String name;

	@Column(name = "cost")
	private double cost;
}
