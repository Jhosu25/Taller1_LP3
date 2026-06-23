package com.itsqmet.tiendavinilos.repositorio;

import com.itsqmet.tiendavinilos.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
