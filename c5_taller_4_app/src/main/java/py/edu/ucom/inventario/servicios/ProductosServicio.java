package py.edu.ucom.inventario.servicios;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import py.edu.ucom.inventario.modelos.entidades.Producto;
import py.edu.ucom.inventario.modelos.repositorios.ProductosRepositorio;

import java.util.List;

@ApplicationScoped
public class ProductosServicio {

    @Inject
    ProductosRepositorio repo;

    public List<Producto> listar() {
        return repo.listar();
    }

    public Producto porId(Long id) {
        Producto p = repo.porId(id);
        if (p == null) throw new NotFoundException("Producto no encontrado: " + id);
        return p;
    }

    @Transactional
    public Producto crear(Producto p) {
        return repo.crear(p);
    }

    @Transactional
    public Producto actualizar(Long id, Producto p) {
        Producto actual = porId(id);
        // copiar campos que se pueden actualizar
        actual.setSku(p.getSku());
        actual.setNombre(p.getNombre());
        actual.setUnidad(p.getUnidad());
        actual.setCategoria(p.getCategoria());
        actual.setPesoPorUnidadKg(p.getPesoPorUnidadKg());
        actual.setVolumenPorUnidadL(p.getVolumenPorUnidadL());
        return repo.actualizar(actual);
    }

    @Transactional
    public void eliminar(Long id) {
        porId(id); // valida existencia
        repo.eliminar(id);
    }
}
