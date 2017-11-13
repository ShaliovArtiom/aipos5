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
 * Класс, реализующий запросы MYSQL
 *
 * @author ShaliovArtiom, TruntsVitalij
 */
public class MysqlOption implements BookService {

    private String sqlForAdd;
    private String sqlForRemove;
    private String sqlForGetAll;
    private String sqlForRename;

    /**
     * поле, предназначенное для создания единственного экземпляра класса
     */
    public static MysqlOption instance = null;
    /**
     * поле для ведения лога
     */
    private static final Logger log = Logger.getLogger(MysqlOption.class);
    /**
     * поле для запроса MYSQL
     */
    /**
     * поле содержащее ответ запроса MYSQL
     */
    private ResultSet resultSet;
    /**
     * поле для подключения к таблице в MYSQL
     */
    private DBWorker worker;
    /**
     * Конструктор, вызывающий функцию readBookTable
     */
    private MysqlOption() {
        worker = DBWorker.getInstance();
    }
    /**
     * Метод проверки единственности базы данных
     *
     * @return возвращение значения instance
     */
    public static MysqlOption getInstance() {
        if (instance == null) {
            instance = new MysqlOption();
        }
        return instance;
    }

    /**
     * Метод получения всех книги из таблицы MYSQL
     *
     * @return возвращает лист книг
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
     * получение книги по id
     * @param id - номер книги
     * @return найденного студента
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
     * Метод для добавление книги в класс Storage и в Mysql
     *
     * @param book книга, которую необходимо добавить.
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
     * Метод замены книги из класса Storage и Mysql
     *
     * @param book книга, на которую необходимо заменить.
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
     * Метод удаления книги из класса Storage и Mysql
     *
     * @param book книга, которую необходимо удалить.
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
     * Метод удаления книги из класса Storage и Mysql
     *
     * @param id id книги, которую необходимо удалить.
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

