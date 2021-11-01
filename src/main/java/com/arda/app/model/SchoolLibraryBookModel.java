package com.arda.app.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class SchoolLibraryBookModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private long libraryId;
	private String libraryName;
	private long schoolId;
	private String bookName;
	private int shelfNo;
}
