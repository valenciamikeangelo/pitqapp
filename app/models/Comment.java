package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import play.db.ebean.Model;

@Entity
public class Comment extends Model {
 
	@Id
	@GeneratedValue
	public Long commentId;
	
    public String author;
    public Date postedAt;
     
  
    @Column(name="CONTENT", length=1024)
    public String content;
    
    @ManyToOne
    public Post post;
    
    public Comment(Post post, String author, String content) {
        this.post = post;
        this.author = author;
        this.content = content;
        this.postedAt = new Date();
    }
 
    
    public static Finder<Long,Comment> find = new Finder<Long,Comment>(
    		Long.class, Comment.class
    	  ); 
}
