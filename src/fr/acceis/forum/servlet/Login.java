package fr.acceis.forum.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.acceis.forum.beans.User;
import fr.acceis.forum.beans.Utilisateur;
import fr.acceis.forum.dao.UserDAO;
import fr.acceis.forum.hibernate.TestDatabase;

public class Login extends HttpServlet{
	
	@Override
	public void init(){
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String login = req.getParameter("username");
		String password = req.getParameter("password");
		String uriToRemind = req.getParameter("uriToRemind");
		Utilisateur found = TestDatabase.findUtilisateurByLogin(login);
		if ( !(found == null) ) {
			Boolean authentified = found.getPassword().equals(password);
			session.setAttribute("authentified",authentified);
			if ( authentified ) {
				session = req.getSession();
				session.setAttribute("user",found);
				resp.sendRedirect(uriToRemind);
			} else {
				HttpServletResponse httpResponse = (HttpServletResponse) resp;
				httpResponse.sendRedirect("login?uriToRemind="+uriToRemind);
			}			
		} else {
			HttpServletResponse httpResponse = (HttpServletResponse) resp;
			httpResponse.sendRedirect("login?uriToRemind="+uriToRemind);
		}
	}
}