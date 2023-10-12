package com.imli.service;

import com.imli.controller.Request.BooksPageRequest;
import com.imli.pojo.Books;
import com.imli.pojo.PageBean;

import java.util.List;

/**
 * 员工管理
 */
public interface BookService {
    List<Books> findByCondition(String bookName, String author);
    /**
     * 分页查询
     * @param booksPageRequest
     * @return
     */
    PageBean page(BooksPageRequest booksPageRequest);

    /**
     * 批量删除
     * @param id
     */
    void delete(Integer id);

    /**
     * 新增员工
     * @param books
     */
    void save(Books books);

    /**
     * 根据ID查询员工
     * @param id
     * @return
     */
    Books getById(Integer id);

    /**
     * 更新员工
     * @param books
     */
    void update(Books books);

    Books getByBookName(String bookName);
}
