package controller.products;

import java.io.IOException;
import java.util.LinkedList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Itinerario;
import model.Producto;
import model.Usuario;
import services.ItineraryService;
import services.ProductService;

@WebServlet("/productos/index.do")
public class ListProductsServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = -8884866454546938842L;
	private ProductService productService;
	private ItineraryService itineraryService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.productService = new ProductService();
		this.itineraryService = new ItineraryService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LinkedList<Producto> productos = productService.listProductos((Usuario) req.getSession().getAttribute("usuario"));
		Itinerario itinerario = itineraryService.findByUsuario((Usuario) req.getSession().getAttribute("usuario"), productos);
		req.setAttribute("productos", productos);
		req.setAttribute("itinerario", itinerario);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/productos/index.jsp");
		dispatcher.forward(req, resp);

	}

}
