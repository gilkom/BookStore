package gilko.marcin.bookstore.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
 
    @Autowired
    private DataSource dataSource;
     
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
            .dataSource(dataSource)
            .usersByUsernameQuery("select email_uzytkownika as username, password as password,blokada_konta as enabled from bk_uzytkownik where email_uzytkownika=?")
            .authoritiesByUsernameQuery("select email_uzytkownika as username, rola as role from bk_uzytkownik where email_uzytkownika=?")
        ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        	.antMatchers("/","/lista_ksiazek","/wyswietl_ksiazke/*",
        			"/lista_ksiazek/page/{\\d+}/?sortField=[\\w+]&sortDir=[\\w+]&keyword=[\\w*]&nazwaKategorii=[\\w*]",
        			"/login", "/images/*", "/rejestracja", "/rejestracja/save").permitAll()
        	.antMatchers("/lista_kategorii", "/nowa_kategoria", "/nowa_kategoria/save", "/edytuj_kategorie/save", "/edytuj_kategorie/{\\d+}", "/usun_kategorie/{\\d+}").hasRole("ADMIN")
        	.antMatchers("/lista_wydawnictw","/nowe_wydawnictwo", "/nowe_wydawnictwo/save", "/edytuj_wydawnictwo/save").hasRole("ADMIN")
        	.antMatchers("/lista_autorow", "/nowy_autor", "/nowy_autor/save", "/edytuj_autora/save", "/usun_autora/save").hasRole("ADMIN")
        	.antMatchers("/lista_uzytkownikow", "/nowy_uzytkownik", "/nowy_uzytkownik/save", "/edytuj_uzytkownika/{id}", "/edytuj_uzytkownika/save", "/usun_uzytkownika/{id}").hasRole("ADMIN")
        	.antMatchers("/lista_opinii", "/nowa_opinia", "/nowa_opinia/save", "/edytuj_opinie/{id_uzytkownika}/{id_ksiazki}", "/edytuj_opinie/save", "/usun_opinie/{id_uzytkownika}/{id_ksiazki}").hasRole("ADMIN")
        	.antMatchers("/lista_klientow").hasRole("OPERATOR")
        	.antMatchers("/lista_zamowien_obsluga", "/wyswietl_zamowienie_obsluga/{id}").hasRole("OPERATOR")
        	.antMatchers("/lista_zamowien_obsluga", "/wyswietl_zamowienie_obsluga/{id}").hasRole("OPERATOR")
        	.antMatchers("/lista_zamowien_zamowione", "/wyswietl_zamowienie_zamowione/{id}", "/wyswietl_zamowienie_zamowione/skompletuj").hasRole("OPERATOR")
        	.antMatchers("/lista_zamowien_skompletowane", "/wyswietl_zamowienie_skompletowane/{id}", "/wyswietl_zamowienie_skompletowane/wyslij").hasRole("OPERATOR")
        	.antMatchers("/lista_zamowien_wyslane", "/wyswietl_zamowienie_wyslane/{id}", "/wyswietl_zamowienie_wyslane/zamknij").hasRole("OPERATOR")
        	.antMatchers("/lista_zamowien_zamkniete", "/wyswietl_zamowienie_zamkniete/{id}").hasRole("OPERATOR")
        	.antMatchers("/koszyk", "/wyswietl_zamowienie/{id}", "/edytuj_koszyk/{id_zamowienia}/{pozycja_zamowienia}").hasRole("USER")
        	.antMatchers("/edytuj_koszyk/save","/usun_z_koszyka/{id_zamowienia}/{pozycja_zamowienia}","/moje_zamowienia").hasRole("USER")
        	.antMatchers("/zloz_zamowienie","/edytuj_dane_adresowe","/edytuj_dane_adresowe/save").hasRole("USER")
            .anyRequest().authenticated()
            .and()
            .formLogin().loginPage("/login").permitAll()
            .and()
            .logout().logoutSuccessUrl("/").permitAll() ;   
    }

}
