package gilko.marcin.bookstore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BK_KATEGORIA")
public class Kategoria {

	private Long id;
	private String name;
	private String description;
	
	public Kategoria() {
	}
	public Kategoria(Long id, String name, String description) {
		this.id = id;
		this.name= name;
		this.description = description;
	}
	@Id
	@Column(name = "ID_KATEGORII")
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "NAZWA_KATEGORII")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "OPIS_KATEGORII")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
