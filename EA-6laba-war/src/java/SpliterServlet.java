
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import stateful.SpliterBeanRemote;

@WebServlet(name = "split", urlPatterns = {"/split"})
public class SpliterServlet extends HttpServlet {

    @EJB
    SpliterBeanRemote spliter = lookupSpliterBeanRemote();

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
        String string = request.getParameter("word");
        if (string != null && string.length() > 0) {
            string = spliter.split(string);
            request.setAttribute("context2", string);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    private SpliterBeanRemote lookupSpliterBeanRemote() {
        try {
            Context c = new InitialContext();
            return (SpliterBeanRemote) c.lookup("java:global/EA-6laba/EA-6laba-ejb/SpliterBean!stateful.SpliterBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
