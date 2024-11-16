package com.example.demo.Controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Models.Book;
import com.example.demo.Repository.BookRepository;
import org.springframework.ui.Model; 

@Controller
public class BookController {

	@Autowired
	BookRepository books;
	
	@PostMapping("/book/add")
	public String addBook(Book book)
	{
		books.save(book);
		return "redirect:/book/display";
	}
	
	@GetMapping("/book/display")
    public String listBooks(Model model) 
	{ 
        List<Book> bookList = (List<Book>) books.findAll();
        model.addAttribute("books", bookList);  
        return "display";  
    }
	
	@GetMapping("/book/delete/{id}")
    public String deleteBook(@PathVariable("id") Integer id) 
	{
        books.deleteById(id);  
        return "redirect:/book/display";  
    }
	
	@GetMapping("/book/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model)
	{
	    Book book = books.findById(id).orElse(null);
	    model.addAttribute("book", book);
	    return "update"; 
	}
	
	@PostMapping("/book/update/{id}")
	public String updateBook(@PathVariable("id") Integer id, Book updatedBook) 
	{
	    updatedBook.setId(id);
	    books.save(updatedBook);	    
	    return "redirect:/book/display";
	}

}
