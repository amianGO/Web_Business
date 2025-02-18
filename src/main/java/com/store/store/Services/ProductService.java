package com.store.store.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.store.Entities.Product;
import com.store.store.Entities.ProductCategory;
import com.store.store.Repositories.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    //Metodo para Crear o Actualizar
    public void save(Product product){
        productRepository.save(product);
    }

    //Metodo para Buscar Todo los productos
    public List<Product> findProducts(){
        return productRepository.findAll();
    }

    //Metodo para Buscar Por ID
    public Product findProductById(Long id){
        return productRepository.findById(id).orElse(null);
    }

    //Metodo para Borrar por ID
    public void deleteById(Long id){
        productRepository.deleteById(id);
    }

    //Metodo para Buscar por Nombre
    public List<Product> searchByName(String name){
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    //Metodo para Buscar Por precio
    public List<Product> searchByPrice(double price){
        return productRepository.findByPriceLessThanEqual(price);
    }

    //Metodo para buscar por Nombre y precio
    public List<Product> searchByNameAndPrice(String name, double price){
        return productRepository.findByNameContainingIgnoreCaseAndPriceLessThanEqual(name, price);
    }

    //Metodo para Buscar por Categoria
    public List<Product> searchByCategory(ProductCategory category){
        return productRepository.findByCategory(category);
    }


}
