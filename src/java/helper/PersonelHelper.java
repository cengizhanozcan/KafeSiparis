/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import model.Personel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author cengizhan
 */
public class PersonelHelper implements Serializable {

    SessionFactory sessionFactory = null;

    public PersonelHelper() {

        sessionFactory = MyHibernateUtil.getSessionFactory();
    }

    public void insert(Personel personel) throws Exception {

        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.save(personel);
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

    public void delete(Personel personel) throws Exception {

        Session session = null;
        Transaction tx = null; 
        
        personel.setIsAktif(false);
        try {
            
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.update(personel);
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

    public void update(Personel personel) throws Exception {

        Session session = null;
        Transaction tx = null;

        try {

            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.update(personel);
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
    
    public List<Personel> listPersonel() throws Exception{
        
        Session session = null;
        List<Personel> list = new ArrayList<>();
        try {
            session = sessionFactory.openSession();
            list = session.createCriteria(Personel.class, "personel")
                    .createAlias("personel.yetki", "yetki")
                    .add(Restrictions.ne("personel.isAktif", false))
                    .list();
            
        } catch (Exception e) {
            throw e;
        }
        finally{
            session.close();
        }
        
        return list;
    }

    public Personel login(Personel personel) throws Exception {

        Session session = null;
        Personel loginPersonel = new Personel();

        try {
            session = sessionFactory.openSession();
            loginPersonel = (Personel) session.createCriteria(Personel.class, "personel")
                    .createAlias("personel.yetki", "yetki")
                    .add(Restrictions.eq("personel.kullaniciAd", personel.getKullaniciAd()))
                    .add(Restrictions.eq("personel.sifre", personel.getSifre()))
                    .add(Restrictions.ne("personel.isAktif", false))
                    .uniqueResult();

            if (loginPersonel == null) {

                return null;
            }
        } catch (Exception e) {
            throw e;
        }
        finally{
        
            session.close();
        }
        return loginPersonel;
    }
}
