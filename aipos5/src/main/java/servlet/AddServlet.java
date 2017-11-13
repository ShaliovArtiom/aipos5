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
 * Сервлет для добавление новой книги
 */
@WebServlet("/add")
public class AddServlet extends HttpServlet {
    private static Logger log = Logger.getLogger("AddServlet");
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
        log.info("doGet for AddServlet");
        resp.setContentType("text/html");
        req.setCharacterEncoding("UTF-8");
        setReq(req, resp, "/add.jsp");
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
        req.setCharacterEncoding("UTF-8");
        log.info("doPost for AddServlet");
        if(req.getParameter("ok") != null) {
            String bookName = (String) req.getParameter("bookName");
            String authorName = (String) req.getParameter("authorName");
            int pageValue = Integer.parseInt(req.getParameter("pageValue"));
            if(!bookName.equals("") && !authorName.equals("")) {
                addBook(bookName, authorName, pageValue);
                resp.sendRedirect("/tableOfBooks");
            } else {
                doGet(req, resp);
            }
        } else if(req.getParameter("cancel") != null) {
            resp.sendRedirect("/tableOfBooks");
        }
    }

    /**
     * Метода добавления книги
     * @param bookName
     * @param authorName
     * @param pageValue
     */
    private void addBook(String bookName, String authorName, int pageValue) {
        Book book = new Book();
        book.setBookName(bookName);
        book.setAuthorName(authorName);
        book.setPageValue(pageValue);
        mysqlOption.addBook(book);
    }

    private void setReq(HttpServletRequest req, HttpServletResponse resp, String s) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        requestDispatcher = req.getRequestDispatcher(s);
        requestDispatcher.forward(req, resp);
    }
}
