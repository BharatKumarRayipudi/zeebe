package io.flowing.retail.checkout.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WorkflowDataContext<T> {

	private T payload;

	@SuppressWarnings("unchecked")
	public static <R> WorkflowDataContext<R> fromJson(String json, Class<R> classtype) {
		try {
			return (WorkflowDataContext<R>) new ObjectMapper().readValue(json, classtype);
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

	public T getPayload() {
		return payload;
	}

	public WorkflowDataContext<T> setPayload(T payload) {
		this.payload = payload;
		return this;
	}

}
