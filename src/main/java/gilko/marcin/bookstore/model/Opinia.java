package gilko.marcin.bookstore.model;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "BK_OPINIA")
@AssociationOverrides({
	@AssociationOverride(name = "primaryKey.klient",
			joinColumns = @JoinColumn(name = "ID_KLIENTA")),
	@AssociationOverride(name = "primaryKey.ksiazka",
			joinColumns = @JoinColumn(name = "ID_KSIAZKI"))})
public class Opinia {
	@EmbeddedId
	private OpiniaId primaryKey = new OpiniaId();
	@NotBlank
	private int ocena;
	@NotBlank
	private String komentarz;
	
	
	public Opinia() {
		
	}
	public Opinia(OpiniaId opiniaId, int ocena, String komentarz) {
		this.primaryKey = opiniaId;
		this.ocena = ocena;
		this.komentarz = komentarz;
	}
	public OpiniaId getPrimaryKey() {
		return primaryKey;
	}
	
	public void setPrimaryKey(OpiniaId primaryKey) {
		this.primaryKey = primaryKey;
	}
	
	@Transient
	public Klient getKlient() {
		return getPrimaryKey().getKlient();
	}
	
	public void setKlient(Klient klient) {
		getPrimaryKey().setKlient(klient);
	}
	
	@Transient
	public Ksiazka getKsiazka() {
		return getPrimaryKey().getKsiazka();
	}
	
	public void setKsiazka(Ksiazka ksiazka) {
		getPrimaryKey().setKsiazka(ksiazka);
	}
	
	public int getOcena() {
		return ocena;
	}
	
	public void setOcena(int ocena) {
		this.ocena = ocena;
	}
	
	public String getKomentarz() {
		return komentarz;
	}
	
	public void setKomentarz(String komentarz) {
		this.komentarz = komentarz;
	}
	

}
