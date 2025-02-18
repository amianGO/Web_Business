package com.store.store.Controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.store.store.Entities.Product;
import com.store.store.Entities.ProductCategory;
import com.store.store.Services.CartService;
import com.store.store.Services.ProductService;

@Controller
@RequestMapping("/products")
@SessionAttributes("cart")
public class productController {

    @Autowired
    private ProductService productService;

    @ModelAttribute("cart")
    public CartService initializeCart(){
        return new CartService();
    }

    @GetMapping
    public String showProducts(
        
        @RequestParam(name = "name", required = false) String name,
        @RequestParam(name = "price", required = false) Double price,
        @RequestParam(name = "category",required = false) String category,
        Model model,
        @ModelAttribute("cart") CartService cartService
    ){
        List<Product> products;

        if (name != null && price != null) {
            products = productService.searchByNameAndPrice(name, price);
        } else if (name != null){
            products = productService.searchByName(name);
        } else if (price != null) {
            products = productService.searchByPrice(price);
        } else {
            products = productService.findProducts();
        }

        if (category != null) {
            try {
                ProductCategory categoryEnum = ProductCategory.valueOf(category.toUpperCase());
                products = products.stream()
                                    .filter(p -> p.getCategory() == categoryEnum)
                                    .collect(Collectors.toList());
            } catch (IllegalArgumentException e) {
                products = productService.findProducts(); //Si la categoria es invalida, muestra todo
            }
        } else {
            products = productService.findProducts();
        }
        
        model.addAttribute("products", products);
        model.addAttribute("categories", Arrays.asList(ProductCategory.values())); //Enviar todas las categorias
        model.addAttribute("cartItems", cartService.showItems());
        return "products";
    }
    
}
