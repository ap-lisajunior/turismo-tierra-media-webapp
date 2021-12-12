package controller.attractions;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Atraccion;
import services.AttractionService;

@WebServlet("/atracciones/editar.do")
public class EditAttractionServlet extends HttpServlet {

	private static final long serialVersionUID = 7598291131560345626L;
	private AttractionService attractionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.attractionService = new AttractionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nombre = req.getParameter("nombre");

		Atraccion atraccion = attractionService.findByName(nombre);
		req.setAttribute("atraccion", atraccion);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/atracciones/editar.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nombre = req.getParameter("nombre");
		Integer costo = req.getParameter("costo").trim() == "" ? null : Integer.parseInt(req.getParameter("costo"));
		Double tiempo = Double.parseDouble(req.getParameter("tiempo"));
		Integer cupo = Integer.parseInt(req.getParameter("cupo"));
		Boolean activo = Boolean.parseBoolean(req.getParameter("activo"));

		Atraccion atraccion = attractionService.update(nombre, costo, tiempo, cupo, activo);

		if (atraccion.esValida()) {
			resp.sendRedirect("/turismo/atracciones/index.do");
		} else {
			req.setAttribute("atraccion", atraccion);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/atracciones/editar.jsp");
			dispatcher.forward(req, resp);
		}
	}
}
