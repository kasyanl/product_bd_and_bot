package by.kasyan.database.service;

import by.kasyan.database.bean.Person;
import by.kasyan.database.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private PersonRepository personRepository;

    // проверка на совпадение login и password
    public boolean verificationOfAuthenticity(String login, String password) {
        for (Person person : findAllPerson()) {
            if (login.equals(person.getLogin()) && password.equals(person.getPassword())) return true;
        }
        return false;
    }

    // отправка запроса на получение всех пользователей их БД
    public List<Person> findAllPerson() {
        return (List<Person>) personRepository.findAll();
    }
//
//    @Autowired
//    public void setPersonRepository(PersonRepository personRepository) {
//        this.personRepository = personRepository;
//    }
}