package com.store.store.Services;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.store.store.Entities.User;
import com.store.store.Repositories.UserRepository;

@Service                                                                //Define esta clase como un servicio de Spring, lo que permite que Spring la detecte como un Bean
public class CustomUserDetailsService implements UserDetailsService{    //Esta clase implenta la unterfaz UserDetailsService, es utilizada por Spring Security para cargar los datos durante la autenticacion
    
    @Autowired                                                          //Inyeccion de Dependencias
    private UserRepository userRepository;                              //Nos permite interactuar con la base de datos de Usuario

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{ //Este metodo se ejecuta automaticamente cuando un usuario intenta autenticarse
        User user = userRepository.findByUsername(username)                                 //Se busca en la base de datos, un usuario con el argumento seleccionado
                                  .orElseThrow(()-> new UsernameNotFoundException("Usuario no encontrado"));    //Si el usuario no aparece enviamos una excepcion, lo que impedira el inicio de sesion
        
        return new org.springframework.security.core.userdetails.User(                      //Ya que SpringSecurity no acepta la entidad directamente, cremos la instancia que represente el usuario autenticados
            user.getUsername(),                                                             //Obtenemos el Username
            user.getPassword(),                                                             //Obtenemos al Contraseña
            user.getRole().stream()                                                         //Obtiene los roles de los usuarios
            .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))              //Convierte cada rol en un objeto (Si role.getAdmin es 'ADMIN', se convierte en ROLE_ADMIN)
            .collect(Collectors.toList())                                                   //Convierte el Stream en una lista
        );
    }
}
