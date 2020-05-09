package sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import sample.entity.Buyer;


public interface BuyerRepository extends JpaRepository<Buyer, Integer> {

    @Transactional
    @Modifying
    @Query(
            value = "UPDATE buyer SET(surname, area, discount) = (:sn, :area, :discount) WHERE buyer.id = :id",
            nativeQuery = true
    )
    void updateBuyer(@Param("id") int id, @Param("sn") String surname, @Param("area") String area, @Param("discount") int discount);

    @Query(value = "SELECT * FROM safe_delete(:tab, :id)", nativeQuery = true)
    boolean safeDelete(@Param("tab") String tableName, @Param("id") int id);
}
