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
	

}
