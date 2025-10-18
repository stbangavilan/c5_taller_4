package py.edu.ucom.inventario.modelos.repositorios;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import py.edu.ucom.inventario.modelos.entidades.Local;

import java.util.List;

@ApplicationScoped
public class LocalesRepositorio {

    @PersistenceContext
    EntityManager em;

    public List<Local> listar() {
        return em.createQuery("SELECT l FROM Local l ORDER BY l.idLocal", Local.class)
                 .getResultList();
    }

    public Local porId(Long id) {
        return em.find(Local.class, id);
    }

    @Transactional
    public Local crear(Local l) {
        em.persist(l);
        return l;
    }

    @Transactional
    public Local actualizar(Local l) {
        return em.merge(l);
    }

    @Transactional
    public void eliminar(Long id) {
        Local ref = em.getReference(Local.class, id);
        em.remove(ref);
    }
}
