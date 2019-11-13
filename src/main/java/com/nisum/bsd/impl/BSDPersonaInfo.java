package com.nisum.bsd.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import com.nisum.bsd.BSDPersonaInfoInterface;
import com.nisum.dto.PersonaInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BSDPersonaInfo implements BSDPersonaInfoInterface {

    private static final Logger LOG = LoggerFactory.getLogger(BSDPersonaInfo.class);

    protected List<PersonaInfo> listPerson = new ArrayList<>();
    private Integer id = 0;
    private static List<String> listHair = new ArrayList<String>(Arrays.asList("black", "red", "yellow", "brown", "white"));

    @Override
    public List<PersonaInfo> findAll() {
        return listPerson;
    }

    @Override
    public PersonaInfo save(PersonaInfo persona) throws IllegalAccessException {
        PersonaInfo newPerson = new PersonaInfo();
        if (validHairColour(persona)) {
            if (persona.getId() == null) {
                this.id += 1;
                newPerson = persona;
                newPerson.setId(this.id);
                listPerson.add(persona);
            } else {
                for (int i = 0; i < listPerson.size(); i++) {
                    if (persona.getId().equals(listPerson.get(i).getId())) {
                        if (persona.getName() != null) {
                            listPerson.get(i).setName(persona.getName());
                        }
                        if (persona.getLastname() != null) {
                            listPerson.get(i).setLastname(persona.getLastname());
                        }
                        if (persona.getAddress() != null) {
                            listPerson.get(i).setAddress(persona.getAddress());
                        }
                        if (persona.getPhone() != null) {
                            listPerson.get(i).setPhone(persona.getPhone());
                        }
                        if (persona.getHair() != null) {
                            listPerson.get(i).setHair(persona.getHair());
                        }
                        newPerson = listPerson.get(i);
                    }
                }
            }
        } else {
            throw new IllegalAccessException("Hair Colour Not Valid");
        }
        return newPerson;
    }

    @Override
    public PersonaInfo findById(Integer id) {
        for (int i = 0; i < listPerson.size(); i++) {
            if (id.equals(listPerson.get(i).getId())) {
                return listPerson.get(i);
            }
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        for (int i = 0; i < listPerson.size(); i++) {
            if (id.equals(listPerson.get(i).getId())) {
                listPerson.remove(i);
                break;
            }
        }
    }

    @Override
    public List<PersonaInfo> findByCriteria(PersonaInfo filtro) {
        List<PersonaInfo> listPers = new ArrayList<>();
        PersonaInfo persona;
        for (int i = 0; i < listPerson.size(); i++) {
            if (filtro.getId() == null || filtro.getId().equals(listPerson.get(i).getId())) {
                if (filtro.getName() == null || filtro.getName().equals(listPerson.get(i).getName())) {
                    if (filtro.getLastname() == null || filtro.getLastname().equals(listPerson.get(i).getLastname())) {
                        if (filtro.getAddress() == null || filtro.getAddress().equals(listPerson.get(i).getAddress())) {
                            if (filtro.getPhone() == null || filtro.getPhone().equals(listPerson.get(i).getPhone())) {
                                if (filtro.getHair() == null || filtro.getHair().equals(listPerson.get(i).getHair())) {
                                    persona = new PersonaInfo();
                                    persona = listPerson.get(i);
                                    listPers.add(persona);
                                }
                            }
                        }
                    }
                }
            }
        }
        return listPers;
    }

    private Boolean validHairColour(PersonaInfo persona) {
        if (listHair.contains(persona.getHair())) {
            return true;
        }
        return false;
    }

}
