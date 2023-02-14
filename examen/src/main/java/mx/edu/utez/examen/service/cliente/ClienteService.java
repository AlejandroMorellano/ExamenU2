package mx.edu.utez.examen.service.cliente;

import mx.edu.utez.examen.models.cliente.Cliente;
import mx.edu.utez.examen.models.cliente.ClienteRepository;
import mx.edu.utez.examen.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClienteService {
    @Autowired
    private ClienteRepository repository;

    @Transactional(readOnly = true)
    public CustomResponse<List<Cliente>> getAll(){
        return new CustomResponse<>(
                this.repository.findAll(),
                false,
                200,
                "OK"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Cliente> insert(Cliente cliente){
        Optional<Cliente> exists = this.repository.findByCorreo(cliente.getCorreo());
        if(exists.isPresent()){
            return new CustomResponse<>(
                    null, true, 400,
                    "Cliente ya registrado"
            );
        }
        return new CustomResponse<>(
                this.repository.saveAndFlush(cliente),
                false, 200, "Cliente registrado"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Cliente> update(Cliente cliente){
        if(!this.repository.existsById(cliente.getId())){
            return new CustomResponse<>(
                    null,true,400,"Cliente no existe"
            );
        }
        return new CustomResponse<>(
                this.repository.saveAndFlush(cliente),
                false,200,"Cliente Modificado"
        );
    }

}
