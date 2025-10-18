package py.edu.ucom.inventario.api;

import py.edu.ucom.inventario.modelos.entidades.Stock;
import py.edu.ucom.inventario.modelos.repositorios.StockRepositorio;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.util.List;

@Path("/api/stock")
@Produces(MediaType.APPLICATION_JSON)
public class StockRecurso {

    @Inject StockRepositorio repo;

    @GET
    public Response listar(@QueryParam("producto") Long idProducto,
                           @QueryParam("local") Long idLocal) {
        List<Stock> data = repo.listar(idProducto, idLocal);
        return Response.ok(data).build();
    }
}
