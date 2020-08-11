package io.flowing.retail.zeebe.order.domain;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity(name = "OrderEntity")
public class Order {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	protected String id = UUID.randomUUID().toString();

	private String orderId;
	private String orderName;

	public String getId() {
		return id;
	}

	public String getOrderId() {
		return orderId;
	}

	@JsonProperty("orderId")
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderName() {
		return orderName;
	}

	@JsonProperty("orderName")
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderName=" + orderName + "]";
	}

}
