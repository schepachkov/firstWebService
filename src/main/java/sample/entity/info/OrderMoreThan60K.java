package sample.entity.info;


import javax.persistence.*;

@Entity
@Table(name = "purchase")
@SecondaryTable(name = "buyer")
@SqlResultSetMapping(
        name = "orderMoreThan60K",
        columns = {
                @ColumnResult(name = "id"),
                @ColumnResult(name = "surname"),
                @ColumnResult(name = "date")
        })
@NamedNativeQuery(
        name = "orderMoreThan60K",
        query = "SELECT 'Номер заказа: ' || purchase.id AS \"id\", " +
                "       'Имя: ' || surname AS surname, " +
                "       date " +
                "FROM purchase INNER JOIN buyer " +
                "    b ON purchase.buyer = b.id " +
                "WHERE  total >= 60000;"
)
public class OrderMoreThan60K {

    @Id
    private int id;
    @Column(name = "surname", table = "buyer")
    private String surname;
}
