import java.io.IOException;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import stateless.InverterBeanLocal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "invert", urlPatterns = {"/invert"})
public class InverterServlet extends HttpServlet {

    @EJB
    InverterBeanLocal inverter;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String string = request.getParameter("string");
        if (string != null && string.length() > 0) {
            string =inverter.invert(string);
            request.setAttribute("context1", string);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

}

