package br.com.fiap.resource;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import br.com.fiap.bo.BicicletaBO;
import br.com.fiap.entity.BicicletaTO;


@Path("/bicicleta")
public class BicicletaResource {
	
	BicicletaBO bikeBO = new BicicletaBO();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response inserir(BicicletaTO bicicleta, @Context UriInfo uriInfo) {
		bikeBO.inserir(bicicleta);
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(Integer.toString(bicicleta.getId()));
		return Response.created(builder.build()).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<BicicletaTO> listar(){
		return bikeBO.listar();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public BicicletaTO buscar(@PathParam("id") int id) {
		return bikeBO.buscarPorId(id);
	}
	
	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response atualizar(BicicletaTO bicicleta, @PathParam("id") int id) {
		bicicleta.setId(id);
		bikeBO.atualizar(bicicleta);
		return Response.ok().build();
	}
	
	@DELETE 
	@Path("/{id}")
	public void excluir(@PathParam("id") int id) {
		bikeBO.deletar(id);
	}
	
}
