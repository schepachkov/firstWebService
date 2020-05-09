package sample.entity.info;

import javax.persistence.*;

@Entity
@Table(name = "purchase")
@SecondaryTables({
        @SecondaryTable(name = "buyer"),
        @SecondaryTable(name = "book")
})
@NamedNativeQuery(
        name = "joinBuyerBookPurchase",
        query = "SELECT 'Покупатель: ' || surname AS \"surname\", " +
                "'Скидка: ' || discount || '%' AS \"discount\", " +
                "'Магазин: ' || b2.name AS \"name\", " +
                "'Количество: ' || purchase.amount AS \"amount\", " +
                "date AS \"date\" " +
                "FROM purchase INNER JOIN buyer b " +
                "ON purchase.buyer = b.id " +
                "INNER JOIN book b2 ON purchase.book = b2.id;"
)
@SqlResultSetMapping(
        name = "joinBuyerBookPurchase",
        columns = {
                @ColumnResult(name = "surname"),
                @ColumnResult(name = "discount"),
                @ColumnResult(name = "name"),
                @ColumnResult(name = "amount"),
                @ColumnResult(name = "date")
        }
)
public class SurnameDiscountNameAmountDate {

    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "amount")
    private int amount;

    @Column(name = "surname", table = "buyer", insertable = false, updatable = false)
    private String surname;

    @Column(name = "surname", table = "buyer")
    private int discount;

    @Column(name = "name", table = "book")
    private String name;

}
