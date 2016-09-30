package improve.web.improve.services.tomcat;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import lombok.extern.java.Log;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by uev on 29.09.16.
 */
@Log
public class EntityManagerContext implements ServletContextListener {

    private static EntityManagerFactory emf;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        emf = Persistence.createEntityManagerFactory("persistUnit");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        emf.close();
    }

    /**
     * @return EntityManager
     */
    public static EntityManager getEntityManager() {
        if (emf == null) {
            throw new IllegalStateException("Context is not initialized yet.");
        }
        return emf.createEntityManager();
    }

    /**
     * @return JPQLQuery
     */
    public static JPQLQuery getJPQL() {
        return new JPAQuery(getEntityManager());
    }
}
