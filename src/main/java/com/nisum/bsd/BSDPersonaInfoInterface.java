package com.nisum.bsd;

import com.nisum.dto.PersonaInfo;

import java.util.List;

public interface BSDPersonaInfoInterface {

    public List<PersonaInfo> findAll();

    public PersonaInfo save(PersonaInfo persona) throws IllegalAccessException;

    public PersonaInfo findById(Integer id);

    public void delete(Integer id);

    public List<PersonaInfo> findByCriteria(PersonaInfo filtro);
}
