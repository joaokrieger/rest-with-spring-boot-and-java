package br.com.jek.service;

import br.com.jek.controller.PersonController;
import br.com.jek.data.dto.PersonDTO;
import br.com.jek.exception.ResourceNotFoundException;
import br.com.jek.model.Person;
import br.com.jek.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.jek.mapper.ObjectMapper.parseListObjects;
import static br.com.jek.mapper.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<PersonDTO> findAll(){
        List<PersonDTO> personDTOS = parseListObjects(personRepository.findAll(), PersonDTO.class);
        personDTOS.forEach(this::addHateoasLinks);
        return personDTOS;
    }

    public PersonDTO findById(Long id){
        var entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        PersonDTO personDTO = parseObject(entity, PersonDTO.class);
        addHateoasLinks(personDTO);
        return personDTO;
    }

    public PersonDTO create(PersonDTO person){
        var entity = parseObject(person, Person.class);
        PersonDTO personDTO = parseObject(personRepository.save(entity), PersonDTO.class);
        addHateoasLinks(personDTO);
        return personDTO;
    }

    public PersonDTO update(PersonDTO person){
        var entity = personRepository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        PersonDTO personDTO = parseObject(personRepository.save(entity), PersonDTO.class);
        addHateoasLinks(personDTO);
        return personDTO;
    }

    public void delete(Long id){
        Person entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        personRepository.delete(entity);
    }

    private void addHateoasLinks(PersonDTO personDTO) {
        personDTO.add(linkTo(methodOn(PersonController.class).findById(personDTO.getId())).withSelfRel().withType("GET"));
        personDTO.add(linkTo(methodOn(PersonController.class).findAll()).withRel("findAll").withType("GET"));
        personDTO.add(linkTo(methodOn(PersonController.class).create(personDTO)).withRel("create").withType("POST"));
        personDTO.add(linkTo(methodOn(PersonController.class).update(personDTO)).withRel("update").withType("PUT"));
        personDTO.add(linkTo(methodOn(PersonController.class).delete(personDTO.getId())).withRel("delete").withType("DELETE"));
    }
}
