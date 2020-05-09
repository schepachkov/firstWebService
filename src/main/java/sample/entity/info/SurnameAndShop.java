package sample.entity.info;

import javax.persistence.*;

@Entity
@Table(name = "purchase")
@SecondaryTables({
        @SecondaryTable(name = "buyer"),
        @SecondaryTable(name = "seller")
})
@NamedNativeQuery(
        name = "surnameAndShop",
        query = "SELECT 'Имя: ' || b.surname AS \"surname\", " +
                "'Магазин: ' || s.name AS \"name\" " +
                "FROM purchase INNER JOIN buyer b " +
                "ON purchase.buyer = b.id " +
                "INNER JOIN seller s on purchase.seller = s.id;"
)
@SqlResultSetMapping(
        name = "surnameAndShop",
        columns = {
                @ColumnResult(name = "surname"),
                @ColumnResult(name = "name")
        }
)
public class SurnameAndShop {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "surname", table = "buyer")
    private String surname;

    @Column(name = "name", table = "seller")
    private String name;


}
