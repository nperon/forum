package fr.acceis.forum.beans;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Post")
public class Post {
	
	@Id
	@GeneratedValue
	private long idPost;
	
	@ManyToOne
	private Topic topic;
	
	private String content;
	
	private Date date;
	
	@ManyToOne
	private Utilisateur author;

	public long getIdPost() {
		return idPost;
	}

	public void setIdPost(long idPost) {
		this.idPost = idPost;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Utilisateur getAuthor() {
		return author;
	}

	public void setAuthor(Utilisateur author) {
		this.author = author;
	}
}
