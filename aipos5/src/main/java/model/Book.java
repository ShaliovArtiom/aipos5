package model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Trunts.Vitalij, Shaliov.Artiom
 */

/**
 * Модель, описывающая книгу
 */
@Getter
@Setter
public class Book {
    /**
     * номер книги
     */
    private int id;
    /**
     * количество страниц в книге
     */
    private int pageValue;
    /**
     * название книги
     */
    private String bookName;
    /**
     * имя автора
     */
    private String authorName;


    /**
     * Конструктор новой книги
     */
    public Book() {
    }


}
