package sample.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "purchase")
@SqlResultSetMapping(name = "allDistinctMonths",
            columns = @ColumnResult(name = "date")
)
@NamedNativeQuery(name = "allDistinctMonths",
    query = "SELECT DISTINCT trim(to_char(purchase.date, 'MONTH')) FROM purchase;")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int seller;
    private int buyer;
    private int book;
    private int amount;
    private double total;

}
