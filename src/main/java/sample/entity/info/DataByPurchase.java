package sample.entity.info;

import javax.persistence.*;


@Entity
@Table(name = "purchase")
@SecondaryTables({
        @SecondaryTable(name = "book"),
        @SecondaryTable(name = "seller")
})
@SqlResultSetMapping(
        name = "dataByPurchase",
        columns = {
                @ColumnResult(name = "name"),
                @ColumnResult(name = "seller"),
                @ColumnResult(name = "book"),
                @ColumnResult(name = "amount")
        }
)
@NamedNativeQuery(
        name = "dataByPurchase",
        query = "SELECT 'Название: '|| b.name AS \"name\", " +
                "'Район: '|| s.area AS \"area\", " +
                "'Количество: '|| purchase.amount AS \"amount\", " +
                "'Стоимость: '|| b.cost || ' RUB.' AS \"cost\" " +
                "FROM purchase " +
                "         INNER JOIN book b " +
                "                    ON purchase.book = b.id " +
                "         INNER JOIN  seller s ON purchase.seller = s.id " +
                "WHERE purchase.amount >= 10 ORDER BY b.cost;"
)
public class DataByPurchase {

    @Id
    private int id;

    @Column(name = "name", table = "book")
    private String name;

    @Column(name = "area", table = "seller")
    private String area;

    @Column(name = "cost", table = "book")
    private int cost;

    private int amount;
}
