package py.edu.ucom.inventario.modelos.entidades;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "unidades_medida")
public class UnidadMedida implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_unidad")
    private Long idUnidad;

    @Column(name = "codigo", length = 10, nullable = false, unique = true)
    private String codigo;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name = "dimension", length = 10, nullable = false)
    private String dimension; // UNIDAD / PESO / VOLUMEN

    public Long getIdUnidad() { return idUnidad; }
    public void setIdUnidad(Long idUnidad) { this.idUnidad = idUnidad; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDimension() { return dimension; }
    public void setDimension(String dimension) { this.dimension = dimension; }
}
