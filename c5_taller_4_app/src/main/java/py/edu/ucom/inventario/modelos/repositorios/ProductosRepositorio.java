package py.edu.ucom.inventario.modelos.repositorios;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import py.edu.ucom.inventario.modelos.entidades.Producto;

import java.util.List;

@ApplicationScoped
public class ProductosRepositorio {

    @PersistenceContext
    EntityManager em;

    public List<Producto> listar() {
        return em.createQuery("SELECT p FROM Producto p ORDER BY p.idProducto", Producto.class)
                 .getResultList();
    }

    public Producto porId(Long id) {
        return em.find(Producto.class, id);
    }

    @Transactional
    public Producto crear(Producto p) {
        em.persist(p);
        return p;
    }

    @Transactional
    public Producto actualizar(Producto p) {
        return em.merge(p);
    }

    @Transactional
    public void eliminar(Long id) {
        Producto ref = em.getReference(Producto.class, id);
        em.remove(ref);
    }
}
