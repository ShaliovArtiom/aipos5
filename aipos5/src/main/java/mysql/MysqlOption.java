package mysql;

import model.Book;
import org.apache.log4j.Logger;
import interfaces.BookService;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * �����, ����������� ������� MYSQL
 *
 * @author ShaliovArtiom, TruntsVitalij
 */
public class MysqlOption implements BookService {

    private String sqlForAdd;
    private String sqlForRemove;
    private String sqlForGetAll;
    private String sqlForRename;

    /**
     * ����, ��������������� ��� �������� ������������� ���������� ������
     */
    public static MysqlOption instance = null;
    /**
     * ���� ��� ������� ����
     */
    private static final Logger log = Logger.getLogger(MysqlOption.class);
    /**
     * ���� ��� ������� MYSQL
     */
    /**
     * ���� ���������� ����� ������� MYSQL
     */
    private ResultSet resultSet;
    /**
     * ���� ��� ����������� � ������� � MYSQL
     */
    private DBWorker worker;
    /**
     * �����������, ���������� ������� readBookTable
     */
    private MysqlOption() {
        worker = DBWorker.getInstance();
    }
    /**
     * ����� �������� �������������� ���� ������
     *
     * @return ����������� �������� instance
     */
    public static MysqlOption getInstance() {
        if (instance == null) {
            instance = new MysqlOption();
        }
        return instance;
    }

    /**
     * ����� ��������� ���� ����� �� ������� MYSQL
     *
     * @return ���������� ���� ����
     */
    @Override
    public List<Book> getAllBook() {
        List<Book> bookList = new ArrayList<Book>();
        try {
            worker.openConnection();
            Statement statement = worker.getConnection().createStatement();
            resultSet = statement.executeQuery(ConstForSQL.SELECT_ALL_FROM_BOOK);
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt(1));
                book.setBookName(resultSet.getString(2));
                book.setAuthorName(resultSet.getString(3));
                book.setPageValue(resultSet.getInt(4));
                bookList.add(book);
            }
            setSqlForGetAll(ConstForSQL.SELECT_ALL_FROM_BOOK);
            statement.close();
            worker.closeConnection();
            log.info("Get all Books");
        } catch (SQLException e) {
            log.error("Could not send request to the DB");
        }
        return bookList;
    }

    /**
     * ��������� ����� �� id
     * @param id - ����� �����
     * @return ���������� ��������
     */
    @Override
    public Book getBookById(int id) {
        List<Book> bookList = getAllBook();
        for (Book book : bookList) {
            if (book.getId() == id) {
                log.info("get book whose id = " + id);
                return book;
            }
        }
        return null;
    }

    /**
     * ����� ��� ���������� ����� � ����� Storage � � Mysql
     *
     * @param book �����, ������� ���������� ��������.
     */
    @Override
    public void addBook(Book book) {
        PreparedStatement preparedStatement = null;
        try {
            worker.openConnection();
            preparedStatement = worker.getConnection().prepareStatement(ConstForSQL.INSERT_NEW_BOOK);
            preparedStatement.setString(1, book.getBookName());
            preparedStatement.setString(2, book.getAuthorName());
            preparedStatement.setInt(3, book.getPageValue());

            preparedStatement.execute();
            log.info("add book");
            String sql = preparedStatement.toString();
            setSqlForAdd(sql.substring(sql.indexOf(": ") + 2));
        } catch (SQLException e1) {
            e1.printStackTrace();
            log.error("error");
        }
        worker.closeConnection();
    }

    /**
     * ����� ������ ����� �� ������ Storage � Mysql
     *
     * @param book �����, �� ������� ���������� ��������.
     */
    public void updateBook(Book book) {
        PreparedStatement preparedStatement = null;
        try {
            worker.openConnection();
            preparedStatement = worker.getConnection().prepareStatement(ConstForSQL.RENAME_BOOK);
            preparedStatement.setString(1, book.getBookName());
            preparedStatement.setString(2, book.getAuthorName());
            preparedStatement.setInt(3, book.getPageValue());
            preparedStatement.setInt(4, book.getId());
            preparedStatement.execute();
            log.info("Chanched the book");
            String sql = preparedStatement.toString();
            setSqlForRename(sql.substring(sql.indexOf(": ") + 2));
        } catch (SQLException e1) {
            e1.printStackTrace();
            log.error("error");
        }
        worker.closeConnection();
    }

    /**
     * ����� �������� ����� �� ������ Storage � Mysql
     *
     * @param book �����, ������� ���������� �������.
     */
    @Override
    public void deleteBook(Book book) {
        PreparedStatement preparedStatement = null;
        try {
            worker.openConnection();
            preparedStatement = worker.getConnection().prepareStatement(ConstForSQL.DELETE_FROM_BOOK);
            preparedStatement.setInt(1, book.getId());
            preparedStatement.setString(2, book.getBookName());
            preparedStatement.setString(3, book.getAuthorName());
            preparedStatement.setInt(4, book.getPageValue());

            preparedStatement.execute();
            String sql = preparedStatement.toString();
            setSqlForRemove(sql.substring(sql.indexOf(": ") + 2));
            log.info("Remove the book");
        } catch (SQLException e1) {
            e1.printStackTrace();
            log.error("error");
        }
        worker.closeConnection();
    }

    /**
     * ����� �������� ����� �� ������ Storage � Mysql
     *
     * @param id id �����, ������� ���������� �������.
     */
    public void deleteBookById(int id) {
        PreparedStatement preparedStatement = null;
        try {
            worker.openConnection();
            preparedStatement = worker.getConnection().prepareStatement(ConstForSQL.DELETE_FROM_BOOKID);
            preparedStatement.setInt(1, id);

            preparedStatement.execute();
            String sql = preparedStatement.toString();
            setSqlForRemove(sql.substring(sql.indexOf(": ") + 2));
            log.info("Remove the book");
        } catch (SQLException e1) {
            e1.printStackTrace();
            log.error("error");
        }
        worker.closeConnection();
    }
    public String getSqlForAdd() {
        return sqlForAdd;
    }

    public void setSqlForAdd(String sqlForAdd) {
        this.sqlForAdd = sqlForAdd;
    }

    public String getSqlForRemove() {
        return sqlForRemove;
    }

    public void setSqlForRemove(String sqlForRemove) {
        this.sqlForRemove = sqlForRemove;
    }

    public String getSqlForGetAll() {
        return sqlForGetAll;
    }

    public void setSqlForGetAll(String sqlForGetAll) {
        this.sqlForGetAll = sqlForGetAll;
    }

    public String getSqlForRename() {
        return sqlForRename;
    }

    public void setSqlForRename(String sqlForRename) {
        this.sqlForRename = sqlForRename;
    }
}

