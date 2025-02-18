package com.store.store.Components;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.store.store.Entities.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
public class Cart {

    private List<CartItem> items = new ArrayList<>();
    
    //Metodo para Guardar un item en el carrito
    public void saveItem(Product product, int quantity){
        for (CartItem item : items) {
            if (item.getProduct().getId().equals(product.getId())) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        items.add(new CartItem(product,quantity));
    }

    //Metodo para eliminar un item del carrito
    public void removeProduct(Long productId){
        items.removeIf(item -> item.getProduct().getId().equals(productId));
    }

    //Metodo para Vaciar el carrito
    public void removeAll(){
        items.clear();
    }

}
