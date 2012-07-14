package controllers;

import java.util.Map;

import models.Comment;
import models.PageDetails;
import models.Post;
import models.PostDetails;
import play.data.Form;
import play.mvc.Result;
import views.html.createPost;
import views.html.viewPost;

public class PostController extends Application {
	
	public static Result displayCreatePost() {
		PageDetails pageDetails =  getPageDetails();
		return ok(createPost.render(pageDetails));
	  }
	
	public static Result addCommentToPost(Long id) {
		PageDetails pageDetails =  getPageDetails();
		Post postToView = Post.find.byId(id);
		Form<Comment> commentform = form(Comment.class).bindFromRequest();
		String userid = pageDetails.currentUserDetails.user.email; 
		postToView.addComment(userid, commentform.data().get("comment"));
		pageDetails.currentPostDetails=buildPostDetails(id);
		return ok(viewPost.render(pageDetails));
	  }
	
	public static Result submitPost(){
		Form<Post> postform = form(Post.class).bindFromRequest();
		Post post =buildPost(postform);
		post.save();
		return redirect(routes.Application.index());
	}
	
	public static Result displayPost(Long id) {
		PageDetails pageDetails =  getPageDetails();
		pageDetails.currentPostDetails=buildPostDetails(id);
		return ok(viewPost.render(pageDetails));
	  }
	
	private static PostDetails buildPostDetails(Long id){
		Post postToView = Post.find.byId(id);
		PostDetails postdetails = new PostDetails();
		postdetails.currentPostView=postToView;
		return postdetails;
	}
	
	private static Post buildPost(Form<Post> form){
		Map<String,String> values= form.data();
		String userid = session("useremail"); 
		Post post= new Post(userid,values.get("title"),values.get("content"));
		return post;
	}
	
	
}
