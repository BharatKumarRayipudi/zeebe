package io.flowing.retail.zeebe.order.flow;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.flowing.retail.zeebe.order.domain.Order;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class OrderFlowContext {

	private Order order;

	public static OrderFlowContext fromJson(String json) {
		try {
			return new ObjectMapper().readValue(json, OrderFlowContext.class);
		} catch (Exception e) {
			throw new RuntimeException("Could not deserialize context from JSON: " + e.getMessage(), e);
		}
	}

	public String asJson() {
		try {
			return new ObjectMapper().writeValueAsString(this);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Could not serialize context to JSON: " + e.getMessage(), e);
		}
	}

	public Order getOrder() {
		return order;
	}

	public OrderFlowContext setOrder(Order order) {
		this.order = order;
		return this;
	}

}
