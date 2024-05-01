package com.fernandoschimidt.desaioitaufullstack.controller;

import com.fernandoschimidt.desaioitaufullstack.dto.ClientDTO;
import com.fernandoschimidt.desaioitaufullstack.entity.Client;
import com.fernandoschimidt.desaioitaufullstack.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Client> create(@RequestBody ClientDTO userDTO) {
        Client newUser = service.create(userDTO);
        return ResponseEntity.ok().body(newUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable(name = "id") Long id,
                                         @RequestBody ClientDTO userDTO) {
        Client updatedUser = service.update(id, userDTO);
        return ResponseEntity.ok().body(updatedUser);
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAll() {
        List<Client> users = service.findAll();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Client>> getById(@PathVariable(name = "id") Long id) {
        Optional<Client> user = service.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        service.delete(id);
    }
}
