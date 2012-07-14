package models;

import static org.junit.Assert.*;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;

import org.junit.Test;

public class PostTest {

	Post post;
	@Test
	public void testCreatePostAndComment() {
		running(fakeApplication(), new Runnable() {
            public void run() {
            	User userOnTest = new User("userpost@test.com", "mypass", "firstname",false);
        		userOnTest.save();
        		post= new Post(userOnTest.email,"testtitle","testcontent");
        		post.save();
        		
        		assertNotNull(post);
        		assertNotNull(post.postId);
        		
        		Post rpost=Post.find.byId(post.postId);
        		assertNotNull(rpost);
        		assertNotNull(rpost.content);
        		assertEquals(post.postId, rpost.postId);
        		assertEquals(post.title, rpost.title);
        		assertEquals(post.author, rpost.author);
        		//assertEquals(post.content, rpost.content);
        	
        		     		
        		post.addComment( "user1@test.com","comment1");
        		assertTrue(post.comments.size()==1);
        		Comment comment1=post.comments.get(0);
        		assertEquals("user1@test.com", comment1.author);
        		assertEquals("comment1", comment1.content);
        		assertNotNull(comment1.content);
        		
        		post.addComment( "user2@test.com","comment2");
        		assertTrue(post.comments.size()==2);
        		Comment comment2=post.comments.get(1);
        		assertEquals("user2@test.com", comment2.author);
        		assertEquals("comment2", comment2.content);
        		assertNotNull(comment2.content);
        		
        	
            }
        });
	}
}
