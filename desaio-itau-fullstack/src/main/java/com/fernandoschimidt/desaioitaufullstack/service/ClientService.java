package com.fernandoschimidt.desaioitaufullstack.service;

import com.fernandoschimidt.desaioitaufullstack.dto.ClientDTO;
import com.fernandoschimidt.desaioitaufullstack.entity.Client;
import com.fernandoschimidt.desaioitaufullstack.repository.ClientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public Client create(ClientDTO userDTO) {
        Client user = new Client();
        user.setFirstname(userDTO.firstname());
        user.setLastname(userDTO.lastname());
        user.setParticipation(userDTO.participation());
        Double totalParticipation = repository.sumParticipation();

        if (totalParticipation + userDTO.participation() > 100) {
            throw new NullPointerException("Limite da participação é 100, total de participação cadastrado até o momento é de: " + totalParticipation);
        }
        if (userDTO.firstname() == null || userDTO.lastname() == null || userDTO.participation() == null) {
            throw new NullPointerException();
        }
        return repository.save(user);
    }

    public Client update(Long id, ClientDTO userDTO) {
        Client oldUser = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        oldUser.setFirstname(userDTO.firstname());
        oldUser.setLastname(userDTO.lastname());
        oldUser.setParticipation(userDTO.participation());

        if (userDTO.firstname() == null || userDTO.lastname() == null || userDTO.participation() == null) {
            throw new NullPointerException();
        }
        return repository.save(oldUser);
    }

    public List<Client> findAll() {
        return repository.findAll();

    }

    public Optional<Client> findById(Long id) {
        Optional<Client> client = repository.findById(id);
        if (client == null) {
            throw new NullPointerException("Client not found.");
        }
        return repository.findById(id);


    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
