package py.edu.ucom.inventario.servicios;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import py.edu.ucom.inventario.modelos.entidades.Local;
import py.edu.ucom.inventario.modelos.repositorios.LocalesRepositorio;

import java.util.List;

@ApplicationScoped
public class LocalesServicio {

    @Inject
    LocalesRepositorio repo;

    public List<Local> listar() { return repo.listar(); }

    public Local porId(Long id) {
        Local l = repo.porId(id);
        if (l == null) throw new NotFoundException("Local no encontrado: " + id);
        return l;
    }

    @Transactional
    public Local crear(Local l) {
        return repo.crear(l);
    }

    @Transactional
    public Local actualizar(Long id, Local l) {
        Local actual = porId(id);
        actual.setNombre(l.getNombre());
        actual.setUbicacion(l.getUbicacion());
        return repo.actualizar(actual);
    }

    @Transactional
    public void eliminar(Long id) {
        porId(id);
        repo.eliminar(id);
    }
}
