package sample.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name ="buyer")
@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "allDistinctAreas",
                columns = @ColumnResult(name = "area")
        ),
        @SqlResultSetMapping(
                name = "surnameAndDiscountIfAreaNizhny",
                columns = {
                        @ColumnResult(name = "surname"),
                        @ColumnResult(name = "discount"),
                        @ColumnResult(name = "area")
                })
})
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "allDistinctAreas",
                query = "SELECT DISTINCT area FROM buyer;"
        ),
        @NamedNativeQuery(
                name = "surnameAndDiscountIfAreaNizhny",
                query = "SELECT 'ФИО: ' || surname AS \"surname\", " +
                        "'Скидка: ' || discount || ' %' as \"discount\", " +
                        "'Район: ' || area AS \"area\" " +
                        "FROM buyer " +
                        "WHERE trim(lower(area)) = 'нижегородский';"
        )
})


public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String surname;
    private String area;
    private int discount;




}

