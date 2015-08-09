/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import model.GiderTuru;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author cengizhan
 */
public class GiderTuruHelper implements Serializable{
    
    private SessionFactory sessionFactory;
    
    public GiderTuruHelper(){
        
        sessionFactory = MyHibernateUtil.getSessionFactory();
    }
    
    public void insert(GiderTuru giderTuru) throws Exception{
    
        Session session = null;
        Transaction tx = null;
        
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.save(giderTuru);
            tx.commit();
            
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
            
            throw e;
        }
        finally{
            session.close();
        }
    }
    
    public List<GiderTuru> list() throws Exception{
        
        Session session = null;
        List<GiderTuru> list = new ArrayList<>();
        try {
            session = sessionFactory.openSession();
            list = session.createCriteria(GiderTuru.class, "giderTuru")
                    .add(Restrictions.ne("giderTuru.isAktif", false))
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
