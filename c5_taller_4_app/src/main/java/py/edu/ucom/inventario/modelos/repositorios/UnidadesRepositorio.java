package py.edu.ucom.inventario.modelos.repositorios;

import jakarta.enterprise.context.ApplicationScoped;
import py.edu.ucom.inventario.core.generico.RepositorioGenerico;
import py.edu.ucom.inventario.modelos.entidades.UnidadMedida;

@ApplicationScoped
public class UnidadesRepositorio extends RepositorioGenerico<UnidadMedida, Long> {
    @Override
    protected Class<UnidadMedida> getEntidad() {
        return UnidadMedida.class;
    }
}
