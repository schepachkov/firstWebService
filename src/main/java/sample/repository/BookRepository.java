package sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import sample.entity.Book;

import java.util.List;


public interface BookRepository extends JpaRepository<Book, Integer> {

    @Transactional
    @Modifying
    @Query(
            value = "UPDATE book SET (name, cost, stock, amount) = (:name, :cost, :stock, :amount) WHERE id = :id",
            nativeQuery = true
    )
    void updateBook(@Param("id") int id, @Param("name") String name, @Param("cost") int cost, @Param("stock") String stock, @Param("amount") int amount);


    @Query(value = "SELECT * FROM safe_delete(:tab, :id)", nativeQuery = true)
    boolean safeDelete(@Param("tab") String tableName, @Param("id") int id);

}
