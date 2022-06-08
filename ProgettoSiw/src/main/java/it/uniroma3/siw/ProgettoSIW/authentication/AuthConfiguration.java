package it.uniroma3.siw.ProgettoSIW.authentication;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import it.uniroma3.siw.ProgettoSIW.handler.MyAuthSuccessHandler;

import static it.uniroma3.siw.ProgettoSIW.model.Credentials.ADMIN_ROLE;

@Configuration
@EnableWebSecurity
public class AuthConfiguration extends WebSecurityConfigurerAdapter {
	
	@Bean
	public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
	    return new MyAuthSuccessHandler();
	}

    @Autowired
    DataSource datasource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        		.csrf().disable()
                // authorization paragraph: qui definiamo chi può accedere a cosa
                .authorizeRequests()

                // chiunque (autenticato o no) può accedere alle pagine index, login, register, ai css e alle immagini
                .antMatchers(HttpMethod.GET, "/",
                        "/index",
                        "/login*",
                        "/collection/*",
                        "/collections",
                        "/NFTS",
                        "/signup_form",
                        "/register",
                        "/css/**", 
                        "/restNFT/**",
                        "/images/**").permitAll()

                // chiunque (autenticato o no) può mandare richieste POST al punto di accesso per login e register
                .antMatchers(HttpMethod.POST, "/login", "/register").permitAll()
                .antMatchers(HttpMethod.PUT,"/restNFT/**").permitAll()
                .antMatchers(HttpMethod.POST,"/restNFT/**").permitAll()
                .antMatchers(HttpMethod.DELETE,"/restNFT/**").permitAll()
                // solo gli utenti autenticati con ruolo ADMIN possono accedere a risorse con path /admin/**
                .antMatchers(HttpMethod.GET, "/admin/**").hasAnyAuthority(ADMIN_ROLE)
                .antMatchers(HttpMethod.POST, "/admin/**").hasAnyAuthority(ADMIN_ROLE)

                // tutti gli utenti autenticati possono accere alle pagine rimanenti
                .anyRequest().authenticated()

                // login paragraph: qui definiamo come è gestita l'autenticazione
                // usiamo il protocollo formlogin
                .and().formLogin()

                // la pagina di login si trova a /login
                // NOTA: Spring gestisce il post di login automaticamente
                .loginPage("/login")
//                .usernameParameter("username")
//                .passwordParameter("password")      
                .loginProcessingUrl("/login")
                // se il login ha successo, si viene rediretti al path /default
                .defaultSuccessUrl("/process_login")
//                .successHandler(myAuthenticationSuccessHandler())
//                .failureUrl("/loginPage?error")
                // logout paragraph: qui definiamo il logout
                .and().logout()

                // il logout è attivato con una richiesta GET a "/logout"
                .logoutUrl("/logout")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))

                // in caso di successo, si viene reindirizzati alla /index page

                .logoutSuccessUrl("/index")
                .invalidateHttpSession(true)
                .clearAuthentication(true).permitAll();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                //use the autowired datasource to access the saved credentials
                .dataSource(this.datasource)
                //retrieve username and role
                .authoritiesByUsernameQuery("SELECT username, role FROM credentials WHERE username=?")
                //retrieve username, password and a boolean flag specifying whether the user is enabled or not (always enabled in our case)
                .usersByUsernameQuery("SELECT username, password, 1 as enabled FROM credentials WHERE username=?");

        /*auth.inMemoryAuthentication()
                .withUser("user1").password(passwordEncoder().encode("user1")).roles("USER")
                .and()
                .withUser("user2").password(passwordEncoder().encode("user2")).roles("USER")
                .and()
                .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN_ROLE");*/
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
        
     // Filtro Custom JWT 
//        httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }
}
