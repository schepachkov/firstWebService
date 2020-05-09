package sample.entity.info;

import javax.persistence.*;

@Entity
@Table(name = "purchase")
@SecondaryTables({
        @SecondaryTable(name = "buyer"),
        @SecondaryTable(name = "seller")
})
@SqlResultSetMapping(
        name = "notAvtozavodShopWithBuyerFilter",
        columns = {
                @ColumnResult(name = "name"),
                @ColumnResult(name = "area")
        }
)
@NamedNativeQuery(
        name = "notAvtozavodShopWithBuyerFilter",
        query = "SELECT DISTINCT s.name AS \"name\", s.area AS \"area\"FROM purchase " +
                "    INNER JOIN seller " +
                "        s ON purchase.seller = s.id " +
                "    INNER JOIN buyer b " +
                "        ON purchase.buyer = b.id " +
                "WHERE trim(lower(s.area)) <> 'автозаводский' AND b.discount BETWEEN 10 AND 15;"
)
public class NotAvtozavodShopWithBuyerFilter {

    @Id
    private int id;

    @Column(name = "name", table = "seller")
    private String name;

    @Column(name = "area", table = "seller")
    private String area;

}
