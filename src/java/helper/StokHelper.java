/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package helper;

import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Stok;
import model.Urun;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author cengizhan
 */
public class StokHelper implements Serializable{
    
    private SessionFactory sessionFactory;
    
    public StokHelper(){
        sessionFactory = MyHibernateUtil.getSessionFactory();
        
    }
    
    public void insert(Stok stok) throws Exception{
    
        Session session = null;
        Transaction tx = null;
        
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.save(stok);
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
    
    public void update(Stok stok) throws Exception{
        
        Session session = null;
        Transaction tx = null;
    
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.update(stok);
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
    
    public List<Stok> findStok(Urun urun) throws Exception{
    
        Session session = null;
        List<Stok> list = new ArrayList<>();
        
        try {
            session = sessionFactory.openSession();
            list = session.createCriteria(Stok.class ,"stok")
                    .createAlias("stok.urun", "urun")
                    .add(Restrictions.eq("urun.urunId", urun.getUrunId()))
                    .add(Restrictions.ne("urun.isAktif", false))
                    .add(Restrictions.ne("stok.isAktif", false))
                    .list();
        } catch (Exception e) {
            throw e;
        } 
        finally{
            session.close();
        }
        
        return list;
    }
    
    public List<Stok> getAllStok() throws Exception{
    
        Session session = null;
        List<Stok> list = new ArrayList<>();
        try {
            
            session = sessionFactory.openSession();
            list = session.createCriteria(Stok.class, "stok")
                    .createAlias("stok.urun", "urun")
                    .add(Restrictions.ne("urun.isAktif", false))
                    .add(Restrictions.ne("stok.isAktif", false))
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
