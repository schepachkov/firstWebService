package sample.entity.info;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "purchase")
@SecondaryTables({
        @SecondaryTable(name = "buyer"),
        @SecondaryTable(name = "seller")
})
@SqlResultSetMapping(
        name = "buyInYourselfAreaAfterThanMarch",
        columns = {
                @ColumnResult(name = "surname"),
                @ColumnResult(name = "area"),
                @ColumnResult(name = "date")
        }
)
@NamedNativeQuery(
        name = "buyInYourselfAreaAfterThanMarch",
        query = "SELECT 'Покупатель: ' || surname AS \"surname\", 'Район:' || b.area AS \"area\", date " +
                "FROM purchase " +
                "    INNER JOIN buyer " +
                "        b on purchase.buyer = b.id " +
                "    INNER JOIN seller s " +
                "        on purchase.seller = s.id " +
                "WHERE date_part('month', date) >= 3 AND s.area = b.area ORDER BY surname;"
)
public class BuyInYourselfAreaAfterThanMarch {

    @Id
    private int id;

    @Column(name = "surname", table = "buyer")
    private String surName;

    @Column(name = "area", table = "seller")
    private String area;

    private String date;
}
