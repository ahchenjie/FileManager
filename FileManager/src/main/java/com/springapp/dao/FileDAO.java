package com.springapp.dao;

import com.springapp.model.File;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yuriy on 04.10.15.
 */
@Repository
public class FileDAO {

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }


    @Transactional
    public void saveFile(File file) {
        Session session = sessionFactory.getCurrentSession();
        session.save(file);
    }

    @Transactional
    public List<File> fileList() {
        Session session = sessionFactory.getCurrentSession();
        List<File> files = null;
        files = (List<File>) session.createQuery("from File").list();
        return files;
    }

    @Transactional
    public File get(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return (File) session.get(File.class, id);
    }

    @Transactional
    public void remove(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        File file = (File) session.get(File.class, id);
        session.delete(file);
    }
}