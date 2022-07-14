package tn.cita.app.domain.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
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
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import tn.cita.app.domain.UserRoleBasedAuthority;
import tn.cita.app.domain.listener.CredentialEntityListener;

@Entity
@Table(name = "credentials")
@EntityListeners(CredentialEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class Credential extends AbstractMappedEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column(unique = true, nullable = false)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false)
	private UserRoleBasedAuthority userRoleBasedAuthority;
	
	@Column(name = "is_enabled", nullable = false)
	private Boolean isEnabled;
	
	@Column(name = "is_account_non_expired", nullable = false)
	private Boolean isAccountNonExpired;
	
	@Column(name = "is_account_non_locked", nullable = false)
	private Boolean isAccountNonLocked;
	
	@Column(name = "is_credentials_non_expired", nullable = false)
	private Boolean isCredentialsNonExpired;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "credential")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Customer customer;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "credential")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Employee employee;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "credential")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<VerificationToken> verificationTokens;
	
}













