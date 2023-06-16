package com.manutencao.industrial.application.operador.resource;

import com.manutencao.industrial.application.operador.dto.view.OperadorDTO;
import com.manutencao.industrial.domain.operador.service.OperadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/operadores")
public class OperadorResource {

    @Autowired
    private OperadorService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<OperadorDTO> findById(@PathVariable Integer id) {
        OperadorDTO objDTO = new OperadorDTO(service.findById(id));
        return ResponseEntity.ok().body(objDTO);
    }

    @GetMapping
    public ResponseEntity<List<OperadorDTO>> findAll() {
        List<OperadorDTO> listDTO = service.findAll().stream().map(obj -> new OperadorDTO(obj))
        .collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<OperadorDTO> create(@Valid @RequestBody OperadorDTO objDTO) {
        var newObj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<OperadorDTO> update(@PathVariable Integer id, @Valid @RequestBody OperadorDTO objDTO) {
        OperadorDTO newObj = new OperadorDTO(service.update(id, objDTO));
        return ResponseEntity.ok().body(newObj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
