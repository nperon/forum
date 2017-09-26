package fr.acceis.forum.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import fr.acceis.forum.beans.Utilisateur;
import fr.acceis.forum.hibernate.TestDatabase;

public class Inscription extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/jsp/inscription.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String login = req.getParameter("login");
		String email = req.getParameter("email");
		String password1 = req.getParameter("password1");
		String password2 = req.getParameter("password2");
		if ( password1 == null || password2 == null ) {
			this.doGet(req, resp);
			return;
		} else if ( !( password1.equals(password2) ) ) {
			this.doGet(req, resp);
			return;			
		} else {
			Utilisateur found = TestDatabase.findUtilisateurByLogin(login);		
			if ( !(found == null) ){
				this.doGet(req, resp);
			} else {
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setEmail( email );
				utilisateur.setPassword( password1 );
				utilisateur.setLogin( login );
				Utilisateur saved = TestDatabase.createUtilisateur(utilisateur);
				Boolean authentified = true;
				session.setAttribute("authentified",authentified);
				session = req.getSession();
				session.setAttribute("user",saved);
				RequestDispatcher rd = req.getRequestDispatcher("home");
				rd.forward(req, resp);
			}
		}
	}
}