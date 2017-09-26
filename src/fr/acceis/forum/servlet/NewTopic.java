package fr.acceis.forum.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.acceis.forum.beans.Post;
import fr.acceis.forum.beans.Topic;
import fr.acceis.forum.beans.Utilisateur;
import fr.acceis.forum.hibernate.TestDatabase;

public class NewTopic extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String titre = req.getParameter( "title" );
		String content = req.getParameter( "content" );
		HttpSession session = req.getSession();
		Topic topic = new Topic();
		topic.setTitle(titre);
		topic.setPosts( new ArrayList<Post>() );
		topic = TestDatabase.createTopic( topic );
		Utilisateur utilisateur = (Utilisateur)session.getAttribute("user");
		Post post = new Post();
		post.setAuthor( utilisateur );
		post.setContent( content );
		Date now = GregorianCalendar.getInstance().getTime();
		post.setDate(now);
		post.setTopic( topic );
		post.setAuthor(utilisateur);
		post = TestDatabase.createPost( post );
		req.getRequestDispatcher("home").forward(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/jsp/topic.jsp").forward(req, resp);
	}
}
