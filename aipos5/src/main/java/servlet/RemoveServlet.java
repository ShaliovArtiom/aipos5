package servlet;

import model.Book;
import mysql.DBWorker;
import mysql.MysqlOption;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * @author Trunts.Vitalij, Shaliov.Artiom
 */
/**
 * Сервлет для удаления книги
 */
@WebServlet("/remove")
public class RemoveServlet extends HttpServlet {
    private int id;
    private static Logger log = Logger.getLogger("RemoveServlet");
    private MysqlOption mysqlOption = MysqlOption.getInstance();
    /**
     * doGet
     * @param req - request
     * @param resp - response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("doGet for RemoveServlet");
        resp.setContentType("text/html");

        removeBookById(req);
        setReq(req, resp, "/tableOfBooks");
    }

    /**
     * Метод удаления книги.
     * @param req
     */
    private void removeBookById(HttpServletRequest req) {
        log.info("doPost for RemoveServlet");
        mysqlOption.deleteBookById(Integer.parseInt(req.getQueryString()));
    }

    private void setReq(HttpServletRequest req, HttpServletResponse resp, String s) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        requestDispatcher = req.getRequestDispatcher(s);
        requestDispatcher.forward(req, resp);
    }
}
