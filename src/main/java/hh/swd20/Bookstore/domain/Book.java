package hh.swd20.Bookstore.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String title, author, isbn;
	private double price;
	private int year;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="categoriaid")
	private Category category;
	
	public Book() {
		super();
		this.title = null;
		this.author = null;
		this.isbn = null;
		this.price = 0;
		this.year = 0;
	}
	public Book(String title, String author, String isbn, double price,
			int year, Category category) {
		super();
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.price = price;
		this.year = year;
		this.category = category;
	}
	
	public Book(String title, String author, String isbn, double price,
			int year) {
		super();
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.price = price;
		this.year = year;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getTitle() {
		return title;
	}
	public String getAuthor() {
		return author;
	}
	public String getIsbn() {
		return isbn;
	}
	public double getPrice() {
		return price;
	}
	public int getYear() {
		return year;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	@Override
	public String toString() {
		if (this.category != null)
		return "Book title" + title + ", author: " + author + ", isbn: " + isbn + ", price: " + price + ", category: " + this.getCategory();		
	else
		return "Book title" + title + ", author: " + author + ", isbn: " + isbn + ", price: " + price;
}
}
