package mx.edu.utez.examen.models.producto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    Boolean findAllById(long id);
    Optional<Producto> findAllByNombreProducto(String nombreProducto);
    @Modifying
    @Query(value = "UPDATE producto SET status = :status WHERE id = :id", nativeQuery = true)
    int updateStatusById(
            @Param("status") Boolean status,
            @Param("id") Long id
    );

}
