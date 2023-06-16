package com.manutencao.industrial.application.operador.resource;

import com.manutencao.industrial.application.operador.dto.form.OperadorForm;
import com.manutencao.industrial.application.operador.dto.form.OperadorUpForm;
import com.manutencao.industrial.application.operador.dto.view.OperadorListView;
import com.manutencao.industrial.application.operador.dto.view.OperadorView;
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
    public ResponseEntity<OperadorView> findById(@PathVariable Integer id) {
        OperadorView view = new OperadorView(service.findById(id));
        return ResponseEntity.ok().body(view);
    }

    @GetMapping
    public ResponseEntity<List<OperadorListView>> findAll() {
        List<OperadorListView> listViews = service.findAll().stream().map(o -> new OperadorListView(o))
        .collect(Collectors.toList());
        return ResponseEntity.ok().body(listViews);
    }

    @PostMapping
    public ResponseEntity<OperadorForm> create(@Valid @RequestBody OperadorForm form) {
        var obj = service.create(form);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<OperadorUpForm> update(@PathVariable Integer id, @Valid @RequestBody OperadorUpForm upForm) {
        OperadorUpForm newUpForm = new OperadorUpForm(service.update(id, upForm));
        return ResponseEntity.ok().body(newUpForm);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
