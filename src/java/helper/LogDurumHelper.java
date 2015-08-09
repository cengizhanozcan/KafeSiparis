/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import model.LogDurum;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author cengizhan
 */
public class LogDurumHelper implements Serializable{
 
    SessionFactory sessionFactory;
    
    public LogDurumHelper(){
    
        sessionFactory = MyHibernateUtil.getSessionFactory();
    }
    
    public List<LogDurum> listLogDurum() throws Exception{
    
        Session session = null;
        List<LogDurum> list = new ArrayList<>();
        
        try {
            session = sessionFactory.openSession();
            list = session.createCriteria(LogDurum.class, "logDurum")
                    .add(Restrictions.ne("logDurum.isAktif", false))
                    .list();
            
        } catch (Exception e) {
            throw e;
        }
        finally{
        
            session.close();
        }
        return list;
    }
    
}
