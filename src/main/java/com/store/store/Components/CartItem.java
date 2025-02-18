package com.store.store.Components;

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
public class CartItem {
    
    private Product product;
    private int quantity;

    //Metodo personalizado
    public double getTotalPrice(){
        return product.getPrice() * quantity;
    }
}
