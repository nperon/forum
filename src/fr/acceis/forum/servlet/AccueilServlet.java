package fr.acceis.forum.servlet;

import java.io.IOException;

import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.acceis.forum.beans.Post;
import fr.acceis.forum.beans.Topic;
import fr.acceis.forum.hibernate.TestDatabase;

public class AccueilServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Topic> topics = TestDatabase.findAllTopics();
		req.setAttribute("topics",topics);
		req.getRequestDispatcher("/WEB-INF/jsp/threads.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}