package com.store.store.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity                                                         //Indica que la clase es una enetidad de JPA, asi que mapeara una tabla en la base de datos
@AllArgsConstructor                                             //Genera un constructor con todos los atributos de la clase
@NoArgsConstructor                                              //Genrea un contructor sin argumentos 
@Getter                                                         //Genera metodos Get
@Setter                                                         //Genera metodos Set
@Data                                                           //Esta Combina varias anotaciones anteriores y mas como toString
@Table(name = "Roles")                                          //Especifica que esta entidad se mapeara en la tabla roles de la base de datos
public class Role {                                         

    @Id                                                         //Indica que el campo es la clave primaria de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY)         //La clave primaria se auto incrementara en la base de datos
    private Long id;                                        

    @Column(unique = true, nullable = false)                    //Configura la Columna en la base de datos (No permite valores duplicados (Unique), El campo no puede estar vacio (Nullable))
    private String name;
    
}
