package com.arda.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arda.app.entity.Library;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {

}
