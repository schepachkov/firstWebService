package sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import sample.entity.Seller;


public interface SellerRepository extends JpaRepository<Seller, Integer> {

    @Transactional
    @Modifying
    @Query(
            value = "UPDATE seller SET(name, area, commission) = (:name, :area, :comm) WHERE seller.id = :id",
            nativeQuery = true)
    void updateSeller(@Param("id") int id,@Param("name") String name, @Param("area") String area, @Param("comm") int commission);

    @Query(value = "SELECT * FROM safe_delete(:tab, :id)", nativeQuery = true)
    boolean safeDelete(@Param("tab") String tableName, @Param("id") int id);
}
