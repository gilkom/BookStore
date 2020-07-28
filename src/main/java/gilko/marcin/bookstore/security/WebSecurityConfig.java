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
            .usersByUsernameQuery("select email_klienta as username, password as password,blokada_konta as enabled from bk_klient where email_klienta=?")
            .authoritiesByUsernameQuery("select email_klienta as username, rola as role from bk_klient where email_klienta=?")
        ;
    }
/*@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception{
		auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
		.dataSource(dataSource)
		//.usersByUsernameQuery("select bk_klient.email_klienta as username, bk_klient.password  as password, 1 as enabled from bk_klient where bk_klient.email_klienta=?")
		.usersByUsernameQuery("select username, password, 1 as enabled from userss where username=?")
		//.authoritiesByUsernameQuery("select bk_klient.email_klienta, bk_klient.rola from bk_klient where bk_klient.email_klienta=?")
		.authoritiesByUsernameQuery("select username, rola from userss where username =?")
				;
	}
	*/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .anyRequest().authenticated()
            .and()
            .formLogin().permitAll()
            .and()
            .logout().permitAll();     
    }

}
