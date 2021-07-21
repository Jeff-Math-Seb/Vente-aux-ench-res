package fr.eni.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.beans.ObjetUsers;
import fr.eni.services.UsersService;
//CONTROLEUR
@WebServlet("/CreerCompte")
public class AjouterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/CreerCompte.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjetUsers newUser = Validation.validerSaisie(request);
		if(newUser!=null)
		{
			UsersService vs = new UsersService();
			try {
				vs.ajouterUser(newUser);
				response.sendRedirect(request.getContextPath()+"/ListerServlet");	
			} catch (Exception e) {
				request.setAttribute("erreur", e.getMessage());
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/erreur.jsp");
				rd.forward(request, response);
			}
			
		}
		else
		{
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/ListerServlet.jsp");
			rd.forward(request, response);
		}

	}

}
