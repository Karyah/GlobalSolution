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
import br.com.fiap.bo.PontoBO;
import br.com.fiap.entity.PontoTO;

@Path("/ponto")
public class PontoResource {
	
	PontoBO pontoBO = new PontoBO(); 
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response inserir(PontoTO ponto, @Context UriInfo uriInfo) {
		pontoBO.inserir(ponto);
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(Integer.toString(ponto.getId()));
		return Response.created(builder.build()).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<PontoTO> listar(){
		return pontoBO.listar();
	}
	
	@GET
	@Path("/{endereco}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PontoTO> buscarPorEndereco(@PathParam("endereco") String endereco) {
		return pontoBO.buscarPorEndereco(endereco);
	}
	
	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response atualizar(PontoTO ponto, @PathParam("id") int id) {
		ponto.setId(id);
		pontoBO.atualizar(ponto);
		return Response.ok().build();
	}
	
	@DELETE 
	@Path("/{id}")
	public void excluir(@PathParam("id") int id) {
		pontoBO.deletar(id);
	}
	
}
