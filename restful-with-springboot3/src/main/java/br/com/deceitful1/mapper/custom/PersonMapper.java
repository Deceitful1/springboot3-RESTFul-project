package br.com.deceitful1.mapper.custom;


import br.com.deceitful1.dataDTO.version.v2.PersonDTOV2;
import br.com.deceitful1.models.Person;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class PersonMapper
{
    public PersonDTOV2 convertEntityToDTO(Person person)
    {
        PersonDTOV2 entity = new PersonDTOV2();
        entity.setId(person.getId());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setBirthDay(new Date());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());


        return entity   ;
    }
    public Person convertDTOtoEntity(PersonDTOV2 dto)
    {
        Person entity = new Person();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setAddress(dto.getAddress());
        entity.setGender(dto.getGender());
        return entity;

    }


}
