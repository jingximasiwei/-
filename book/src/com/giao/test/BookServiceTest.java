package com.giao.test;

import com.giao.pojo.Book;
import com.giao.pojo.Page;
import com.giao.service.BookService;
import com.giao.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BookServiceTest {

    private BookService bookService=new BookServiceImpl();
    @Test
    public void addBook() {
        bookService.addBook(new Book(null,"giao哥天下无敌","giao哥",new BigDecimal(1000),111,11,null));
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(22);
        bookService.deleteBookById(23);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(22,"giao哥天下无敌","giao哥",new BigDecimal(1000),122,0,null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(1));
    }

    @Test
    public void queryBooks() {
        for (Book queryBook : bookService.queryBooks()) {
            System.out.println(queryBook);
        }
    }
    @Test
    public void page(){
        System.out.println(bookService.page(1, Page.PAGE_SIZE));
    }
    @Test
    public void pageByPirce(){
        System.out.println(bookService.pageByPrice(0, Page.PAGE_SIZE,10,50));
    }
}