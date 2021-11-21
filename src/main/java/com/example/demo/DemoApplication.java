package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Product;

import java.util.HashMap;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@SpringBootApplication
@RestController
public class DemoApplication {
	private static HashMap<String,Product> list = new HashMap<>();
	static {
		Product product = new Product();
		product.setId("1");
		product.setName("p1");
		list.put(product.getId(),product);
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@RequestMapping(value = "/")
	public String Hello() {
		return "hello world";
	}
	
	//get all method

	@RequestMapping(value = "/products")
	public ResponseEntity<Object> getProducts() {
		return new ResponseEntity<>(list.values(), HttpStatus.OK);
	}

	//get one method
	@RequestMapping(value = "/products/{id}")
	public ResponseEntity<Object> getOneProduct(@PathVariable("id") String id) {
		Product product = list.get(id);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}
	
	//create method

	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public ResponseEntity<Object> create(@RequestBody Product product) {
		list.put(product.getId(), product);
		return new ResponseEntity<>("created successfully", HttpStatus.CREATED);
	}

	//update method

	@RequestMapping(value = "/products/{id}",method = RequestMethod.PUT)
	public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product) {
		list.remove(id);
		product.setId(id);
		list.put(id, product);
		return new ResponseEntity<>("updated successfully", HttpStatus.OK);
	}

	//delete methode

	@RequestMapping(value = "/products/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteItem(@PathVariable("id") String id) {
		list.remove(id);
		return new ResponseEntity<>("item deleted", HttpStatus.OK);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name){
		return String.format("Hello %s!", name);
	}

}
