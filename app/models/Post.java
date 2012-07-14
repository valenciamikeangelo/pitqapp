package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.ebean.Model;



@Entity
public class Post extends Model {

	@Id
	@GeneratedValue
	public Long postId;

	public String title;
	public Date postedAt;
	
	@OneToMany(mappedBy="post", cascade=CascadeType.ALL)
	public List<Comment> comments;
	
	
	@Column(name="CONTENT", length=1024)
	public String content;

	@ManyToOne
	public String author;

	public Post(String author, String title, String content) {
		this.comments = new ArrayList<Comment>();
		this.author = author;
		this.title = title;
		this.content = content;
		this.postedAt = new Date();
	}

	public Post addComment(String author, String content) {
	    Comment newComment = new Comment(this, author, content);
	    newComment.save();
	    this.comments.add(newComment);
	    this.save();
	    return this;
	}
	
	public static List<Post> getPostByUser(User user){
		String oql = "author = :email";
    	List<Post> posts = find.query()
    			.select("*")
    			.where(oql)
    			.setParameter("email", user.email)
    			.findList();
		return posts;
	}
	
	public static Finder<Long,Post> find = new Finder<Long,Post>(
    		Long.class, Post.class
    	  ); 
	
}
