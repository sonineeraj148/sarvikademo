package com.example.sarvikademo.controller;

import com.example.sarvikademo.ResponsePacket;
import com.example.sarvikademo.Util;
import com.example.sarvikademo.bean.BeanEvent;
import com.example.sarvikademo.bean.BeanEventOrder;
import com.example.sarvikademo.bean.BeanPet;
import com.example.sarvikademo.modal.Event;
import com.example.sarvikademo.modal.Pet;
import com.example.sarvikademo.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
public class PetController {

    @Autowired
    PetService petService;

    @GetMapping(value = "/pets")
    public Object getPetList(HttpServletRequest request, @RequestBody BeanPet beanPet) {
        ResponsePacket responsePacket = null;
        try {
            List<Pet> petList = petService.getPetList(beanPet);
            String data = Util.getObjectToJson(petList);
            responsePacket = new ResponsePacket(HttpStatus.OK.value()," Pet List ",data);
            return new ResponseEntity<>(responsePacket, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responsePacket = new ResponsePacket(HttpStatus.BAD_REQUEST.value(),e.getMessage(),null);
            return new ResponseEntity<>(responsePacket, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping(value = "/pets/{id}")
    public Object getPetAndEvents(HttpServletRequest request, @PathVariable int id, @RequestBody BeanEventOrder beanEventOrder) {
        ResponsePacket responsePacket = null;
        try {
            Map petEventData = petService.getPetAndEvents(id, beanEventOrder);
            String data = Util.getObjectToJson(petEventData);
            responsePacket = new ResponsePacket(HttpStatus.OK.value()," Pet Events List ",data);
            return new ResponseEntity<>(responsePacket, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responsePacket = new ResponsePacket(HttpStatus.BAD_REQUEST.value(),e.getMessage(),null);
            return new ResponseEntity<>(responsePacket, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/pets")
    public Object addPet(HttpServletRequest request, @RequestBody BeanPet beanPet) {
        ResponsePacket responsePacket = null;
        try {
            Pet pet = petService.addPet(beanPet);
            String data = Util.getObjectToJson(pet);
            responsePacket = new ResponsePacket(HttpStatus.OK.value()," Pet Added ",data);
            return new ResponseEntity<>(responsePacket, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responsePacket = new ResponsePacket(HttpStatus.BAD_REQUEST.value(),e.getMessage(),null);
            return new ResponseEntity<>(responsePacket, HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping(value = "/pets")
    public Object updatePet(HttpServletRequest request, @RequestBody BeanPet beanPet) {
        ResponsePacket responsePacket = null;
        try {
            Pet pet = petService.updatePet(beanPet);
            String data = Util.getObjectToJson(pet);
            responsePacket = new ResponsePacket(HttpStatus.OK.value()," Pet Updated ",data);
            return new ResponseEntity<>(responsePacket, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responsePacket = new ResponsePacket(HttpStatus.BAD_REQUEST.value(),e.getMessage(),null);
            return new ResponseEntity<>(responsePacket, HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping(value = "/pets/{id}")
    public Object addEvent(HttpServletRequest request, @PathVariable int id, @RequestBody BeanEvent beanEvent) {
        ResponsePacket responsePacket = null;
        try {
            Event event = petService.addEvent(id, beanEvent);
            String data = Util.getObjectToJson(event);
            responsePacket = new ResponsePacket(HttpStatus.OK.value()," Event Added ",data);
            return new ResponseEntity<>(responsePacket, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responsePacket = new ResponsePacket(HttpStatus.BAD_REQUEST.value(),"No Pet Id Found",null);
            return new ResponseEntity<>(responsePacket, HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping(value = "/pet")
    public Object deletePet(HttpServletRequest request,  @RequestBody BeanPet beanPet) {
        ResponsePacket responsePacket = null;
        try {
            petService.deletePet(beanPet.getId());
            responsePacket = new ResponsePacket(HttpStatus.OK.value()," Pet Deleted ",null);
            return new ResponseEntity<>(responsePacket, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responsePacket = new ResponsePacket(HttpStatus.BAD_REQUEST.value(),e.getMessage(),null);
            return new ResponseEntity<>(responsePacket, HttpStatus.BAD_REQUEST);
        }
    }
}
