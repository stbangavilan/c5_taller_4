package py.edu.ucom.inventario.core.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public class TransferenciaRequest {
    @NotNull public Long idProducto;
    @NotNull public Long idLocalOrigen;
    @NotNull public Long idLocalDestino;
    @NotNull @Positive public BigDecimal cantidad;
    public String referencia;
}
