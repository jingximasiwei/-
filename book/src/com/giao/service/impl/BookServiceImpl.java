package com.giao.service.impl;

import com.giao.dao.BookDao;
import com.giao.dao.impl.BookDaoImpl;
import com.giao.pojo.Book;
import com.giao.pojo.Page;
import com.giao.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {

    private BookDao bookDao=new BookDaoImpl();

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book>page=new Page<Book>();

        //一页有几条数据
        page.setPageSize(pageSize);
        //总记录数
        Integer pageTotalCount=bookDao.queryForPageTotalCount();
        page.setPageTotalCount(pageTotalCount);
        //页数
        Integer pageTotal=pageTotalCount/pageSize;
        if(pageTotalCount%pageSize>0) pageTotal++;
        page.setPageTotal(pageTotal);

        //当前页

        page.setPageNo(pageNo);

        //当前页数据
        int begin=(page.getPageNo()-1)*pageSize;
        List<Book> items=bookDao.queryForPageItems(begin,pageSize);
        page.setItems(items);
        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book>page=new Page<Book>();

        //一页有几条数据
        page.setPageSize(pageSize);
        //总记录数
        Integer pageTotalCount=bookDao.queryForPageTotalCountByPrice(min,max);
        page.setPageTotalCount(pageTotalCount);
        //页数
        Integer pageTotal=pageTotalCount/pageSize;
        if(pageTotalCount%pageSize>0) pageTotal++;
        page.setPageTotal(pageTotal);

        //当前页

        page.setPageNo(pageNo);

        //当前页数据
        int begin=(page.getPageNo()-1)*pageSize;
        List<Book> items=bookDao.queryForPageItemsByPrice(begin,pageSize,min,max);
        page.setItems(items);
        return page;
    }
}
