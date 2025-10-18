package py.edu.ucom.inventario.api;

import py.edu.ucom.inventario.core.dto.*;
import py.edu.ucom.inventario.modelos.entidades.Movimiento;
import py.edu.ucom.inventario.servicios.MovimientosServicio;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path("/api/movimientos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MovimientosRecurso {

    @Inject MovimientosServicio servicio;

    @POST @Path("/entrada")
    public Response entrada(@Valid EntradaRequest req, @Context UriInfo uri) {
        Movimiento m = servicio.entrada(req);
        var loc = uri.getAbsolutePathBuilder().path(m.getIdMovimiento().toString()).build();
        return Response.created(loc).entity(m).build();
    }

    @POST @Path("/salida")
    public Response salida(@Valid SalidaRequest req, @Context UriInfo uri) {
        Movimiento m = servicio.salida(req);
        var loc = uri.getAbsolutePathBuilder().path(m.getIdMovimiento().toString()).build();
        return Response.created(loc).entity(m).build();
    }

    @POST @Path("/transferencia")
    public Response transferencia(@Valid TransferenciaRequest req, @Context UriInfo uri) {
        Movimiento m = servicio.transferencia(req);
        var loc = uri.getAbsolutePathBuilder().path(m.getIdMovimiento().toString()).build();
        return Response.created(loc).entity(m).build();
    }

    @POST @Path("/ajuste")
    public Response ajuste(@Valid AjusteRequest req, @Context UriInfo uri) {
        Movimiento m = servicio.ajuste(req);
        var loc = uri.getAbsolutePathBuilder().path(m.getIdMovimiento().toString()).build();
        return Response.created(loc).entity(m).build();
    }
}
