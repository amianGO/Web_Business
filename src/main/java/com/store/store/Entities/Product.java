package com.store.store.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Producto")
public class Product {

    //Añadimos los Atributos de la clase productos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Nombre")
    private String name;
    @Column(name = "Precio")
    private double price;
    @Column(name = "Descripcion")
    private String description;
    @Column(name = "Codigo")
    private int code;
    @Enumerated(EnumType.STRING)//Hace que la Categoria se guarde como texto en la base de datos en vez de numero
    @Column(name = "Categoria")
    private ProductCategory category;
    @Column(name = "Imagen")
    private String image;

}
