package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class User extends Model {

	@Id
	public String email;
    public String password;
    public String fullname;
    public boolean isAdmin;
	
    public User(){
    	
    }
    
    public User(String email, String password, String fullname, boolean isAdmin) {
		super();
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.isAdmin = isAdmin;
	}

    
    public static long getMembers() {
		List<User> members =find.all();
		return  members.size();
	}

    public static User retrieveAccountByUsernameAndPassword(String email, String password) {
		String oql = "email = :email and password= :password";
    	List<User> members = find.query()
    			.select("*")
    			.where(oql)
    			.setParameter("email", email)
    			.setParameter("password",password)
    			.findList();
		if(members.size()>0){
			return members.get(0);
		}
			
		return null;
	}
    
	public static Finder<String,User> find = new Finder<String,User>(
    		String.class, User.class
    	  ); 
}
