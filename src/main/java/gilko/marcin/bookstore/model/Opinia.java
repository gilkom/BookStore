package gilko.marcin.bookstore.model;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	
	private int ocena;
	private String komentarz;
	
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
