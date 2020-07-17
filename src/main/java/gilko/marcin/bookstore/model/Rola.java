package gilko.marcin.bookstore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "BK_ROLA")
public class Rola {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_roli;
	@NotBlank(message="Nazwa jest obowiązkowa!")
	@Size(min=2, max=20)
	private String rola;
	
	public Rola() {
		
	}
	public Rola(Long id_roli, String rola) {
		this.id_roli = id_roli;
		this.rola = rola;
	}
	
	public Long getId_roli() {
		return id_roli;
	}
	public void setId_roli(Long id_roli) {
		this.id_roli = id_roli;
	}
	
	public String getRola() {
		return rola;
	}
	public void setRola(String rola) {
		this.rola = rola;
	}
	
}
