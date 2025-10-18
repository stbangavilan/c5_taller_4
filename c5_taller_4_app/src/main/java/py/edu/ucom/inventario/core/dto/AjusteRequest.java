package py.edu.ucom.inventario.core.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public class AjusteRequest {
    @NotNull public Long idProducto;
    @NotNull public Long idLocal;
    @NotNull @Positive public BigDecimal cantidad; // magnitud
    @NotNull public String signo; // "MAS" | "MENOS"
    public String referencia;
}
