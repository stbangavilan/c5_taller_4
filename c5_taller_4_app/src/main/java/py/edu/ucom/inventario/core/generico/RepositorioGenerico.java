package py.edu.ucom.inventario.core.generico;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@ApplicationScoped
public abstract class RepositorioGenerico<T, ID extends Serializable> {

    @Inject EntityManager em;

    protected abstract Class<T> getEntidad();

    public List<T> listarTodos() {
        String jpql = "SELECT e FROM " + getEntidad().getSimpleName() + " e";
        TypedQuery<T> q = em.createQuery(jpql, getEntidad());
        return q.getResultList();
    }

    public T obtenerPorId(ID id) {
        return em.find(getEntidad(), id);
    }

    @Transactional
    public T crear(T entidad) {
        em.merge(entidad);
        return entidad;
    }

    @Transactional
    public T actualizar(T entidad) {
        return em.merge(entidad);
    }

    @Transactional
    public void eliminar(ID id) {
        T ref = em.find(getEntidad(), id);
        if (ref != null) em.remove(ref);
    }
}
