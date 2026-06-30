package com.itsqmet.tiendavinilos.repositorio;

import com.itsqmet.tiendavinilos.modelo.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio de Venta.
 */
public interface VentaRepository extends JpaRepository<Venta, Long> {
}
