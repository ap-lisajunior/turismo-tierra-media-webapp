package controller.products;

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
import model.Usuario;
import services.AttractionService;
import services.ProductService;

@WebServlet("/productos/index.do")
public class ListProductsServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 7134460597485822777L;
	private ProductService productService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.productService = new ProductService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Producto> productos = productService.listProductos((Usuario) req.getSession().getAttribute("usuario"));
		req.setAttribute("productos", productos);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/productos/index.jsp");
		dispatcher.forward(req, resp);

	}

}
