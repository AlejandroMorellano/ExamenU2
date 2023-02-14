package mx.edu.utez.examen.models.cliente;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.examen.models.transaccion.Transaccion;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cliente")
@NoArgsConstructor
@Setter
@Getter
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    private String nombre;
    @Column(nullable = false, length = 50)
    private String correo;
    @Column(nullable = false, length = 50)
    private String contraseña;
    @Column(nullable = false, length = 50)
    private String lista;
    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private List<Transaccion> transaccions;

    public Cliente(Long id, String nombre, String correo, String contraseña, String lista){
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.contraseña = contraseña;
        this.lista = lista;
    }
}
