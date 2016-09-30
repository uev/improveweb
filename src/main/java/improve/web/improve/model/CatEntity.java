package improve.web.improve.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by uev on 23.09.16.
 */
@Entity
@Table(name = "cat", schema = "public", catalog = "improveweb")
public @Data class CatEntity implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(name = "name")
    private String name;

}
