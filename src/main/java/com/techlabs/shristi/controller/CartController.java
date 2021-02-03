package com.techlabs.shristi.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.techlabs.shristi.model.Product;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private RestTemplate restTemplate;
	
	private static final String BASE_URI ="http://PRODUCT-SERVICE/products";//"http://localhost:7002/products";
	
	@Autowired
	private LoadBalancerClient loadBalancerClient;
	
	private List<Product> products = new ArrayList<>();
	
	@GetMapping
	public List<Product> getAllProducts() {
		return products;
	}
	
	@GetMapping("/many/{category}/{brand}")
	public List<Product> getProductsByCategory(@PathVariable("category") String category, @PathVariable("brand") String brand){
		List<LinkedHashMap<Integer, Object>> responseMap = restTemplate.getForObject(BASE_URI+"/category/"+category, List.class);
		for(LinkedHashMap<Integer, Object> response: responseMap) {
			String brandRes = (String) response.get("brand");
			if(brandRes.equals(brand)) {
				Integer productId = (Integer) response.get("id");
				String name = (String) response.get("name");
				Double price = (Double) response.get("price");
				Product product = new Product(productId, name, category, price, brand);
				products.add(product);
			}
		}
		ServiceInstance instance = loadBalancerClient.choose("PRODUCT-SERVICE");
		System.out.println("PORT used: "+ instance.getPort());
		return products;
	}
	
	@GetMapping("/one/{productId}")
	public List<Product> getProductById(@PathVariable("productId") int id){
		Product product = restTemplate.getForObject(BASE_URI+"/id/"+id, Product.class);
		products.clear();
		products.add(product);
		return products;
	}
}
