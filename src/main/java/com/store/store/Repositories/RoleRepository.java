package com.store.store.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.store.Entities.Role;

@Repository                                                             //Marcamos la interfaz como un componente de acceso a datos de Spring
public interface RoleRepository extends JpaRepository<Role, Long> {     //Heredamos de JpaRepository el cual manejara la entidad Role y usara Long Como tipo de clave primaria
    Optional<Role> findByName(String name);                             //El metodo Optional retorna un objeto opcional lo que significa que puede haber un rol o no
}
