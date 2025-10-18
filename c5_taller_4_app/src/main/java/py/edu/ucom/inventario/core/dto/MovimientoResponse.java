package py.edu.ucom.inventario.core.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class MovimientoResponse {
    public Long idMovimiento;
    public String tipo;
    public Long idProducto;
    public Long idLocalOrigen;
    public Long idLocalDestino;
    public BigDecimal cantidad;
    public String referencia;
    public OffsetDateTime fechaMovimiento;
}
