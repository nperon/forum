package fr.acceis.forum.beans;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Topic")
public class Topic {
	
	@Id
	@GeneratedValue
	private long idTopic;
	
	private String title;
	
	@OneToMany(mappedBy="topic")
	private List<Post> posts;

	public long getIdTopic() {
		return idTopic;
	}

	public void setIdTopic(long idTopic) {
		this.idTopic = idTopic;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
}
