package models;

import static org.junit.Assert.*;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;

import org.junit.Test;

import play.db.ebean.Model;

public class UserTest {

	User userOnTest;
	
	@Test
	public void testCreateAndFindUser(){
		running(fakeApplication(), new Runnable() {
            public void run() {
            
            	userOnTest = new User("user@test.com", "mypass", "firstname",false);
            	userOnTest.save();
            	
            	User retrieveUser = User.find.byId(userOnTest.email);
            	
            	assertNotNull(userOnTest);
            	assertNotNull(retrieveUser);
            	assertEquals(userOnTest.email, retrieveUser.email);
            	
            }
        });
	}
	
	@Test
	public void testDeleteUserById(){
		running(fakeApplication(), new Runnable() {
            public void run() {
            /*
            	userOnTest.delete();
            	User retrieveUser = User.find.byId(userOnTest.email);
            	assertEquals(retrieveUser,null);
            	*/
            }
        });
	}
	
	
}
