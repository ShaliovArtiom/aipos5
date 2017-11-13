package interfaces;

import model.Book;
import mysql.MysqlOption;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import javax.xml.bind.JAXBElement;
import java.util.List;

/**
 * @author Trunts.Vitalij, Shaliov.Artiom
 */

/**
 * Интерфейс, определяющий методы.
 */
@WebService
@SOAPBinding(style =  SOAPBinding.Style.RPC)
public interface BookService {

    /**
     * Метод для получения всех книг.
     */
    @WebMethod
    List<Book> getAllBook();

    /**
     * Метод получения книги по id
     * @param id номер книги
     */
    Book getBookById(int id);

    /**
     * Метод для добавления книги
     * @param book книга, которую необходимо дабавить.
     */
    @WebMethod
    public void addBook(Book book);


    /**
     * Метод для удаления книги
     * @param book книга, которую необходимо удалить.
     */
    @WebMethod
    public void deleteBook(Book book);

    /**
     * Метод для изменения книги
     * @param book книга, на которую необходимо заменить существующую книгу.
     */
    @WebMethod
    public void updateBook(Book book);
}
