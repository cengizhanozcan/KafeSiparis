/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import model.UrunTur;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author cengizhan
 */
public class UrunTuruHelper implements Serializable{

    private SessionFactory sessionFactory;

    public UrunTuruHelper() {

        sessionFactory = MyHibernateUtil.getSessionFactory();
    }

    public void insert (UrunTur urunTuru) throws Exception {

        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.save(urunTuru);
            tx.commit();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    }

    public List<UrunTur> listele() throws Exception {
    
        Session session = null;
        List<UrunTur> list = new ArrayList<>();
        try {
            session = sessionFactory.openSession();
            list = session.createCriteria(UrunTur.class,"urunTur")
                    .add(Restrictions.ne("urunTur.isAktif", false))
                    .list();
            
        } catch (Exception e) {
            throw e;
        }
        finally{
            session.close();
        }
        
        return list;
    }
    
    public void update(UrunTur urunTuru) throws Exception{
    
        Session session = null;
        Transaction tx = null;
        try {
            
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.update(urunTuru);
            tx.commit(); 
        } catch (Exception e) {
            if (tx!=null) {
                tx.rollback();
            }
            throw e;
        }
        finally{
            session.close();
        }
    }
}