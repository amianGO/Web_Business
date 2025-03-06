package com.store.store.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.store.Entities.User;

@Repository                                                             //Marcamos la interfaz como un componente de acceso a datos en Spring
public interface UserRepository extends JpaRepository<User, Long> {     //Hereda de JpaRepository, la cual manejara la Enidad User y usara Long como tipo de clave primaria
    //Declaramos un metodo personalizado
    Optional<User> findByUsername(String username);                     //Optional retorna un objeto opcional, lo que significa que puede haber un user o no
}
