package services;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import beans.TodoBean;
import data.Todo;

//from the moment you read this Pink Panther theme song
//is in your head todo; todo; todo todo todo; todo todooo; to do do doo dooo
@Path("todo")
public class TodoService {
	private static final String success = "Success.";
	@Inject
	TodoBean tb;
	
	//just to check if service responds
	@Path("test")
	@Produces(MediaType.TEXT_PLAIN)
	@GET
	public Response test() {
		String response = "this is test";
		return Response.ok().entity(response).build();
	}
	
	
	
	@Path("delete/{id}")
	@Consumes(MediaType.TEXT_PLAIN)
	@DELETE
	public Response delete(@PathParam("id") Long id) {
		tb.delete(id);
		
		return Response.ok().entity(success).build();
	}
	
	@Path("details")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Response details(@QueryParam("id") Long id) {
		Todo todo = tb.find(id);
		todo.toJson();
		return Response.ok().entity(todo).build();
	}
	
	@Path("finish")
	@PUT
	public Response finish(@QueryParam("id") Long id) {
		tb.finish(id);
		
		return Response.ok().entity(success).build();
	}
	
	@Path("index")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Response index() {
		List<Todo> todo = tb.getTodo();
		JSONArray ja = tb.toJson(todo);

		return Response.ok().entity(ja).build();
	}
	
	@Path("save")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	@POST
	public Response save(@QueryParam("task") String task, 
						 @QueryParam("description") String description) {
		Todo todo = new Todo();
		todo.setTask(task);
		todo.setDescription(description);
		todo.setId(tb.getMaxId().longValue() + 1);
		
		tb.save(todo);
		
		return Response.ok().entity(success).build();
	}
	
	@Path("update")
	@Consumes(MediaType.APPLICATION_JSON)
	@PUT
	public Response update(String request) throws ParseException {
		JSONParser jp = new JSONParser();
		JSONObject jo = (JSONObject) jp.parse(request);
		Todo todo = new Todo(jo);
		tb.update(todo);
		
		return Response.ok().entity(success).build();
	}
}
