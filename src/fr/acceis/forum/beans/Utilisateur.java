package fr.acceis.forum.beans;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Utilisateur")
public class Utilisateur{
		
	@Id
	@GeneratedValue
	private long id;
	
	private String email;
	
	private Date inscription;
	
	private String login;
	
	private String password;
	
	@OneToMany
	public List<Post> posts;

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getInscription() {
		return inscription;
	}

	public void setInscription(Date inscription) {
		this.inscription = inscription;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
}
