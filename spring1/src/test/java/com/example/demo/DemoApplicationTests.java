package com.example.demo;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void test_get_coupon_id_1() {
		ClientConfig clientConfig = new DefaultClientConfig();

		Client client = Client.create(clientConfig);
		WebResource webResource = client.resource(UriBuilder.fromUri("http://localhost:8080/coupon/1").build());

		String result =  webResource.path("").path("").accept(String.valueOf(MediaType.APPLICATION_JSON)).get(String.class);
		System.out.println(result);

		Gson gson = new Gson();
		CouponDTO coupon_id1 = gson.fromJson(JsonParser.parseString(result).getAsJsonObject(), CouponDTO.class);
		assert coupon_id1.getId() == 1;
		assert coupon_id1.getTitle().equals("Caffe") == true;
	}

	@Test
	public void test_post_coupon() {

		// this is just for fun, not related to the test!!!
		///////////////////////////////////////////
		// fluent
		Car car = new Car();
		car.drive(100).reduceKm(50).drive(200);
		car.setKm(0).setKm(0).setKm(0);
		///////////////////////////////////////////

		Gson gson = new Gson();

		ClientConfig clientConfig = new DefaultClientConfig();

		Client client = Client.create(clientConfig);
		WebResource webResource =  client.resource(UriBuilder.fromUri("http://localhost:8080/coupon").build());

		CouponDTO coupon = new CouponDTO(4, "launch");

		ClientResponse resp = webResource.path("").accept("application/json").type("application/json").post(ClientResponse.class,
				gson.toJson(coupon));

		if(resp.getStatus() != 201) {
			assert false;
		}

		// ---- get for assert

		// not ideal

		webResource = client.resource(UriBuilder.fromUri("http://localhost:8080/coupon/4").build());
		String result =  webResource.path("").accept(String.valueOf(MediaType.APPLICATION_JSON)).get(String.class);

		System.out.println(result);

		CouponDTO coupon_id4 = gson.fromJson(JsonParser.parseString(result).getAsJsonObject(), CouponDTO.class);
		assert coupon_id4.getId() == 4;
		assert coupon_id4.getTitle().equals("launch") == true;
	}

	@Test
	public void test_put_coupon() {
		Gson gson = new Gson();

		ClientConfig clientConfig = new DefaultClientConfig();

		Client client = Client.create(clientConfig);
		WebResource webResource =  client.resource(UriBuilder.fromUri("http://localhost:8080/coupon/2").build());

		CouponDTO coupon = new CouponDTO(2, "Movie VIP stars");

		ClientResponse resp = webResource.accept("application/json").type("application/json").put(ClientResponse.class,
				gson.toJson(coupon));

		if(resp.getStatus() != 200) {
			assert false;
		}

		// ---- get for assert

		// not ideal

		webResource = client.resource(UriBuilder.fromUri("http://localhost:8080/coupon/2").build());
		String result =  webResource.path("").accept(String.valueOf(MediaType.APPLICATION_JSON)).get(String.class);

		System.out.println(result);

		CouponDTO coupon_id2 = gson.fromJson(JsonParser.parseString(result).getAsJsonObject(), CouponDTO.class);
		assert coupon_id2.getId() == 2;
		assert coupon_id2.getTitle().equals("Movie VIP stars") == true;
	}

	@Test
	public void test_delete_coupon() {
		Gson gson = new Gson();

		ClientConfig clientConfig = new DefaultClientConfig();

		Client client = Client.create(clientConfig);
		WebResource webResource =  client.resource(UriBuilder.fromUri("http://localhost:8080/coupon").build());

		CouponDTO coupon = new CouponDTO(10, "burger");

		ClientResponse resp = webResource.path("").accept("application/json").type("application/json").post(ClientResponse.class,
				gson.toJson(coupon));

		if(resp.getStatus() != 201) {
			assert false;
		}

		webResource =  client.resource(UriBuilder.fromUri("http://localhost:8080/coupon/10").build());
		webResource.accept("application/json").type("application/json").delete();

		//if(resp.getStatus() != 200) {
//			assert false;
		//}

		// ---- get for assert

		// not ideal

		webResource = client.resource(UriBuilder.fromUri("http://localhost:8080/coupon/10").build());
		String result =  webResource.path("").accept(String.valueOf(MediaType.APPLICATION_JSON)).get(String.class);

		System.out.println(result);

		assert result.equals("") == true;
	}
}
