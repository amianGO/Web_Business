package com.store.store.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.store.store.Components.CartItem;
import com.store.store.Entities.Product;
import com.store.store.Services.ProductService;
import com.store.store.Services.CartService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@SessionAttributes("cart")
public class CartController{

    @Autowired
    private ProductService productService;
    
    //Metodo que Inicialice el carrito en la sesion
    @ModelAttribute("cart")
    public CartService initializeCart(){
        return new CartService();
    }

    //Metodo para listar los Productos
    @GetMapping("/cart")
    public String showCart(Model model,@ModelAttribute("cart") CartService service){
        List<CartItem> items = service.showItems();
        model.addAttribute("cartItems", items);
        return "products";
    }

    //Metodo para Añadir
    @PostMapping("/cart/add")
    public String addToCart(@RequestParam("id") Long productId, 
                            @RequestParam("quantity") int quantity,
                            @ModelAttribute("cart")CartService cartService) {   
        Product product = productService.findProductById(productId);
        cartService.addProduct(product, quantity);

        //Verificacion en consola
        System.out.println("Productos en el carrito");
        for (CartItem item : cartService.showItems()) {
            System.out.println("Producto: " + item.getProduct().getName() + " | Cantidad: " + item.getQuantity());
            System.out.println(cartService.showItems().size());
        }
        return "redirect:/products";
    }

    //Prueba para verificar el carrito
    @GetMapping("/cart/debug")
    @ResponseBody
    public String debugCart(@ModelAttribute("cart") CartService cartService){
        return "Productos en el carrito: " + cartService.showItems().size();
    }

    //Metodo para eliminar item del carrito
    @PostMapping("/cart/remove") //Actualizacion de estado (Thymeleaf no reconoce @DelteMapping)
    public String deleteItem(@RequestParam("id") Long productId,@ModelAttribute("cart") CartService cartService){
        cartService.removeItem(productId);
        return "redirect:/products";
    }
    

}
