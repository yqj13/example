package com.imli.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.imli.controller.Request.BooksPageRequest;
import com.imli.mapper.BookMapper;
import com.imli.pojo.Books;
import com.imli.pojo.PageBean;
import com.imli.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;
    @Override
    public List<Books> findByCondition(String bookName, String author) {
        return bookMapper.findByCondition(bookName, author);
    }

    /*@Override
    public PageBean page(Integer page, Integer pageSize) {
        //1. 获取总记录数
        Long count = empMapper.count();

        //2. 获取分页查询结果列表
        Integer start = (page - 1) * pageSize;
        List<Emp> empList = empMapper.page(start, pageSize);

        //3. 封装PageBean对象
        PageBean pageBean = new PageBean(count, empList);
        return pageBean;
    }*/


    @Override
    public PageBean page(BooksPageRequest booksPageRequest) {
        //1. 设置分页参数
        PageHelper.startPage(booksPageRequest.getPage(), booksPageRequest.getPageSize());
        //2. 执行查询
        List<Books> booksList = bookMapper.findByCondition(booksPageRequest.getBookName(),booksPageRequest.getBookStyle());
        Page<Books> p = (Page<Books>) booksList;

        //3. 封装PageBean对象
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        return pageBean;
    }

    @Override
    public void delete(Integer id) {
        bookMapper.delete(id);
    }

    @Override
    public void save(Books books) {
        bookMapper.insert(books);
    }

    @Override
    public Books getById(Integer id) {
        return bookMapper.getById(id);
    }

    @Override
    public void update(Books books) {
        bookMapper.update(books);
    }

    @Override
    public Books getByBookName(String bookName) {
      return  bookMapper.getByBookName(bookName);
    }
}
