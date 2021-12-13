package controller.promotype;

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
import model.TipoPromocion;
import services.AttractionService;
import services.AttractionTypeService;
import services.PromoTypeService;

@WebServlet("/tipopromociones/index.do")
public class ListPromoTypeServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 8404470458694889593L;
	private PromoTypeService promoTypeService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.promoTypeService = new PromoTypeService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<TipoPromocion> tiposPromocion = promoTypeService.list();
		req.setAttribute("tipospromocion", tiposPromocion);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/promociones/index.jsp");
		dispatcher.forward(req, resp);

	}

}
