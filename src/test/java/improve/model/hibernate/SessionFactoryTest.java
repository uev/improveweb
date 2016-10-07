package improve.model.hibernate;

import static org.mockito.Mockito.*;

import com.google.common.collect.ImmutableMap;
import com.mysema.query.Tuple;
import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPADeleteClause;
import com.mysema.query.jpa.impl.JPAQuery;
import improve.web.improve.jsf.view.dto.IndexDTO;
import improve.web.improve.model.CatEntity;
import improve.web.improve.model.QCatEntity;
import improve.web.improve.services.jpa2.CatService;
import junit.framework.TestCase;
import org.junit.Test;




import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Map;

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

    @Test
    /**
     * Overriding some method
     */
    public void testGetFilterService() {
        CatService service = spy(new CatService());
        doReturn(new JPAQuery(em)).when(service).getJPQLQuery(); // overridng
        Map<String, String> filter = ImmutableMap.of("product", "Отбеливатель");
        List<IndexDTO> resultList =  service.getList(filter);
        assertEquals(resultList.size() > 0, true);
    }
}