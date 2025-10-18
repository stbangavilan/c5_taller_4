package py.edu.ucom.inventario.api;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import py.edu.ucom.inventario.modelos.entidades.Local;
import py.edu.ucom.inventario.servicios.LocalesServicio;

import java.net.URI;

@Path("/api/locales")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LocalesRecurso {

    @Inject
    LocalesServicio servicio;

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
    public Response crear(@Valid Local l, @jakarta.ws.rs.core.Context UriInfo uriInfo) {
        Local creado = servicio.crear(l);
        URI loc = uriInfo.getAbsolutePathBuilder().path(String.valueOf(creado.getIdLocal())).build();
        return Response.created(loc).entity(creado).build();
    }

    @PUT
    @Path("/{id}")
    public Response actualizar(@PathParam("id") Long id, @Valid Local l) {
        return Response.ok(servicio.actualizar(id, l)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") Long id) {
        servicio.eliminar(id);
        return Response.noContent().build();
    }
}
