package improve.web.improve.services.jpa2;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.types.expr.SimpleExpression;
import improve.web.improve.model.QCatEntity;
import improve.web.improve.model.QProdEntity;
import improve.web.improve.services.tomcat.EntityManagerContext;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by uev on 24.09.16.
 */

@ManagedBean(name = "categoryService")
@ApplicationScoped
public class CatService {

    public JPQLQuery getJPQLQuery() {
        return EntityManagerContext.getJPQL();
    }

    public Object[] getList(Object... params) {
        final QCatEntity catEntity = QCatEntity.catEntity;
        final QProdEntity prodEntity = QProdEntity.prodEntity;
        Map<String,SimpleExpression> filterArgs = new HashMap<String, SimpleExpression>() {{
            put("priceOf",prodEntity.price);
            put("priceTo", prodEntity.price);
            put("category", catEntity.name);
            put("product", prodEntity.name);
        }};
        BooleanBuilder builder = new BooleanBuilder();
        JPQLQuery jpql = getJPQLQuery();
        jpql.from(prodEntity).join(prodEntity.categoryFk, catEntity);

        if (params.length > 0) {
            Map<String, String> args = (Map<String, String>) params[0];
            for (Map.Entry<String,SimpleExpression> entry: filterArgs.entrySet()) {
                if (args.containsKey(entry.getKey())) {
                    String compValue = args.get(entry.getKey()).toString();
                    builder.and(entry.getValue().eq(compValue));
                }
            }
            jpql.where(builder);
        }
        Object[] dlst = jpql.list(prodEntity) .toArray();
        return dlst;
    }
}
