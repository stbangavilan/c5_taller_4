package py.edu.ucom.inventario.modelos.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "productos",
       indexes = {
           @Index(name = "idx_productos_unidad", columnList = "id_unidad"),
           @Index(name = "idx_productos_categoria", columnList = "id_categoria")
       })
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long idProducto;

    @NotBlank
    @Size(max = 64)
    @Column(name = "sku", nullable = false, unique = true, length = 64)
    private String sku;

    @NotBlank
    @Size(max = 150)
    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_unidad", nullable = false)
    private UnidadMedida unidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @Column(name = "peso_por_unidad_kg", precision = 12, scale = 3)
    private BigDecimal pesoPorUnidadKg;

    @Column(name = "volumen_por_unidad_l", precision = 12, scale = 3)
    private BigDecimal volumenPorUnidadL;

    // getters y setters
    public Long getIdProducto() { return idProducto; }
    public void setIdProducto(Long idProducto) { this.idProducto = idProducto; }

    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public UnidadMedida getUnidad() { return unidad; }
    public void setUnidad(UnidadMedida unidad) { this.unidad = unidad; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    public BigDecimal getPesoPorUnidadKg() { return pesoPorUnidadKg; }
    public void setPesoPorUnidadKg(BigDecimal v) { this.pesoPorUnidadKg = v; }

    public BigDecimal getVolumenPorUnidadL() { return volumenPorUnidadL; }
    public void setVolumenPorUnidadL(BigDecimal v) { this.volumenPorUnidadL = v; }
}
