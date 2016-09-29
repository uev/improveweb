package improve.web.improve.model;

import javax.persistence.*;
import java.lang.Long;

/**
 * Created by uev on 23.09.16.
 */
@Entity
@Table(name = "cat", schema = "public", catalog = "improveweb")
public class CatEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
