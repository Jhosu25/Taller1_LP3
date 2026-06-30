package com.itsqmet.tiendavinilos.repositorio;

import com.itsqmet.tiendavinilos.modelo.Disco;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio de Disco.
 */
public interface DiscoRepository extends JpaRepository<Disco, Long> {
}
