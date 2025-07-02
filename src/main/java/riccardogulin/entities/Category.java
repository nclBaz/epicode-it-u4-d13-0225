package riccardogulin.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "categories")
public class Category {
	@Id
	@GeneratedValue
	@Column(name = "category_id")
	private UUID id;
	private String name;

	@ManyToMany(mappedBy = "categories")
	private List<Blog> blogsList;

	public Category() {
	}

	public Category(String name) {
		this.name = name;
	}

	public List<Blog> getBlogsList() {
		return blogsList;
	}

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Category{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
