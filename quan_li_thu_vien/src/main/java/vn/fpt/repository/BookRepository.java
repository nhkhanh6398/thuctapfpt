package vn.fpt.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.fpt.model.Book;

import java.util.List;


public interface BookRepository extends JpaRepository<Book,Integer> {
    Page<Book> findAllByNameBookContaining(String name, Pageable pageable);

//    @Query("select b  from Book b where b.author.name = :id")
    List<Book> findByAuthorNameContaining(String name);
    @Query("select b  from Book b where b.catagory.nameCatagory = :id")
    List<Book> findAllByIdCatagory(@Param("id") String name);
//    @Query("select b from Book b where b.nameBook = :name")
    List<Book> findByNameBookContaining(String name);

//    @Query("SELECT b FROM Book b WHERE b.nameBook = :name")
//    List<Book> searchByName(@Param("name") String keyword);
    @Query("select b \n" +
            "from Book b \n" +
            "inner join Author a on a.id = b.author.id\n" +
            "inner join Catagory c on c.id = b.catagory.id\n" +
            "where b.nameBook like %:keyword% or a.name like %:keyword% or c.nameCatagory like %:keyword%")
    List<Book> searchByAll(@Param("keyword") String name);
}
