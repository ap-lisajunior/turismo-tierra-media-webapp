package controller.attractions;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Atraccion;
import model.TipoAtraccion;
import services.AttractionService;
import services.AttractionTypeService;

@WebServlet("/atracciones/crear.do")
public class CreateAttractionServlet extends HttpServlet {

	private static final long serialVersionUID = 3455721046062278592L;
	private AttractionService attractionService;
	private AttractionTypeService attractionTypeService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.attractionService = new AttractionService();
		this.attractionTypeService = new AttractionTypeService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<TipoAtraccion> tiposAtraccion = attractionTypeService.list();
		req.setAttribute("tiposatraccion", tiposAtraccion);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/atracciones/crear.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nombre = req.getParameter("nombre");
		Integer costo = Integer.parseInt(req.getParameter("costo"));
		Double tiempo = Double.parseDouble(req.getParameter("tiempo"));
		Integer cupo = Integer.parseInt(req.getParameter("cupo"));
		TipoAtraccion tipoAtraccion = TipoAtraccion.valueOf(req.getParameter("tipo"));
		Boolean activo = Boolean.parseBoolean(req.getParameter("activo"));

		Atraccion atraccion = attractionService.create(nombre, costo, tiempo, cupo, tipoAtraccion, activo);
		if (atraccion.esValida()) {
			resp.sendRedirect("/turismo/atracciones/index.do");
		} else {
			req.setAttribute("atraccion", atraccion);

			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/views/atracciones/crear.jsp");
			dispatcher.forward(req, resp);
		}

	}

}
