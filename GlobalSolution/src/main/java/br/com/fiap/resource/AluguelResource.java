package br.com.fiap.resource;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import br.com.fiap.bo.AluguelBO;
import br.com.fiap.entity.AluguelTO;


@Path("/aluguel")
public class AluguelResource {
	
	AluguelBO aluguelBO = new AluguelBO();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response inserir(AluguelTO aluguel,  @Context UriInfo uriInfo) {
		aluguelBO.inserir(aluguel);
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(Integer.toString(aluguel.getId()));
		return Response.created(builder.build()).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<AluguelTO> listar(){
		return aluguelBO.listar();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public AluguelTO buscar(@PathParam("id") int id) {
		return aluguelBO.buscarPorId(id);
	}
	
	@DELETE 
	@Path("/{id}")
	public void excluir(@PathParam("id") int id) {
		aluguelBO.deletar(id);
	}
}
