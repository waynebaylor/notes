package com.baylorsc.notes.manager;

import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class QueryManager extends Manager
{
    private SQLQuery prepareQuery(String queryName, Parameters parameters) {
        SQLQuery q = (SQLQuery)this.session().getNamedQuery(queryName);
        
        for(Map.Entry<String, Object> entry : parameters.asMap().entrySet()) {
            if(List.class.isAssignableFrom(entry.getValue().getClass())) {
                q.setParameterList(entry.getKey(), (List<?>)entry.getValue());
            }
            else if(entry.getValue() instanceof Object[]) {
                q.setParameterList(entry.getKey(), (Object[])entry.getValue());
            }
            else {
                q.setParameter(entry.getKey(), entry.getValue());
            }
        }
        
        return q;
    }
    
    public <T> T uniqueBean(String queryName, Parameters parameters, Class<T> beanType) {
        Object result = this.prepareQuery(queryName, parameters)
                .setResultTransformer(Transformers.aliasToBean(beanType))
                .uniqueResult();
        
        return (T)result;
    }
    
    public Map<String, Object> uniqueMap(String queryName, Parameters parameters) {
        Object result = this.prepareQuery(queryName, parameters)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
                .uniqueResult();
        
        return (Map<String, Object>)result;
    }
    
    public <T> List<T> beanList(String queryName, Parameters parameters, Class<T> beanType) {
        List<T> results = this.prepareQuery(queryName, parameters)
                .setResultTransformer(Transformers.aliasToBean(beanType))
                .list();
        
        return results;
    }
    
    public List<Map<String, Object>> beanList(String queryName, Parameters parameters) {
        List<Map<String, Object>> results = this.prepareQuery(queryName, parameters)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
                .list();
        
        return results;
    }
}
