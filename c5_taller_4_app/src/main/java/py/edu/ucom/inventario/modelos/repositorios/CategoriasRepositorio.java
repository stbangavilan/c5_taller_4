package py.edu.ucom.inventario.modelos.repositorios;

import jakarta.enterprise.context.ApplicationScoped;
import py.edu.ucom.inventario.core.generico.RepositorioGenerico;
import py.edu.ucom.inventario.modelos.entidades.Categoria;

@ApplicationScoped
public class CategoriasRepositorio extends RepositorioGenerico<Categoria, Long> {
    @Override
    protected Class<Categoria> getEntidad() {
        return Categoria.class;
    }
}
