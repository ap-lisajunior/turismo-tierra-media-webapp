package controller.attractiontype;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Producto;
import model.TipoAtraccion;
import services.AttractionService;
import services.AttractionTypeService;

@WebServlet("/tipoatracciones/index.do")
public class ListAttractionTypeServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = -8355766278160481441L;
	private AttractionTypeService attractionTypeService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.attractionTypeService = new AttractionTypeService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<TipoAtraccion> tiposAtracciones = attractionTypeService.list();
		req.setAttribute("tiposatraccion", tiposAtracciones);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/atracciones/index.jsp");
		dispatcher.forward(req, resp);

	}

}
