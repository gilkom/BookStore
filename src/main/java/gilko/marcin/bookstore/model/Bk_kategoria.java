package gilko.marcin.bookstore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BK_KATEGORIA")
public class Bk_kategoria {

	private int id;
	private String nazwa_kategorii;
	private String description;
	
	public Bk_kategoria() {
	}
	public Bk_kategoria(int id, String nazwa_kategorii, String description) {
		this.id = id;
		this.nazwa_kategorii = nazwa_kategorii;
		this.description = description;
	}
	@Id
	@Column(name = "ID_KATEGORII")
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "NAZWA_KATEGORII")
	public String getName() {
		return nazwa_kategorii;
	}
	public void setName(String nazwa_kategorii) {
		this.nazwa_kategorii = nazwa_kategorii;
	}
	@Column(name = "OPIS_KATEGORII")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
