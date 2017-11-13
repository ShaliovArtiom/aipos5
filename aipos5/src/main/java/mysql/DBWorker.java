package mysql;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * �����, ��������������� ��� ���������� � �������� MYSQL
 * @author ShaliovArtiom, TruntsVitalij
 */
public class DBWorker {
    /**
     * ����, ��������������� ��� �������� ������������� ���������� ������
     */
    private static DBWorker instance = null;
    /**
     * ����, ���������� ������ � ���� ������
     */
    private final static String PASSWORD = "1234asdqwe";
    /**
     * ����, ���������� ��� ���� ������
     */
    private final static String USERNAME = "root";
    /**
     * ����, ���������� ���� � ���� ������
     */
    private final static String URL = "jdbc:mysql://localhost:3306/aipos";
    /**
     * ����, ��������������� ��� ���������� � ����� ������
     */
    private Connection connection;

    /**
     * ����������� �� ���������
     */
    private DBWorker() {
    }

    /**
     * ����� ������������ ���������� � ����� ������
     */
    public void openConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * ����� �������� ����������
     */
    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * ����� �������� �������������� ���� ������
     * @return ����������� �������� instance
     */
    public static DBWorker getInstance() {
        if(instance == null){
            instance = new DBWorker();
        }
        return instance;
    }

    /**
     * ����� ��������� ���������� � ����� ������
     * @return ���������� �������� connection
     */
    public Connection getConnection() {
        return connection;
    }
}

