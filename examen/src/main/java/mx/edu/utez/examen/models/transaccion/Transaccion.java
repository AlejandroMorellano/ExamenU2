package mx.edu.utez.examen.models.transaccion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.examen.models.cliente.Cliente;
import mx.edu.utez.examen.models.producto.Producto;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "transaccion")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Transaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 6)
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "prodcuto_id", nullable = false)
    private Producto producto;

    public Transaccion(Long id, Date fecha, Cliente cliente, Producto producto){
        this.id = id;
        this.fecha = fecha;
        this.cliente = cliente;
        this.cliente.setTransaccions((List<Transaccion>) this);
        this.producto = producto;
        this.producto.setTrasaccions((List<Transaccion>) this);
    }
}
