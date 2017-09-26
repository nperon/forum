package fr.acceis.forum.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.acceis.forum.beans.Topic;
import fr.acceis.forum.hibernate.TestDatabase;

public class Thread  extends HttpServlet{

	@Override
	public void init(){
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idTopic = req.getParameter("id");
		System.out.println("idTopic: "+idTopic);
		long idTopicLong = Long.parseLong(idTopic);
		Topic topic = TestDatabase.findTopicById(idTopicLong);
		System.out.println(topic.getTitle());
		req.setAttribute("topic", topic);
		req.getRequestDispatcher("/WEB-INF/jsp/thread.jsp").forward(req, resp);
	}
}