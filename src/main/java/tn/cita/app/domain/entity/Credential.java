package tn.cita.app.domain.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import tn.cita.app.domain.UserRoleBasedAuthority;

@Entity
@Table(name = "credentials")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class Credential extends AbstractMappedEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column(unique = true)
	private String username;
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	private UserRoleBasedAuthority userRoleBasedAuthority;
	
	@Column(name = "is_enabled")
	private Boolean isEnabled;
	
	@Column(name = "is_account_non_expired")
	private Boolean isAccountNonExpired;
	
	@Column(name = "is_account_non_locked")
	private Boolean isAccountNonLocked;
	
	@Column(name = "is_credentials_non_expired")
	private Boolean isCredentialsNonExpired;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "credential")
	private Customer customer;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "credential")
	private Employee employee;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "credential")
	private Set<VerificationToken> verificationTokens;
	
}












