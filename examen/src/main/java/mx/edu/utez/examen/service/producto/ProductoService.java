package mx.edu.utez.examen.service.producto;

import mx.edu.utez.examen.models.producto.Producto;
import mx.edu.utez.examen.models.producto.ProductoRepository;
import mx.edu.utez.examen.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductoService {
    @Autowired
    private ProductoRepository repository;

    @Transactional(readOnly = true)
    public CustomResponse<List<Producto>> getAll(){
        return new CustomResponse<>(
                this.repository.findAll(),
                false,
                200,
                "OK"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Producto> insert(Producto producto){
        Optional<Producto> exists = this.repository.findAllByNombreProducto(producto.getNombreProducto());
        if(exists.isPresent()){
            return new CustomResponse<>(
                    null, true, 400, "Producto ya registrado"
            );
        }
        return new CustomResponse<>(
                this.repository.saveAndFlush(producto),
                false,200,"Prodcuto registrado"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Producto> update(Producto producto){
        if(!this.repository.existsById(producto.getId())){
            return new CustomResponse<>(
                    null,true,400,"Prodycto existe"
            );
        }
        return new CustomResponse<>(
                this.repository.saveAndFlush(producto),
                false,
                200,
                "Producto actualizado correctamente"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Integer> changeStatus(Producto producto){
        if(!this.repository.existsById(producto.getId())){
            return new CustomResponse<>(
                    0,
                    true,
                    400,
                    "Producto no existe"
            );
        }
        return new CustomResponse<>(
                this.repository.updateStatusById(
                        producto.getStatus(), producto.getId()
                ),
                false,
                200,
                "Producto statuts actualizado correctamente"
        );
    }
}
