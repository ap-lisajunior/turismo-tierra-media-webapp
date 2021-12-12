package controller.products;

import java.io.IOException;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;
import persistence.commons.DAOFactory;
import services.BuyProductService;

@WebServlet("/productos/comprar.do")
public class BuyProductServlet extends HttpServlet {

	private static final long serialVersionUID = 3455721046062278592L;
	private BuyProductService buyProductService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.buyProductService = new BuyProductService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String nombreProducto = req.getParameter("nombre");
		Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
		Map<String, String> errores = buyProductService.buy(usuario.getNombre(), nombreProducto);
		
		Usuario usuario2 = DAOFactory.getUsuarioDAO().findByUsername(usuario.getNombre());
		req.getSession().setAttribute("usuario", usuario2);
		
		if (errores.isEmpty()) {
			req.setAttribute("flash", "¡Gracias por comprar!");
		} else {
			req.setAttribute("errores", errores);
			req.setAttribute("flash", "No ha podido realizarse la compra");
		}
		
		
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/productos/index.do");
		dispatcher.forward(req, resp);
		
	}
}
