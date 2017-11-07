package com.github.admissionCommittee;

import com.github.admissionCommittee.model.Role;
import com.github.admissionCommittee.model.User;
import com.github.admissionCommittee.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


/**
 * Created by Admin on 07.11.2017.
 */
public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Role role = new Role("admin");
        session.save(role);

        User user = new User("firstUser",role);



        session.save(user);
        Transaction transaction= session.getTransaction();
        //transaction.rollback();
        transaction.commit();

        session.beginTransaction();
        User load = session.load(User.class, 0);
        Role loadedRole = session.load(Role.class, 0);
        System.out.println(user.getName());
        System.out.println(loadedRole.getName());

        /*CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(Pet.class);
        Metamodel m = em.getMetamodel();
        EntityType<Pet> Pet_ = m.entity(Pet.class);
        Root<Pet> pet = cq.from(Pet.class);
        cq.where(cb.equal(pet.get(Pet_.name)), "Fido");*/

        session.close();
        sessionFactory.close();
    }


}
