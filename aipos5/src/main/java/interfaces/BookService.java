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
 * ���������, ������������ ������.
 */
@WebService
@SOAPBinding(style =  SOAPBinding.Style.RPC)
public interface BookService {

    /**
     * ����� ��� ��������� ���� ����.
     */
    @WebMethod
    List<Book> getAllBook();

    /**
     * ����� ��������� ����� �� id
     * @param id ����� �����
     */
    Book getBookById(int id);

    /**
     * ����� ��� ���������� �����
     * @param book �����, ������� ���������� ��������.
     */
    @WebMethod
    public void addBook(Book book);


    /**
     * ����� ��� �������� �����
     * @param book �����, ������� ���������� �������.
     */
    @WebMethod
    public void deleteBook(Book book);

    /**
     * ����� ��� ��������� �����
     * @param book �����, �� ������� ���������� �������� ������������ �����.
     */
    @WebMethod
    public void updateBook(Book book);
}
