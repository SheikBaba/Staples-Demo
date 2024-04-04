package com.staples.chatbot.StaplesChatbot.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.staples.chatbot.StaplesChatbot.entity.Product;

@Repository
public class ProductRepository {
	
	public static final String HASH_KEY = "Product";
	
	@Autowired
	private RedisTemplate template;
	
	
	
	public Product save(Product product){
		System.out.println("saving product in db");
        template.opsForHash().put(HASH_KEY,product.getId(),product);
    	
        return product;
    }
	
	
	
	 public List<Product> findAll(){
		 System.out.println("loading all products from db");
	        return template.opsForHash().values(HASH_KEY);
	    }
	
	 public Product findProductById(int id){
	        System.out.println("loading product by id from Database");
	        return (Product) template.opsForHash().get(HASH_KEY,id);
	    }
	
	public String deleteProductById(int id) {
		System.out.println("deleting product by id from Database");
		template.opsForHash().delete(HASH_KEY, id);
		return "Product deleted";
	}

	
	
	

}
