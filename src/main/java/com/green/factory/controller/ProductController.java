package com.green.factory.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.green.factory.entity.ProductData;
import com.green.factory.service.ProductDataService;

@Controller
@ComponentScan({"com.green.factory.service"})
public class ProductController {
	
	@Autowired
	private ProductDataService productDataService;
	@Autowired
	private ObjectMapper mapper;
	
	@GetMapping("productdata")
	public String productData() {
		return "productdata";
	}
	
	@GetMapping("productDataId/{id}")
	public ResponseEntity getProductData(@PathVariable("id") int id) {
		ObjectNode jsonObject = mapper.createObjectNode();
		jsonObject.putPOJO("product", productDataService.getProductDataById(id));
		return new ResponseEntity(jsonObject, HttpStatus.OK);
	}
	
	@GetMapping("productsdata")
	public String getProductsData(Model model) {
		List<ProductData> productsData = productDataService.getProductsData();
		model.addAttribute("productsData", productsData);
		return "cms/productsdata";
	}
	
	@PostMapping("newProductData")
	public ResponseEntity addProductData(@RequestBody ProductData object) {
		//List<ProductData>
		
		productDataService.addProductData(object);
		ObjectNode jsonObject = mapper.createObjectNode();
		jsonObject.put("message", "success");
		return new ResponseEntity(jsonObject, HttpStatus.OK);
	}
	
	@DeleteMapping("deleteProductData/{id}")
	public ResponseEntity deleteProductData(@PathVariable("id") int id) {
		ObjectNode jsonObject = mapper.createObjectNode();
		
		if( productDataService.deleteProductData(id)) {
			jsonObject.put("message", "success");
		} else {
			jsonObject.put("message", "failure");
		}
		
		return new ResponseEntity(jsonObject, HttpStatus.OK);
	}
	
	@PutMapping("updateProductData")
	public ResponseEntity updateProductData(@RequestBody ProductData object) {
		ObjectNode jsonObject = mapper.createObjectNode();
		
		if(productDataService.updateProductData(object)) {
			jsonObject.put("message", "success");
		} else {
			jsonObject.put("message", "failure");
		}
		
		return new ResponseEntity(jsonObject, HttpStatus.OK);
	}
}
