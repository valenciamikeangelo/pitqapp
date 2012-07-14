package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class Visits extends Model {

	@Id
	@GeneratedValue
	public Long id;
	public Long currentVisitCount;
	
	
	 public static long getVisitCount(){
		  long currentVisitCount =1;
		  Visits currentVisit= null;
		    List<Visits> visits = new Model.Finder(Long.class, Visits.class).all();
		    if(visits.size()>0){
		    	currentVisit= visits.get(0);
		    	currentVisitCount= currentVisit.currentVisitCount +1;
		    	
		    }else{
		    	currentVisit= new Visits();
		    	currentVisit.currentVisitCount=new Long(0);
		    }
		    currentVisit.currentVisitCount=currentVisitCount;
	    	currentVisit.save();
	    	return currentVisitCount;
	  }
}
