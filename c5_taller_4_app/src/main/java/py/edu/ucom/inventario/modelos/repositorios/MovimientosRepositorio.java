package py.edu.ucom.inventario.modelos.repositorios;

import py.edu.ucom.inventario.modelos.entidades.Movimiento;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class MovimientosRepositorio {

    @PersistenceContext EntityManager em;

    @Transactional
    public Movimiento crear(Movimiento m) {
        em.persist(m);
        return m;
    }

    public Movimiento porId(Long id) {
        return em.find(Movimiento.class, id);
    }
}
