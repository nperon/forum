package fr.acceis.forum.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import fr.acceis.forum.beans.Post;
import fr.acceis.forum.beans.Utilisateur;
import fr.acceis.forum.hibernate.TestDatabase;

public class Reply extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idTopic = req.getParameter( "idTopic" );
		String content = req.getParameter( "content" );		
		HttpSession session = req.getSession();
		/*Enumeration<String> keys = session.getAttributeNames();
		while (keys.hasMoreElements()){
		  String key = (String)keys.nextElement();
		  System.out.println(key + ": " + session.getAttribute(key) );}*/
		Utilisateur utilisateur = (Utilisateur)session.getAttribute("user");
		Post post = new Post();
		post.setAuthor(utilisateur);
		post.setContent(content);
		Date now = GregorianCalendar.getInstance().getTime();
		post.setDate(now);
		post.setTopic( TestDatabase.findTopicById( Long.parseLong( idTopic ) ) );
		TestDatabase.createPost( post );
		req.getRequestDispatcher("home").forward(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idTopic = req.getParameter("idTopic");
		System.out.println("trace a " + idTopic);
		req.getRequestDispatcher("/WEB-INF/jsp/reply.jsp").forward(req, resp);
	}
}