package com.green.factory.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.green.factory.entity.ProductData;
import com.green.factory.entity.ProductRepository;
//@XmlRootElement

//@ComponentScan(basePackages={"com.green.factory.entity"})
@EntityScan("com.green.factory.entity")
@EnableJpaRepositories("com.green.factory.entity")
@Service
public class ProductDataService {
	
	@Autowired
	private ProductRepository productRespository;
	
	private List<ProductData> productsData = new ArrayList<>(Arrays.asList(
						new ProductData(1, "green1", "kind1", "this product can help you to light what you see in dark place.", 1200),
						new ProductData(2, "green1", "kind2", "this product can help you to light what you see in dark place.", 1300),
						new ProductData(3, "green3", "kind3", "this product can help you to light what you see in dark place.", 1400)
						));
	
	public List<ProductData> getProductsData() {
		List<ProductData> products = new ArrayList<>();
		productRespository.findAll()
		.forEach(products::add);
		return products;
	}
	
	public void addProductData(ProductData newProduct) {
		
		ProductData productData = null;
		
		if(newProduct != null) {
			productData = newProduct;
		}
		
		//productsData.add(newProduct);
		try {
			productRespository.save(productData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public ProductData getProductDataById(int id) {
		//Optional<ProductData>
		return productsData.stream().filter(t -> t.getId() == id).findFirst().get();
		//return productRespository.findById(id);
		//return productsData.get(0);
	}
	
	public Boolean deleteProductData(int id) {
		
		productRespository.deleteById(id);
		return true;
		/*productRespository.deleteById(id);
		return true;
		
		
		
		if(productsData.removeIf(t -> t.getId() == id)) {
			return true;
		} else {
			return false;
		}*/
		
	}
	
	public Boolean updateProductData(ProductData newProduct) {
		/*for(ProductData oldData : productsData) {
			if(newProduct.getId() == oldData.getId()) {
				productsData.set(
						productsData.indexOf(oldData), 
						newProduct
						);
				return true;
			}
		}*/
		
		productRespository.save(newProduct);
		
		return true;
	}
}
