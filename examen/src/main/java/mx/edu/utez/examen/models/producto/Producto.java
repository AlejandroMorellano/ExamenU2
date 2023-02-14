package mx.edu.utez.examen.models.producto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.examen.models.cliente.Cliente;
import mx.edu.utez.examen.models.transaccion.Transaccion;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "producto")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nombreProducto;

    @Column(nullable = false, length = 50)
    private String categoria;

    @Column(nullable = false, length = 50)
    private Double precio;

    @Column(nullable = false, length = 50)
    private Boolean status;

    @OneToMany(mappedBy = "producto")
    @JsonIgnore
    private List<Transaccion> transaccions;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
}
