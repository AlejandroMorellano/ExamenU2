package mx.edu.utez.examen.controllers.cliente.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.examen.models.cliente.Cliente;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ClienteDto {
    private Long id;

    @NotEmpty(message = "Campo obligatorio")
    private String nombre;

    @NotEmpty(message = "Campo obligatorio")
    private String correo;

    @NotEmpty(message = "Campo obligatorio")
    private String contraseña;

    @NotEmpty(message = "Campo obligatorio")
    private String lista;

    public Cliente getCliente(){
        return new Cliente(
                getId(),
                getNombre(),
                getCorreo(),
                getContraseña(),
                getLista()
        );
    }
}
