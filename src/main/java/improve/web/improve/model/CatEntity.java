package improve.web.improve.model;

import lombok.Data;


import javax.persistence.*;
import java.lang.Long;

/**
 * Created by uev on 23.09.16.
 */
@Entity
@Table(name = "cat", schema = "public", catalog = "improveweb")
public @Data class CatEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(name = "name")
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CatEntity catEntity = (CatEntity) o;

        if (id != null ? !id.equals(catEntity.id) : catEntity.id != null) return false;
        if (name != null ? !name.equals(catEntity.name) : catEntity.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
