package sample.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "shopNameAndArea",
                columns = {
                        @ColumnResult(name = "name"),
                        @ColumnResult(name = "area")
                }
        )
})
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "shopNameAndArea",
                query = "SELECT 'Магазин: ' || name AS \"name\", " +
                        "'Район: ' || area AS \"area\"  " +
                        "FROM seller " +
                        "WHERE lower(trim(area)) IN ('сормовский', 'советский');"
        )
})
@Entity
@Table(name = "seller")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String area;
    private int commission;
}
