package com.store.store.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.store.store.Components.CartItem;
import com.store.store.Entities.Product;
import com.store.store.Services.ProductService;
import com.store.store.Services.CartService;



@Controller
@SessionAttributes("cart")
public class IndexController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String showHomerPage(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); //Inicializamos la interfaz de Spring que nos permitira verificar las autenticaciones

        if (authentication != null && authentication.isAuthenticated() && !authentication.getName().equals("anonymousUser")) {
            model.addAttribute("username", authentication.getName());
        } else {
            model.addAttribute("username", null);
        }
        return "home";
    }

    @GetMapping("/login")
    public String loginPage(Authentication authentication){
        if (authentication != null && authentication.isAuthenticated()) { //Si la autenticacion existe y esta autenticado, retornamos la misma pagina
            return "redirect:/";
        }
        return "login";
    }

    @GetMapping("/Products")
    public String showProductPage(Model model){
        List<Product> products = productService.findProducts();
        model.addAttribute("products", products);
        return "products";
    }

    @ModelAttribute("cartItems")
    public List<CartItem> getCartItems(@ModelAttribute("cart") CartService cartService){
        return cartService.showItems();
    }

   
}
