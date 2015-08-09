/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import model.Firma;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author cengizhan
 */
public class FirmaHelper implements Serializable{
    
    SessionFactory sessionFactory;
    
    public FirmaHelper(){
    
        sessionFactory  = MyHibernateUtil.getSessionFactory();
    }
    
    public void insert(Firma firma) throws Exception{
    
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.save(firma);
            tx.commit();
            
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }
        finally{
            session.close();
        }
    }
    
    public void update(Firma firma) throws Exception{
    
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.update(firma);
            tx.commit();
            
        } catch (Exception e) {
            if(tx != null){
                tx.rollback();
            }
            throw e;
        }
        finally{
            session.close();
        }
        
    }
    
    
    public List<Firma> list() throws Exception{
    
        Session session = null;
        List<Firma> list = new ArrayList<>();
        
        try {
            session = sessionFactory.openSession();
            list = session.createCriteria(Firma.class,"firma")
                    .add(Restrictions.ne("firma.isAktif", false))
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
