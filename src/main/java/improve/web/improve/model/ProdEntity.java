package improve.web.improve.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by uev on 23.09.16.
 */
@Entity
@Table(name = "prod", schema = "public", catalog = "improveweb")
public @Data class  ProdEntity {
    @Id @Column(name = "id") private Long id;
    @Basic @Column(name = "name") private String name;
    @Basic @Column(name = "price") private Double price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProdEntity that = (ProdEntity) o;

        if (id != null ? id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}
