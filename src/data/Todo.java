package data;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.json.simple.JSONObject;

@Entity
public class Todo implements Serializable {
	public static final long serialVersionUID = -8040698538484289094L;
	
	@Id
	Long id;
	
	String createdAt;
	String description;
	boolean finished;
	String finishedAt;
	String task;
	
	public Todo() {
		this.createdAt = LocalDateTime.now().toString();
		this.finished = false;
		this.finishedAt = "not finished";
	}
	
	public Todo(Long id, String task, String description, boolean finished, String finishedAt) {
		this.id = id;
		
		this.createdAt = LocalDateTime.now().toString();
		this.description = description;
		this.finished = finished;
		this.finishedAt = finishedAt;
		this.task = task;
	}
	
	public Todo(JSONObject todo) {
		this.id = (Long) todo.get("id");
		
		this.createdAt = todo.get("createdAt").toString();
		this.description = todo.get("description").toString();
		this.finished = (Boolean) todo.get("finished");
		this.finishedAt = todo.get("finishedAt").toString();
		this.task = todo.get("task").toString();
	}
	
	
	
	public Long getId() {
		return id;
	}
	
	
	public String getCreatedAt() {
		return createdAt;
	}
	
	public String getDescription() {
		return description;
	}
	
	public boolean getFinished() {
		return finished;
	}
	
	public String getFinishedAt() {
		return createdAt;
	}
	
	public String getTask() {
		return task;
	}
	
	
	
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void finish(String finishedAt) {
		finished = true;
		setFinishedAt(finishedAt);
	}
	
	public void setFinishedAt(String finishedAt) {
		this.finishedAt = finishedAt;
	}
	
	public void setTask(String task) {
		this.task = task;
	}
	
	
	
	public JSONObject toJson() {
		JSONObject jo = new JSONObject();
		jo.put("id", id);
		jo.put("task", task);
		jo.put("description", description);
		jo.put("createdAt", createdAt);
		jo.put("finishedAt", finishedAt);
		jo.put("finished", finished);
		
		return jo;
	}
}
