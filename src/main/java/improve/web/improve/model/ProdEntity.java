package improve.web.improve.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by uev on 23.09.16.
 */
@Entity
@Table(name = "prod", schema = "public", catalog = "improveweb")
public @Data class  ProdEntity implements Serializable {
    @Id @Column(name = "id") private Long id;
    @Basic @Column(name = "name") private String name;
    @Basic @Column(name = "price") private Double price;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)  @JoinColumn(name = "cat_id")
    private CatEntity categoryFk;
}