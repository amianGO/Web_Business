package com.store.store.Entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity                                                     //Indica que la clase es un componente de JPA, lo cual siginifica que mapeara una tabla en la base de datos
@AllArgsConstructor                                         //Genera un constructor con todos los argumentos de la entidad
@NoArgsConstructor                                          //Genera un contructor sin argumentos
@Getter                                                     //Crea los metodos Get
@Setter                                                     //Crea los metodos Set
@Data                                                       //Contiene varias anotaciones anteriores y otras mas como toString
@Table(name = "Usuarios")                                   //Especifica que esta entidad se mapeara en la tabla Usuarios
public class User {

    @Id                                                     //Indica que este campo es la clave primaria de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //La clave primaria incrementa su valor automaticamente en la base de datos
    private Long id;

    @Column(unique = true, nullable = false)                //Configura la columna en la base de datos ((Unique) El campo debe de ser unico, (nullable) El campo no puede ir vacio)
    private String username;

    @Column(nullable = false)                               //Configura la columna en la base de datos ((nullable) El campo no puede ir vacio)
    private String password;

    //Many to Many indica que un usuario puede tener muchos roles y que un rol puede pertenecer a varios usuarios
    @ManyToMany(fetch = FetchType.EAGER)                    //Carga los roles inmediatamente cuando se consulta un usuario
    @JoinTable(                                             //Define la tabla intermedia que asocia a usuarios con roles 
    name = "user_role",                                     //Nombre de la tabla intermedia
        joinColumns = @JoinColumn(name = "user_id"),        //Define la columna user_id que hace referencia al User
        inverseJoinColumns = @JoinColumn(name = "role_id" ) //Define la columna role_id que hace referencia al Role
    )   //Es un conjunto (Set) de roles asociados 
    private Set<Role> role = new HashSet<>();   //Inicializamos para evitar errores como NullPointerException

    public User(String username,String password){           //Constructor Personalizado que recibe solo 2 parametros
        this.username = username;
        this.password = password;
        this.role = new HashSet<>();
    }
}
