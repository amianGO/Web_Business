package com.store.store.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.store.store.Entities.Product;
import com.store.store.Services.ProductService;



@Controller
public class IndexController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String showHomerPage(){
        return "home";
    }

    @GetMapping("/Products")
    public String showProductPage(Model model){
        List<Product> products = productService.findProducts();
        model.addAttribute("products", products);
        return "products";
    }

    
    
}
