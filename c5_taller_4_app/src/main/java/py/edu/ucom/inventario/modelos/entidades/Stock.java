package py.edu.ucom.inventario.modelos.entidades;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "stock",
       uniqueConstraints = @UniqueConstraint(name = "uq_stock_prod_local",
                                            columnNames = {"id_producto","id_local"}),
       indexes = {
           @Index(name = "idx_stock_producto", columnList = "id_producto"),
           @Index(name = "idx_stock_local",    columnList = "id_local")
       })
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_stock")
    private Long idStock;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_local", nullable = false)
    private Local local;

    @Column(name = "cantidad", nullable = false, precision = 18, scale = 3)
    private BigDecimal cantidad = BigDecimal.ZERO;

    // getters y setters
    public Long getIdStock() { return idStock; }
    public void setIdStock(Long idStock) { this.idStock = idStock; }

    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }

    public Local getLocal() { return local; }
    public void setLocal(Local local) { this.local = local; }

    public BigDecimal getCantidad() { return cantidad; }
    public void setCantidad(BigDecimal cantidad) { this.cantidad = cantidad; }
}
