package com.imli.mapper;

import com.imli.controller.Request.BooksPageRequest;
import com.imli.pojo.Books;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 员工管理
 */
@Mapper
public interface BookMapper {

    /**
     * 查询总记录数
     *
     * @return
     */
    //@Select("select count(*) from emp")
    //public Long count();
    List<Books> findByCondition(@Param("bookName")String bookName, @Param("bookStyle")String bookStyle);

//    /**
//     * 分页查询,获取列表数据
//     * @param start
//     * @param pageSize
//     * @return
//     */
//    @Select("select * from books limit #{page},#{pageSize}")
//    public List<Books> page(Integer start, Integer pageSize);

    /**
     * 图书信息分页查询
     *
     * @return books
     */
    List<Books> list(BooksPageRequest booksPageRequest);

    /**
     * 批量删除
     *
     * @param id
     *
     */
    void delete(Integer id);

    /**
     * 新增员工
     *
     * @param books
     */
    @Insert("insert into books(bookName, author, bookStyle, bookPrice) " +
            " values(#{bookName},#{author},#{bookStyle},#{bookPrice})")
    void insert(Books books);

    /**
     * 根据ID查询员工
     *
     * @param id
     * @return
     */
    @Select("select *  from books where  id = #{id}")
    Books getById(Integer id);

    /**
     * 更新员工
     *
     * @param books
     */
    void update(Books books);

    Books getByBookName(String bookName);
}

