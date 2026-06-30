package com.itsqmet.tiendavinilos.repositorio;

import com.itsqmet.tiendavinilos.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio de Cliente.
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
