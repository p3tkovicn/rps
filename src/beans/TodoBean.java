package beans;

import java.time.LocalDateTime;
import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import data.Todo;

@Singleton
public class TodoBean {
	@PersistenceContext(name = "prod")
	EntityManager em;
	
	public List<Todo> getTodo() {
		return em.createQuery("select t from Todo t", Todo.class).getResultList();
	}
	
	public Long getMaxId() {
		return em.createQuery("select count(t) from Todo t", Long.class).getSingleResult();
	}
	
	public JSONArray toJson(List<Todo> todo) {
		JSONArray ja = new JSONArray();
		
		for(Todo item : todo) {
			ja.add(item.toJson());
		}
		
		return ja;
	}
	
	public Todo find(Long id) {
		return em.find(Todo.class, id);
	}
	
	public void save(Todo todo) {
		em.persist(todo);
	}
	
	public void update(Todo todo) {
		Todo old = find(todo.getId());
		
		old.setTask(todo.getTask());
		old.setDescription(todo.getDescription());
		
		em.merge(old);
	}
	
	public void delete(Long id) {
		Todo todo = find(id);
		
		em.remove(todo);
	}
	
	public void finish(Long id) {
		Todo todo = find(id);
		
		todo.finish(LocalDateTime.now().toString());
	}
}
