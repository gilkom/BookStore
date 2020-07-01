package gilko.marcin.bookstore.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Bk_kategoria {
	private int id;
	private String name;
	private String description;
	
	public Bk_kategoria() {
	}
	public Bk_kategoria(int id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}
	@Id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
