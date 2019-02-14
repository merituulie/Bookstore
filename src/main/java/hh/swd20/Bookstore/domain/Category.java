package hh.swd20.Bookstore.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long categoryid;
	private String name;
	
	@OneToMany(mappedBy = "category")
	private List<Book> books;

	public Category() {
		super();
		name = null;
	}
	public Category(String name) {
		super();
		this.name = name;
	}
	public void setCategoryid(Long categoryid) {
		this.categoryid = categoryid;
	}
	public Long getCategoryid() {
		return categoryid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	@Override
	public String toString() {
		return "Category id: "+ categoryid +", name: "+ name;
	}
}