package com.arda.app.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Library")
public class Library {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "libraryName", length = 100, nullable = false)
	private String libraryName;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "zipcode", length = 5, nullable = false)
	private int zipcode;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Book> books;

}
