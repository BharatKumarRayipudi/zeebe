package io.flowing.retail.checkout.rest;

import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.zeebe.client.ZeebeClient;

@RestController
public class ShopRestController {

	@Autowired
	private ZeebeClient zeebe;

	@RequestMapping(path = "/api/cart/order", method = PUT)
	public String placeOrder(@RequestParam(value = "customerId") String customerId) {

		JsonObjectBuilder order = Json.createObjectBuilder();
		order.add("orderId", "102");
		order.add("orderName", "LTI Hyderabad");

		String payload = Json.createObjectBuilder().add("order", order).build().toString();

		try {

			zeebe.newCreateInstanceCommand().bpmnProcessId("order-zeebe").latestVersion().variables(payload).send()
					.join();
		} catch (Exception e) {
			throw new RuntimeException("Could not tranform and send message due to: " + e.getMessage(), e);
		}

		return "{\"payload\": \"" + payload + "\"}";
	}

}