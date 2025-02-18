package com.store.store.Services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import com.store.store.Components.CartItem;
import com.store.store.Entities.Product;

@Service
public class CartService {
    
    private List<CartItem> cartItems = new ArrayList<>();

    //Leer los Productos del carrito
    public List<CartItem> showItems(){
        return cartItems;
    }

    //Añadir el carrito
    public void addProduct(Product product, int quantity){
        if (quantity <= 0) {
            throw new IllegalArgumentException("La cantidad debe de ser mayor a 0");
        }
        if (product == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo");
        }
        for (CartItem cartItem : cartItems) {
            if (cartItem.getProduct().getId().equals(product.getId())) {
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                return;
            }
        }
        cartItems.add(new CartItem(product,quantity));

    }

    //Borrar un elemento del carrito
    public void removeItem(Long productId){
        if (productId == null) {
            throw new IllegalArgumentException("El Id del producto no existe");
        } 
        if (cartItems.isEmpty()) {
            throw new IllegalArgumentException("No existen Productos en el carrito");
        }

        boolean removed = cartItems.removeIf(item -> item.getProduct().getId().equals(productId));

        if (!removed) {
            throw new IllegalArgumentException("No se encontro ningun producto con el ID: " + productId);
        }
    }

    //Vaciar el carrito de compras
    public void clear(){
        if (cartItems.isEmpty()) {
            throw new IllegalArgumentException("El carrito ya esta vacio");
        }
        cartItems.clear();
    }
}
