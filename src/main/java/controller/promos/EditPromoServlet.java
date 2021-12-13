package controller.promos;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Atraccion;
import model.Promocion;
import services.AttractionService;
import services.PromoService;

@WebServlet("/promociones/editar.do")
public class EditPromoServlet extends HttpServlet {

	private static final long serialVersionUID = -805198761489452906L;
	private PromoService promoService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.promoService = new PromoService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nombre = req.getParameter("nombre");

		Promocion promocion = promoService.findByName(nombre);
		req.setAttribute("promocion", promocion);

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

		Atraccion atraccion = promoService.update(nombre, costo, tiempo, cupo, activo);

		if (atraccion.esValida()) {
			resp.sendRedirect("/turismo/productos/index.do");
		} else {
			req.setAttribute("promocion", promocion);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/promociones/editar.jsp");
			dispatcher.forward(req, resp);
		}
	}
}
