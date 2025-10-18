package py.edu.ucom.inventario.servicios;

import py.edu.ucom.inventario.core.dto.*;
import py.edu.ucom.inventario.modelos.entidades.*;
import py.edu.ucom.inventario.modelos.repositorios.*;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@ApplicationScoped
public class MovimientosServicio {

    @Inject EntityManager em;
    @Inject StockRepositorio stockRepo;
    @Inject MovimientosRepositorio movRepo;

    private Producto getProducto(Long id) {
        Producto p = em.find(Producto.class, id);
        if (p == null) throw new NotFoundException("Producto no encontrado: " + id);
        return p;
    }
    private Local getLocal(Long id) {
        Local l = em.find(Local.class, id);
        if (l == null) throw new NotFoundException("Local no encontrado: " + id);
        return l;
    }

    private Movimiento newMovimiento(TipoMovimiento tipo, Producto prod, Local origen, Local destino,
                                     BigDecimal cantidad, String ref) {
        Movimiento m = new Movimiento();
        m.setTipo(tipo);
        m.setProducto(prod);
        m.setLocalOrigen(origen);
        m.setLocalDestino(destino);
        m.setCantidad(cantidad);
        m.setReferencia(ref);
        m.setFechaMovimiento(OffsetDateTime.now());
        return m;
    }

    @Transactional
    public Movimiento entrada(EntradaRequest r) {
        if (r.cantidad == null || r.cantidad.signum() <= 0) throw new BadRequestException("cantidad > 0");
        Producto prod = getProducto(r.idProducto);
        Local dest = getLocal(r.idLocalDestino);

        Stock sDest = stockRepo.getOrCreateForUpdate(prod, dest);
        sDest.setCantidad(sDest.getCantidad().add(r.cantidad));

        Movimiento mov = newMovimiento(TipoMovimiento.ENTRADA, prod, null, dest, r.cantidad, r.referencia);
        return movRepo.crear(mov);
    }

    @Transactional
    public Movimiento salida(SalidaRequest r) {
        if (r.cantidad == null || r.cantidad.signum() <= 0) throw new BadRequestException("cantidad > 0");
        Producto prod = getProducto(r.idProducto);
        Local org = getLocal(r.idLocalOrigen);

        Stock sOrg = stockRepo.getOrCreateForUpdate(prod, org);
        if (sOrg.getCantidad().compareTo(r.cantidad) < 0) throw new BadRequestException("Stock insuficiente");
        sOrg.setCantidad(sOrg.getCantidad().subtract(r.cantidad));

        Movimiento mov = newMovimiento(TipoMovimiento.SALIDA, prod, org, null, r.cantidad, r.referencia);
        return movRepo.crear(mov);
    }

    @Transactional
    public Movimiento transferencia(TransferenciaRequest r) {
        if (r.cantidad == null || r.cantidad.signum() <= 0) throw new BadRequestException("cantidad > 0");
        if (r.idLocalOrigen.equals(r.idLocalDestino)) throw new BadRequestException("Origen y destino deben ser distintos");

        Producto prod = getProducto(r.idProducto);
        Local org = getLocal(r.idLocalOrigen);
        Local dest = getLocal(r.idLocalDestino);

        Stock sOrg = stockRepo.getOrCreateForUpdate(prod, org);
        Stock sDest = stockRepo.getOrCreateForUpdate(prod, dest);

        if (sOrg.getCantidad().compareTo(r.cantidad) < 0) throw new BadRequestException("Stock insuficiente");
        sOrg.setCantidad(sOrg.getCantidad().subtract(r.cantidad));
        sDest.setCantidad(sDest.getCantidad().add(r.cantidad));

        Movimiento mov = newMovimiento(TipoMovimiento.TRANSFERENCIA, prod, org, dest, r.cantidad, r.referencia);
        return movRepo.crear(mov);
    }

    @Transactional
    public Movimiento ajuste(AjusteRequest r) {
        if (r.cantidad == null || r.cantidad.signum() <= 0) throw new BadRequestException("cantidad > 0");
        if (r.signo == null) throw new BadRequestException("Debe indicar signo = MAS|MENOS");

        Producto prod = getProducto(r.idProducto);
        Local loc = getLocal(r.idLocal);

        Stock stock = stockRepo.getOrCreateForUpdate(prod, loc);

        Local origen = null, destino = null;
        switch (r.signo.toUpperCase()) {
            case "MAS" -> {
                stock.setCantidad(stock.getCantidad().add(r.cantidad));
                destino = loc;
            }
            case "MENOS" -> {
                if (stock.getCantidad().compareTo(r.cantidad) < 0)
                    throw new BadRequestException("Ajuste MENOS excede el stock");
                stock.setCantidad(stock.getCantidad().subtract(r.cantidad));
                origen = loc;
            }
            default -> throw new BadRequestException("signo inválido: " + r.signo);
        }
        Movimiento mov = newMovimiento(TipoMovimiento.AJUSTE, prod, origen, destino, r.cantidad, r.referencia);
        return movRepo.crear(mov);
    }
}
