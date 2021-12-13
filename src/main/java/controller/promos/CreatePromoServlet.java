package controller.promos;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Atraccion;
import model.Producto;
import model.Promocion;
import model.TipoAtraccion;
import model.TipoPromocion;
import services.AttractionService;
import services.AttractionTypeService;
import services.PromoService;
import services.PromoTypeService;

@WebServlet("/promociones/crear.do")
public class CreatePromoServlet extends HttpServlet {

	private static final long serialVersionUID = -8235978784126954776L;
	private PromoService promoService;
	private AttractionService attractionService;
	private AttractionTypeService attractionTypeService;
	private PromoTypeService promoTypeService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.promoService = new PromoService();
		this.attractionService = new AttractionService();
		this.attractionTypeService = new AttractionTypeService();
		this.promoTypeService = new PromoTypeService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<TipoAtraccion> tiposAtraccion = attractionTypeService.list();
		req.setAttribute("tiposatraccion", tiposAtraccion);
		List<TipoPromocion> tiposPromocion = promoTypeService.list();
		req.setAttribute("tipospromocion", tiposPromocion);
		List<Producto> atracciones = attractionService.list();
		req.setAttribute("atracciones", atracciones);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/promociones/crear.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nombre = req.getParameter("nombre");
		TipoAtraccion tipoAtraccion = TipoAtraccion.valueOf(req.getParameter("tipoatraccion"));
		TipoPromocion tipoPromocion = TipoPromocion.valueOf(req.getParameter("tipopromocion"));
		String[] nombreAtracciones = req.getParameterValues("nombreatraccion");
		Double descuento = Double.parseDouble(req.getParameter("descuento"));
		Boolean activo = Boolean.parseBoolean(req.getParameter("activo"));
		
		Promocion promocion = null;
		
		if(tipoPromocion == TipoPromocion.PORCENTUAL) {
			promocion = promoService.createPromoPorcentual(nombre, nombreAtracciones, tipoAtraccion, tipoPromocion, descuento, activo);
		} else if (tipoPromocion == TipoPromocion.ABSOLUTA) {
			promocion = promoService.createPromoAbsoluta(nombre, nombreAtracciones, tipoAtraccion, tipoPromocion, descuento, activo);
		} else {
			promocion = promoService.createPromoAxB(nombre, nombreAtracciones, tipoAtraccion, tipoPromocion, activo);
		}
		
		if (promocion.esPromoValida(tipoPromocion)) {
			resp.sendRedirect("/turismo/productos/index.do");
		} else {
			req.setAttribute("promocion", promocion);

			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/views/promociones/crear.jsp");
			dispatcher.forward(req, resp);
		}

	}

}
