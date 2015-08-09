/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Gider;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author cengizhan
 */
public class GiderHelper implements Serializable{
    
    private SessionFactory sessionFactory;
    
    public GiderHelper(){
        
        sessionFactory = MyHibernateUtil.getSessionFactory();
    }
    
    public void insert(Gider gider) throws Exception{
    
        Session session = null;
        Transaction tx = null;
        
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.save(gider);
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
    
    public List<Gider> listStok(Date startDate, Date endDate) throws Exception{
    
        Session session = null;
        List<Gider> list = new ArrayList<>();
        try {
            session = sessionFactory.openSession();
            
            list = session.createCriteria(Gider.class,"gider")
                    .createAlias("gider.giderTuru", "giderTuru")
                    .createAlias("gider.stok", "stok")
                    .createAlias("stok.urun", "urun")
                    .createAlias("stok.firma", "firma")
                    .createAlias("urun.urunTur", "urunTur")
                    .add(Restrictions.ne("gider.isAktif", false))
                    .add(Restrictions.ne("stok.isAktif", false))
                    .add(Restrictions.ne("giderTuru.isAktif", false))
                    .add(Restrictions.ne("urun.isAktif", false))
                    .add(Restrictions.between("gider.alisTarihi", startDate, endDate))
                    .addOrder(Order.desc("gider.alisTarihi"))
                    .list();
                    System.out.println(list.size());
             
        } catch (Exception e) {
                    System.out.println(e.toString());
            throw e;
        }
        finally{
            session.close();
        }
        return list;
    }
    
}
