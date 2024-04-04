package com.staples.chatbot.StaplesChatbot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.staples.chatbot.StaplesChatbot.entity.Product;
import com.staples.chatbot.StaplesChatbot.repository.ProductRepository;

@RestController
@RequestMapping
public class ChatbotController {
	
	@Autowired
	private ProductRepository repo;
	
	
	
	@PostMapping("/product")
    public Product save(@RequestBody Product product) {
    	System.out.println("product "+product.toString());
    	System.out.println("product id"+product.getId());
        return repo.save(product);
    }

    @GetMapping("/product")
    public List<Product> getAllProducts() {
        return repo.findAll();
    }
    
    @GetMapping("/product/{id}")
    @Cacheable(key = "Product"+"#id",value = "Products")
     public Product findProduct(@PathVariable int id) {
     	System.out.println("Inside Cache");
     	Product p=repo.findProductById(id);
     	System.out.println(p.getId()+","+p.getName()+","+p.getPrice()+","+p.getQuantitity());
     	return repo.findProductById(id);
     }

     @DeleteMapping("/product/{id}")
     @CacheEvict(key = "#id",value = "Products")
     public String remove(@PathVariable int id) {
          return repo.deleteProductById(id);
        // return true;
     }

}
