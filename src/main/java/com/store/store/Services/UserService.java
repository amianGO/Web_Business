package com.store.store.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.store.store.Entities.Role;
import com.store.store.Entities.User;
import com.store.store.Repositories.RoleRepository;
import com.store.store.Repositories.UserRepository;

@Service                                                                        //Marca la clase como un componente de servicio en Spring lo que significa que contiene la logica del negocio
public class UserService {
    
@Autowired                                                                      //Inyecta automaticamente las dependencias 
    private UserRepository userRepository;                                      //Usado para interactuar con la base de datos del usuario

    @Autowired
    private RoleRepository roleRepository;                                      //Usado para interactuar con la base de datos de roles

    @Autowired
    private PasswordEncoder passwordEncoder;                                    //Se usa para cifrar contraseñas antes de guardarlas en la basde de datos

   
    public User createUser(String username, String password, String roleName){   //Metodo publico que recibe 3 parametros para crear un usuario
        if (userRepository.findByUsername(username).isPresent()) {               //Si el Nombre del usuario existe, enviara una excepcion con el mensaje
            throw new RuntimeException("El usuario ya existe");
        }
        
        Role role = roleRepository.findByName(roleName)                          //Buscamos el rol en la base de datos
                                  .orElseThrow(()-> new RuntimeException("El rol no existe"));  //Si el rol no existe enviamos una excepcion
        
        User user = new User(username, passwordEncoder.encode(password));       //Creamos un usuario, donde la contraseña se cifra antes de guardarlo
        user.getRole().add(role);                                               //Agrega el rol encontrado a la lista de roles de usuario

        return userRepository.save(user);                                       //Guardamos en la base de datos
    }

    
    public Optional<User> findByUserName(String Username){                      //Recibe un username, y busca el usuario en la base de datos
        return userRepository.findByUsername(Username);
    }
}
