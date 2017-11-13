package model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Trunts.Vitalij, Shaliov.Artiom
 */

/**
 * ������, ����������� �����
 */
@Getter
@Setter
public class Book {
    /**
     * ����� �����
     */
    private int id;
    /**
     * ���������� ������� � �����
     */
    private int pageValue;
    /**
     * �������� �����
     */
    private String bookName;
    /**
     * ��� ������
     */
    private String authorName;


    /**
     * ����������� ����� �����
     */
    public Book() {
    }


}
