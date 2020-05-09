package sample.entity;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "book")
@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "allNameAndCost",
                columns = {
                        @ColumnResult(name = "name"),
                        @ColumnResult(name = "cost"),
                        @ColumnResult(name = "amount")
                }
        ),
        @SqlResultSetMapping(
                name = "nameAndCostWithFilterAndSort",
                columns = {
                        @ColumnResult(name = "name"),
                        @ColumnResult(name = "cost")
                }
        )
})
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "allNameAndCost",
                query = "SELECT 'Название: ' || name AS \"name\", " +
                        "'Стоимость: ' || cost || ' RUB.' AS \"cost\", " +
                        "'Количество: ' || amount AS \"amount\" " +
                        "FROM book;"
        ),
        @NamedNativeQuery(
                name = "nameAndCostWithFilterAndSort",
                query = "SELECT 'Название: ' || name AS \"name\", " +
                        "'Стоимость: ' || cost AS \"cost\" " +
                        "FROM book " +
                        "WHERE lower(name) LIKE '%windows%' OR " +
                        "cost >= 20000 " +
                        "ORDER BY name, cost DESC;"
        )
})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int cost;
    private String stock;
    private int amount;

}
