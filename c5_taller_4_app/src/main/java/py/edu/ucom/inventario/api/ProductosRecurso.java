package py.edu.ucom.inventario.api;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import py.edu.ucom.inventario.modelos.entidades.Producto;
import py.edu.ucom.inventario.servicios.ProductosServicio;

import java.net.URI;

@Path("/api/productos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductosRecurso {

    @Inject
    ProductosServicio servicio;

    @GET
    public Response listar() {
        return Response.ok(servicio.listar()).build();
    }

    @GET
    @Path("/{id}")
    public Response porId(@PathParam("id") Long id) {
        return Response.ok(servicio.porId(id)).build();
    }

    @POST
    public Response crear(@Valid Producto p, @jakarta.ws.rs.core.Context UriInfo uriInfo) {
        Producto creado = servicio.crear(p);
        URI loc = uriInfo.getAbsolutePathBuilder().path(String.valueOf(creado.getIdProducto())).build();
        return Response.created(loc).entity(creado).build();
    }

    @PUT
    @Path("/{id}")
    public Response actualizar(@PathParam("id") Long id, @Valid Producto p) {
        return Response.ok(servicio.actualizar(id, p)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") Long id) {
        servicio.eliminar(id);
        return Response.noContent().build();
    }
}
