package servlet;

import model.Book;
import mysql.MysqlOption;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Trunts.Vitalij, Shaliov.Artiom
 */
/**
 * Сервлет для отображения всех книг
 */
@WebServlet("/tableOfBooks")
public class TableOfBookServlet extends HttpServlet {
    private MysqlOption mysqlOption = MysqlOption.getInstance();
    private static Logger log = Logger.getLogger("TableOfBookServlet");

    /**
     * doGet
     * @param req - request
     * @param resp - response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("doGet for TableOfBookServlet");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        List<Book> books = getBooks();

        req.setAttribute("books", books);
        setReq(req, resp, "/tableOfBooks.jsp");

    }
    /**
     * doPost
     * @param req - request
     * @param resp - response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("doPost for TableOfBookServlet");
        if(req.getParameter("addBook") != null) {
           resp.sendRedirect("/add");
       } else if(req.getParameter("removeBook") != null) {
           resp.sendRedirect("/remove");
       } else if(req.getParameter("editBook") != null) {
           resp.sendRedirect("/edit");
       } else if(req.getParameter("updateBook") != null) {
           doGet(req, resp);
       }
    }

    /**
     * Метод получения всех книг.
     * @return
     */
    private List<Book> getBooks() {
        List<Book> books = mysqlOption.getAllBook();
        return books;
    }

    private void setReq(HttpServletRequest req, HttpServletResponse resp, String s) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        requestDispatcher = req.getRequestDispatcher(s);
        requestDispatcher.forward(req, resp);
    }

}
