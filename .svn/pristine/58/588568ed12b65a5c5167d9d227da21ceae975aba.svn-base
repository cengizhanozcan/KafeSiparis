/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import model.MasaNo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author cengizhan
 */
public class MasaNoHelper implements Serializable {
    
    SessionFactory sessionFactory;
    
    public MasaNoHelper(){
        
        sessionFactory = MyHibernateUtil.getSessionFactory();
    }
    
    public void insert(MasaNo masaNo) throws Exception{
    
        Session session =null;
        Transaction tx = null;
        
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            
            session.save(masaNo);
            tx.commit();
            
        } catch (Exception e) {
            if (tx!= null) {
                tx.rollback();
            }
            throw e;
        }
        finally{
            session.close();
        }
        
    }
    
    
    public void update(MasaNo masaNo) throws Exception{
    
        Session session = null;
        Transaction tx = null;
        
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.update(masaNo);
            tx.commit();
            
        } catch (Exception e) {
            if (tx!= null) {
                tx.rollback();
            }
            throw e;
        }
        finally{
            session.close();
        }
    
    }
    
    public List<MasaNo> list() throws Exception{
    
        Session session = null;
        List<MasaNo> listMasaNo = new ArrayList<>();
        
        try {
            session  =sessionFactory.openSession();
            listMasaNo = session.createCriteria(MasaNo.class,"masaNo")
                    .add(Restrictions.ne("masaNo.isAktif", false))
                    .list();
            
        } catch (Exception e) {
            throw e;
        }
        finally{
            session.close();
        }
        return listMasaNo;
    }
}
