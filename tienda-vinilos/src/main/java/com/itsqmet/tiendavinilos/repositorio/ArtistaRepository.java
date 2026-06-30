package com.itsqmet.tiendavinilos.repositorio;

import com.itsqmet.tiendavinilos.modelo.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio de Artista.
 */
public interface ArtistaRepository extends JpaRepository<Artista, Long> {
}
