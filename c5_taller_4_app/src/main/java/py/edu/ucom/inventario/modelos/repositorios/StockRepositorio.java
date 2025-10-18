package py.edu.ucom.inventario.modelos.repositorios;

import py.edu.ucom.inventario.modelos.entidades.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class StockRepositorio {

    @PersistenceContext EntityManager em;

    public Optional<Stock> findByProductoYLocal(Long idProducto, Long idLocal) {
        var list = em.createQuery("""
                SELECT s FROM Stock s
                WHERE s.producto.idProducto = :p AND s.local.idLocal = :l
                """, Stock.class)
            .setParameter("p", idProducto)
            .setParameter("l", idLocal)
            .getResultList();
        return list.isEmpty() ? Optional.empty() : Optional.of(list.get(0));
    }

    /** Obtiene (o crea) y bloquea el registro para escribir. */
    @Transactional
    public Stock getOrCreateForUpdate(Producto producto, Local local) {
        var list = em.createQuery("""
                SELECT s FROM Stock s
                WHERE s.producto = :p AND s.local = :l
                """, Stock.class)
            .setParameter("p", producto)
            .setParameter("l", local)
            .setLockMode(LockModeType.PESSIMISTIC_WRITE)
            .getResultList();

        Stock s = list.isEmpty() ? null : list.get(0);
        if (s == null) {
            s = new Stock();
            s.setProducto(producto);
            s.setLocal(local);
            s.setCantidad(BigDecimal.ZERO);
            em.persist(s);
        } else {
            em.lock(s, LockModeType.PESSIMISTIC_WRITE);
        }
        return s;
    }

    public List<Stock> listar(Long idProducto, Long idLocal) {
        String jpql = "SELECT s FROM Stock s WHERE 1=1"
                    + (idProducto != null ? " AND s.producto.idProducto = :p" : "")
                    + (idLocal != null ? " AND s.local.idLocal = :l" : "");
        var q = em.createQuery(jpql, Stock.class);
        if (idProducto != null) q.setParameter("p", idProducto);
        if (idLocal != null) q.setParameter("l", idLocal);
        return q.getResultList();
    }
}
