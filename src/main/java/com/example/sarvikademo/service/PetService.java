package com.example.sarvikademo.service;

import com.example.sarvikademo.Util;
import com.example.sarvikademo.bean.BeanEvent;
import com.example.sarvikademo.bean.BeanEventOrder;
import com.example.sarvikademo.bean.BeanPet;
import com.example.sarvikademo.modal.Event;
import com.example.sarvikademo.modal.Pet;
import com.example.sarvikademo.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class PetService {

    @Autowired
    PetRepository petRepository;

    public List<Pet> getPetList(BeanPet beanPet) {
        String species = "";
        if(!Util.isEmpty(beanPet.getSpecies()))
            species = beanPet.getSpecies();
        return  petRepository.getPetList(species);
    }

    public Map getPetAndEvents(int id, BeanEventOrder beanEventOrder) {
        return  petRepository.getPetAndEvents(id, beanEventOrder);
    }

    public Pet addPet(BeanPet beanPet)  {
        return  petRepository.addPet(beanPet);
    }

    public Pet updatePet(BeanPet beanPet){
        return  petRepository.updatePet(beanPet);
    }

    public Event addEvent(int id, BeanEvent beanEvent){
        return petRepository.addEvent(id, beanEvent);
    }

    public void deletePet(int id) {
        petRepository.deletePet(id);
    }
}
