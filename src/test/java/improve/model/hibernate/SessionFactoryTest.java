package improve.model.hibernate;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPADeleteClause;
import com.mysema.query.jpa.impl.JPAQuery;
import improve.web.improve.model.CatEntity;
import improve.web.improve.model.QCatEntity;
import junit.framework.TestCase;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.logging.Logger;

/**
 * Created by uev on 23.09.16.
 */

public class SessionFactoryTest extends TestCase {

    protected EntityManager em;
    protected JPQLQuery jpql;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        this.em = Persistence.createEntityManagerFactory("persistUnit").createEntityManager();
        this.jpql = new JPAQuery(em);
    }

    @Test

    public void testSessionFactory() throws Exception {
        QCatEntity qcat = QCatEntity.catEntity;
        em.getTransaction().begin();
        new JPADeleteClause(em, qcat).where(qcat.name.eq("test")).execute();
        CatEntity c = new CatEntity();
        c.setName("test");
        em.persist(c);
        em.flush();
        em.getTransaction().commit();
        CatEntity res = (CatEntity) jpql.from(qcat).where(qcat.name.eq("test")).singleResult(qcat);
        assertEquals(res.equals(c), true);
    }
}