package mx.edu.utez.examen.controllers.cliente;

import mx.edu.utez.examen.controllers.cliente.Dtos.ClienteDto;
import mx.edu.utez.examen.models.cliente.Cliente;
import mx.edu.utez.examen.service.cliente.ClienteService;
import mx.edu.utez.examen.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api-examen/cliente/")
@CrossOrigin(origins = {"*"})
public class ClienteController {
    @Autowired
    private ClienteService service;

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Cliente>>> getAll(){
        return new ResponseEntity<>(
                this.service.getAll(),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<CustomResponse<Cliente>> insert(
            @Valid @RequestBody ClienteDto clienteDto){
        return new ResponseEntity<>(
                this.service.insert(clienteDto.getCliente()),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<CustomResponse<Cliente>> update(
            @RequestBody ClienteDto clienteDto, @Valid BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(
                this.service.update(clienteDto.getCliente()),
                HttpStatus.CREATED
        );
    }
}
