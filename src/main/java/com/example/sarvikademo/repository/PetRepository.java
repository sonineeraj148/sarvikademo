package com.example.sarvikademo.repository;

import com.example.sarvikademo.Util;
import com.example.sarvikademo.bean.BeanEvent;
import com.example.sarvikademo.bean.BeanEventOrder;
import com.example.sarvikademo.bean.BeanPet;
import com.example.sarvikademo.modal.Event;
import com.example.sarvikademo.modal.Pet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PetRepository {

    @Autowired
    private SessionFactory factory;

    protected Session getSession() {
        Session session = factory.getCurrentSession();
        if (session == null) {
            session = factory.openSession();
        }
        return session;
    }

    @Transactional(readOnly = true)
    public List<Pet> getPetList(String species) {
        String hql = "SELECT p FROM Pet p ";
        if(!Util.isEmpty(species))
            hql += "WHERE p.species = :species";

        Query query = getSession().createQuery(hql);
        if(!Util.isEmpty(species))
            query.setParameter("species", species);

        List<Pet> petList = query.getResultList();
        return petList;
    }


    @Transactional(readOnly = true)
    public Map getPetAndEvents(int id, BeanEventOrder beanEventOrder) {
       /* String hqlPet = "SELECT p FROM Pet p   WHERE p.id = :petId  " ;
        Query queryPet = getSession().createQuery(hqlPet);
        queryPet.setParameter("petId", id);
        Pet petData = (Pet) queryPet.getSingleResult();*/


        String hqlEvent = "SELECT e FROM Event e WHERE e.petId = :petId ";
        if(!Util.isEmpty(beanEventOrder.getColumnName()) && !Util.isEmpty(beanEventOrder.getColumnName()))
            hqlEvent += "ORDER BY e."+beanEventOrder.getColumnName()+" "+beanEventOrder.getOrderType();
        else
            hqlEvent += "ORDER BY e.date DESC";

        Query queryEvent = getSession().createQuery(hqlEvent);
        queryEvent.setParameter("petId", id);
        List<Event> eventData =  queryEvent.getResultList();

        Map responseObj = new HashMap();
        responseObj.put("petData", getPetDetail(id));
        responseObj.put("eventData", eventData);
        return responseObj;
    }


    @Transactional
    public Pet addPet(BeanPet beanPet) {
        Pet pet = new Pet();
        pet.setBirth(Date.valueOf(beanPet.getBirth()));
        pet.setDeath(Date.valueOf(beanPet.getDeath()));
        pet.setName(beanPet.getName());
        pet.setSex(beanPet.getSex());
        pet.setOwner(beanPet.getOwner());
        pet.setSpecies(beanPet.getSpecies());
        getSession().save(pet);
        return pet;
    }


    @Transactional
    public Pet updatePet(BeanPet beanPet) {
        String hql = "UPDATE Pet SET name = :name, owner = :owner, species = :species, sex = :sex, birth = :birth, death = :death WHERE id = :id";
        Query query = getSession().createQuery(hql);
        query.setParameter("name", beanPet.getName());
        query.setParameter("owner", beanPet.getOwner());
        query.setParameter("species", beanPet.getSpecies());
        query.setParameter("sex", beanPet.getSex());
        query.setParameter("birth",Date.valueOf( beanPet.getBirth()));
        query.setParameter("death", Date.valueOf(beanPet.getDeath()));
        query.setParameter("id", beanPet.getId());
        query.executeUpdate();
        return getPetDetail(beanPet.getId());

    }

    @Transactional(readOnly = true)
    public Pet getPetDetail(int id){
        String hql = "SELECT p FROM Pet p WHERE p.id = :id";
        Query query = getSession().createQuery(hql);
        query.setParameter("id", id);
        Pet pet = (Pet) query.getSingleResult();
        return pet;
    }


    @Transactional
    public Event addEvent(int id, BeanEvent beanEvent) {
        Event event = new Event();
        event.setPetId(id);
        event.setDate(Date.valueOf(beanEvent.getDate()));
        event.setType(beanEvent.getType());
        event.setRemark(beanEvent.getRemark());
        getSession().save(event);
        return event;
    }


    @Transactional
    public void deletePet(int id) {

        String hql = "DELETE FROM Event WHERE petId = :id";
        Query query = getSession().createQuery(hql);
        query.setParameter("id", id);
        query.executeUpdate();

        String hql1 = "DELETE FROM Pet WHERE id = :id";
        Query query1 = getSession().createQuery(hql1);
        query1.setParameter("id", id);
        query1.executeUpdate();
    }

}
