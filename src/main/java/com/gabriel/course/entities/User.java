package com.gabriel.course.entities;

import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "app_user")
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String email;
	private String phone;
	private String password;
	
//	private List<Order> orders = new ArrayList<>();
	
	
	/*public void saveOrder(Order order) {
		orders.add(order);
	}
	
	public void removeOrder(Order order) {
		orders.add(order);
	}*/

	/*public List<Order> getOrders() {
		return orders;
	}*/
	
	

	
	
	
	

}
