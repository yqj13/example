package com.imli.controller;

import com.imli.controller.Request.BooksPageRequest;
import com.imli.pojo.Books;
import com.imli.pojo.PageBean;
import com.imli.pojo.Result;
import com.imli.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 员工管理Controller
 */
@Slf4j
@RestController
@CrossOrigin("http://localhost:8081")
@RequestMapping("/api")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public Result page(BooksPageRequest booksPageRequest){
//        log.info("分页查询, 参数: {},{},{},{},{},{}",books.page,pageSize,bookName,author,bookStyle,bookPrice);
//        System.out.println(bookName+author+bookStyle+bookPrice);
        //调用service分页查询
        PageBean pageBean = bookService.page(booksPageRequest);
        return Result.success(pageBean);
    }

    @PutMapping("/remove")
    public Result delete(@RequestBody List<Books> booksList){
        for (Books books : booksList){
            bookService.delete(books.getId());
        }
        return Result.success();
    }

    @PostMapping()
    public Result save(@RequestBody Books books){
        log.info("新增图书, book: {}",books);
        bookService.save(books);
        return Result.success();
    }

    @GetMapping("/{bookName}")
    public Result getByBookName(@PathVariable String bookName){
//        log.info("根据ID查询图书信息, id: {}",id);
        Books books = bookService.getByBookName(bookName);
        return Result.success(books);
    }

    @PutMapping()
    public Result update(@RequestBody Books books){
        log.info("更新图书信息 : {}", books);
        bookService.update(books);
        return Result.success();
    }
}





