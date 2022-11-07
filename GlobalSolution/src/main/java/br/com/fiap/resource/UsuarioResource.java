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
import br.com.fiap.bo.UsuarioBO;
import br.com.fiap.entity.UsuarioTO;

@Path("/usuario")
public class UsuarioResource {
	
	UsuarioBO usuarioBO = new UsuarioBO();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrar(UsuarioTO usuario, @Context UriInfo uriInfo) {
		usuarioBO.inserir(usuario);
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(Integer.toString(usuario.getId()));
		return Response.created(builder.build()).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<UsuarioTO> listar(){
		return usuarioBO.listar();
	}
	
//	@GET
//	@Path("/{id}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public UsuarioTO buscar(@PathParam("id") int id) {
//		return usuarioBO.buscarPorId(id);
//	}
	
	@GET
	@Path("/{login}")
	@Produces(MediaType.APPLICATION_JSON)
	public UsuarioTO buscar(@PathParam("login") String login) {
		return usuarioBO.buscarPorLogin(login);
	}
	
	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response atualizar(UsuarioTO usuario, @PathParam("id") int id) {
		usuario.setId(id);
		usuarioBO.atualizar(usuario);
		return Response.ok().build();
	}
	
	@DELETE 
	@Path("/{id}")
	public void excluir(@PathParam("id") int id) {
		usuarioBO.deletar(id);
	}
}
