/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package helper;

import model.Masa;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author cengizhan
 */
public class MasaHelper {
    
    private SessionFactory sessionFactory = null;
    
    public MasaHelper(){
    
        sessionFactory = MyHibernateUtil.getSessionFactory();
    }
    
    public void insert(Masa masa) throws Exception{
    
        Session session = null;
        Transaction tx = null;
        
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.save(masa);
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
    
    public void update(Masa masa) throws Exception{
    
        Session session = null;
        Transaction tx = null;
        
        try {
            session = sessionFactory.openSession();
            tx =session.beginTransaction();
            session.update(masa);
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
    
    
}
