package com.nisum.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nisum.bsd.BSDPersonaInfoInterface;
import com.nisum.bsd.impl.BSDPersonaInfo;
import com.nisum.dto.PersonaInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/")
public class PersonaInfoController {

    private static final Logger LOG = LoggerFactory.getLogger(PersonaInfoController.class);

    @Autowired
    BSDPersonaInfoInterface bsdPersona;

    @GetMapping(value = "persona")
    public List<PersonaInfo> getDataPerson() {
        return bsdPersona.findAll();
    }

    @GetMapping("persona/{id}")
    public ResponseEntity<?> show(@PathVariable Integer id) {
        PersonaInfo persona = null;
        Map<String, Object> response = new HashMap<>();

        persona = bsdPersona.findById(id);

        if (persona == null) {
            response.put("mensaje", "El usuario ID: ".concat(id.toString().concat(" no existe registrado!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<PersonaInfo>(persona, HttpStatus.OK);
    }

    @PostMapping("persona/filtro")
    public List<PersonaInfo> showFiltered(@Valid @RequestBody PersonaInfo filtro) {
        return bsdPersona.findByCriteria(filtro);
    }

    @DeleteMapping("persona/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();

        PersonaInfo persona = bsdPersona.findById(id);

        if (persona == null) {
            response.put("mensaje", "El usuario ID: ".concat(id.toString().concat(" no existe registrado!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        bsdPersona.delete(id);
        response.put("mensaje", "Usuario eliminado con éxito!");

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @PostMapping("persona")
    public ResponseEntity<?> create(@Valid @RequestBody Object objeto) {

        Map<String, Object> response = new HashMap<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            PersonaInfo persona= mapper.readValue((byte[]) objeto, PersonaInfo.class);

            if (persona.getId() != null) {
                PersonaInfo newPerson = bsdPersona.findById(persona.getId());
                response.put("mensaje", "La persona ha sido actualizada con éxito!");
                if (newPerson == null) {
                    response.put("mensaje", "No existe Id persona para Actualizar..!");
                    response.put("error", "Id Person Not Exists");
                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
                }
            } else {
                List<PersonaInfo> listaPersona = bsdPersona.findByCriteria(persona);
                response.put("mensaje", "La persona ha sido creada con éxito!");
                if (listaPersona.size() > 0) {
                    response.put("mensaje", "La persona ya existe..!");
                    response.put("error", "Person Exists");
                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
                }
            }
            try {
                PersonaInfo personaNew = bsdPersona.save(persona);
                response.put("persona", personaNew);
            } catch (IllegalAccessException e) {
                response.put("mensaje", "Color Cabello Invalido..!");
                response.put("error", e.getMessage());
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (Exception ex) {
            LOG.error(" - Error Exception:" + ex.getMessage() + " *****************************" );
            response.put("mensaje", "Valor Campo Invalido..!");
            response.put("error", "Exception Invalid Cast Field");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

    }

    @GetMapping(value = "saludo")
    public String getSaludo() {

        PersonaInfo personaNew = new PersonaInfo();
        personaNew.setName("Christian");
        personaNew.setLastname("Ayo Roca");
        personaNew.setAddress("Calle el Molino 1845");
        personaNew.setPhone(56934160364L);
        personaNew.setHair("black");

        try {
            return "Hola Mundo " + bsdPersona.save(personaNew).toString();
        } catch (IllegalAccessException e) {
            return "Error " + e.getMessage();
        }
    }
}
