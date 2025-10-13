package py.edu.ucom.inventario.api;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import py.edu.ucom.inventario.core.generico.RecursoGenerico;
import py.edu.ucom.inventario.core.generico.RepositorioGenerico;
import py.edu.ucom.inventario.modelos.entidades.Categoria;
import py.edu.ucom.inventario.modelos.repositorios.CategoriasRepositorio;

@ApplicationScoped
@Path("/api/categorias")
public class CategoriasRecurso extends RecursoGenerico<Categoria, Long> {

    @Inject
    CategoriasRepositorio repo;

    @Override
    protected RepositorioGenerico<Categoria, Long> getRepositorio() {
        return repo;
    }
}