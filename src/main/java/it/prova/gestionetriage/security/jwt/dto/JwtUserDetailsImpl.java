package it.prova.gestionetriage.security.jwt.dto;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import it.prova.gestionetriage.model.User;
import it.prova.gestionetriage.model.StatoUtente;

public class JwtUserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;
	private final String username;
	private final String password;
	private String nome;
	private String cognome;
	private Date dataRegistrazione;
	private StatoUtente statoUtente;
	private final Collection<? extends GrantedAuthority> authorities;
	private final boolean enabled;
	

	public JwtUserDetailsImpl(String username, String password, String nome, String cognome, Date dataRegistrazione,StatoUtente statoUtente,
			Collection<? extends GrantedAuthority> authorities, boolean enabled) {
		super();
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.dataRegistrazione = dataRegistrazione;
		this.statoUtente = statoUtente;
		this.authorities = authorities;
		this.enabled = enabled;
		
	}

	public static JwtUserDetailsImpl build(User user) {
		List<GrantedAuthority> authorities = user.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName().name()))
                .collect(Collectors.toList());
		
        return new JwtUserDetailsImpl(
                user.getUsername(),
                user.getPassword(),
                user.getNome(),
                user.getCognome(),
                user.getDataRegistrazione(),
                user.getStatoUtente(),
                authorities,
                user.getEnabled()

               
        );
    }

	@Override
	public String getUsername() {
		return username;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public StatoUtente getStatoUtente() {
		return statoUtente;
	}

	public void setStatoUtente(StatoUtente statoUtente) {
		this.statoUtente = statoUtente;
	}

	public Date getDataRegistrazione() {
		return dataRegistrazione;
	}

	public void setDataRegistrazione(Date dataRegistrazione) {
		this.dataRegistrazione = dataRegistrazione;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		JwtUserDetailsImpl user = (JwtUserDetailsImpl) o;
		return Objects.equals(username, user.username);
	}

}