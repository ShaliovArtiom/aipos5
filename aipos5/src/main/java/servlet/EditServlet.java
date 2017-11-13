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
 * Сервлет для редактирования книги
 */
@WebServlet("/edit")
public class EditServlet extends HttpServlet {
    private int id;
    private static Logger log = Logger.getLogger("EditServlet");
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
        log.info("doGet for EditServlet");
        resp.setContentType("text/html");
        req.setCharacterEncoding("UTF-8");
        id = Integer.parseInt(req.getQueryString());
        findBookById(req);
        setReq(req, resp, "/edit.jsp");
    }

    private void findBookById(HttpServletRequest req) {
        //DBWorker.getInstance().openConnection();

        Book book = mysqlOption.getBookById(Integer.parseInt(req.getQueryString()));
      //  DBWorker.getInstance().closeConnection();
        id = book.getId();

        req.setAttribute("bookNameEdit", book.getBookName());
        req.setAttribute("authorNameEdit", book.getAuthorName());
        req.setAttribute("pageValueEdit", book.getPageValue());
    }

    /**
     * doGet
     * @param req - request
     * @param resp - response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("doPost for EditServlet");
        req.setCharacterEncoding("UTF-8");
        if(req.getParameter("ok") != null) {
            renameBookById(req, resp);
            resp.sendRedirect("/tableOfBooks");
        } else if(req.getParameter("cancel") != null) {
            resp.sendRedirect("/tableOfBooks");
        }
    }

    /**
     * Метод замены книги.
     * @param req
     * @param resp
     * @throws IOException
     * @throws ServletException
     */
    private void renameBookById(HttpServletRequest req,  HttpServletResponse resp) throws IOException, ServletException {

        String bookName = req.getParameter("bookNameEdit");
        String authorName = req.getParameter("authorNameEdit");
        Integer pageValue = Integer.parseInt(req.getParameter("pageValueEdit"));

        if(!bookName.equals("") && !authorName.equals("") && pageValue != 0) {
            Book book = new Book();
            book.setId(id);
            book.setAuthorName(authorName);
            book.setBookName(bookName);
            book.setPageValue(pageValue);
            mysqlOption.updateBook(book);
        } else {
            doGet(req, resp);
        }
    }

    private void setReq(HttpServletRequest req, HttpServletResponse resp, String s) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        requestDispatcher = req.getRequestDispatcher(s);
        requestDispatcher.forward(req, resp);
    }
}
