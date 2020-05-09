package sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sample.entity.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {


    @Query(
            value = "SELECT * FROM safe_delete(:tab, :id)",
            nativeQuery = true
    )
    boolean safeDelete(@Param("tab") String tableName,
                       @Param("id") int id
    );



    @Query(
            value = "SELECT * FROM calculate_total(:purchase_id)",
            nativeQuery = true
    )
    int calculateTotal(@Param("purchase_id") int id);



    @Query(
            value = "SELECT * FROM can_insert_into_purchase(:book, :amount)",
            nativeQuery = true
    )
    boolean canInsert(@Param("book") int idBook,
                      @Param("amount") int amount
                       );
}
