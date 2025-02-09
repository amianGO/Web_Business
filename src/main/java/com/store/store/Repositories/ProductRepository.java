package com.store.store.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.store.Entities.Product;
import com.store.store.Entities.ProductCategory;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>{

    //Buscar Producto por cierto Nombre (Sin importar mayusculas o minusculas)
    List<Product> findByNameContainingIgnoreCase(String name);

    //Buscar Producto con precio menor o igual al lado
    List<Product> findByPriceLessThanEqual(double price);

    //Buscar Productos por nombre y precio Maximo al mismo tiempo
    List<Product> findByNameContainingIgnoreCaseAndPriceLessThanEqual(String name, double price);    

    //Buscar Productos por Categoria
    List<Product> findByCategory(ProductCategory category);
}
