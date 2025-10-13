package py.edu.ucom.inventario.api;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import py.edu.ucom.inventario.core.generico.RecursoGenerico;
import py.edu.ucom.inventario.core.generico.RepositorioGenerico;
import py.edu.ucom.inventario.modelos.entidades.UnidadMedida;
import py.edu.ucom.inventario.modelos.repositorios.UnidadesRepositorio;

@ApplicationScoped
@Path("/api/unidades")
public class UnidadesRecurso extends RecursoGenerico<UnidadMedida, Long> {

    @Inject
    UnidadesRepositorio repo;

    @Override
    protected RepositorioGenerico<UnidadMedida, Long> getRepositorio() {
        return repo;
    }
}
