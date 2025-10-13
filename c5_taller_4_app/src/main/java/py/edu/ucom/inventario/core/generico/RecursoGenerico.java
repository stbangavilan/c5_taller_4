package py.edu.ucom.inventario.core.generico;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.Serializable;
import java.util.List;

public abstract class RecursoGenerico<T, ID extends Serializable> {

    protected abstract RepositorioGenerico<T, ID> getRepositorio();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<T> obtenerTodos() {
        return getRepositorio().listarTodos();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public T obtenerPorId(@PathParam("id") ID id) {
        return getRepositorio().obtenerPorId(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crear(T entidad) {
        var creado = getRepositorio().crear(entidad);
        return Response.status(Response.Status.CREATED).entity(creado).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public T actualizar(@PathParam("id") ID id, T entidad) {
        return getRepositorio().actualizar(entidad);
    }

    @DELETE
    @Path("/{id}")
    public void eliminar(@PathParam("id") ID id) {
        getRepositorio().eliminar(id);
    }
}
