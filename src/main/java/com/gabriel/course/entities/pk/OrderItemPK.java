package com.gabriel.course.entities.pk;

import java.io.Serializable;

import com.gabriel.course.entities.Order;
import com.gabriel.course.entities.Product;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class OrderItemPK implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "order_Id")
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "product_Id")
	private Product product;
	
}
