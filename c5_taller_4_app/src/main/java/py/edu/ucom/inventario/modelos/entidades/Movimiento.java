package py.edu.ucom.inventario.modelos.entidades;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "movimientos",
       indexes = {
           @Index(name = "idx_mov_prod_fecha", columnList = "id_producto, fecha_movimiento DESC"),
           @Index(name = "idx_mov_tipo_fecha", columnList = "tipo, fecha_movimiento DESC")
       })
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimiento")
    private Long idMovimiento;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 15)
    private TipoMovimiento tipo;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_local_origen")
    private Local localOrigen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_local_destino")
    private Local localDestino;

    @Column(name = "cantidad", nullable = false, precision = 18, scale = 3)
    private BigDecimal cantidad;

    @Column(name = "referencia", length = 120)
    private String referencia;

    @Column(name = "fecha_movimiento", nullable = false)
    private OffsetDateTime fechaMovimiento;

    // getters y setters
    public Long getIdMovimiento() { return idMovimiento; }
    public void setIdMovimiento(Long idMovimiento) { this.idMovimiento = idMovimiento; }

    public TipoMovimiento getTipo() { return tipo; }
    public void setTipo(TipoMovimiento tipo) { this.tipo = tipo; }

    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }

    public Local getLocalOrigen() { return localOrigen; }
    public void setLocalOrigen(Local localOrigen) { this.localOrigen = localOrigen; }

    public Local getLocalDestino() { return localDestino; }
    public void setLocalDestino(Local localDestino) { this.localDestino = localDestino; }

    public BigDecimal getCantidad() { return cantidad; }
    public void setCantidad(BigDecimal cantidad) { this.cantidad = cantidad; }

    public String getReferencia() { return referencia; }
    public void setReferencia(String referencia) { this.referencia = referencia; }

    public OffsetDateTime getFechaMovimiento() { return fechaMovimiento; }
    public void setFechaMovimiento(OffsetDateTime fechaMovimiento) { this.fechaMovimiento = fechaMovimiento; }
}
