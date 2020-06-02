package com.cir3.chessgame.domain;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "authority")
public class Authority implements GrantedAuthority {
	private static final long serialVersionUID = -6244318455911809051L;

	@Id @Column
	@GeneratedValue(generator = "seqAuthority")
	@SequenceGenerator(name = "seqAuthority", sequenceName = "seq_authority")
	private Long id;
	
	private String authority;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
}
