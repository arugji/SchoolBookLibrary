package com.arda.app.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.arda.app.entity.Book;
import com.arda.app.entity.Library;
import com.arda.app.entity.School;
import com.arda.app.model.SchoolLibraryBookModel;
import com.arda.app.repository.LibraryRepository;
import com.arda.app.repository.SchoolRepository;

@Controller
public class WebController {

	@Autowired
	private SchoolRepository repository;

	@Autowired
	private LibraryRepository libraryRepository;

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("schools", repository.findAll());
		return "index";
	}

	@GetMapping("/addSchool")
	public String addSchool(Model model,
			@RequestParam(name = "schoolId", required = false, defaultValue = "0") long schoolId) {
		School sc = new School();
		if (schoolId > 0) {
			sc = repository.getById(schoolId);
		}
		model.addAttribute("school", sc);
		return "addSchool";
	}

	@PostMapping("/saveSchool")
	public String saveSchool(@ModelAttribute("school") School sc) {
		if (Objects.nonNull(sc.getId()) && sc.getId() > 0) {
			School ss = repository.getById(sc.getId());
			ss.setCity(sc.getCity());
			ss.setName(sc.getName());
			repository.save(ss);
		} else {
			repository.save(sc);
		}
		return "redirect:/";
	}

	@GetMapping("/school/delete")
	public String deleteSchool(@RequestParam("id") long id) {
		repository.deleteById(id);
		return "redirect:/";
	}

	@GetMapping("/addLibrary")
	public String addLibrary(@RequestParam("schoolId") long schoolId, Model model) {
		SchoolLibraryBookModel lib = new SchoolLibraryBookModel();
		lib.setSchoolId(schoolId);
		School sc = repository.findById(schoolId).get();
		model.addAttribute("schoolName", sc.getName());
		model.addAttribute("lib", lib);
		return "addLibrary";
	}

	@PostMapping("/saveLibrary")
	public String saveLibrary(@ModelAttribute("lib") SchoolLibraryBookModel lm) {
		School sc = repository.findById(lm.getSchoolId()).get();
		Library lb = new Library();
		lb.setLibraryName(lm.getLibraryName());
		sc.getLibraries().add(lb);
		repository.save(sc);
		return "redirect:/";
	}

	@GetMapping("/addBook")
	public String addBook(@RequestParam("libId") long libId, Model model) {
		SchoolLibraryBookModel book = new SchoolLibraryBookModel();
		book.setLibraryId(libId);
		Library lb = libraryRepository.findById(libId).get();
		model.addAttribute("libName", lb.getLibraryName());
		model.addAttribute("book", book);
		return "addBook";
	}

	@PostMapping("/saveBook")
	public String saveBook(@ModelAttribute("book") SchoolLibraryBookModel lm) {
		Library lb = libraryRepository.findById(lm.getLibraryId()).get();
		Book b = new Book();
		b.setBookName(lm.getBookName());
		b.setShelfNo(lm.getShelfNo());
		lb.getBooks().add(b);
		libraryRepository.save(lb);
		return "redirect:/";
	}
}
