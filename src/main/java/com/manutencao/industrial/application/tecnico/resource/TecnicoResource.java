package com.manutencao.industrial.application.tecnico.resource;

import com.manutencao.industrial.application.tecnico.dto.form.TecnicoForm;
import com.manutencao.industrial.application.tecnico.dto.form.TecnicoUpForm;
import com.manutencao.industrial.application.tecnico.dto.view.TecnicoListView;
import com.manutencao.industrial.application.tecnico.dto.view.TecnicoView;
import com.manutencao.industrial.domain.tecnico.model.Tecnico;
import com.manutencao.industrial.domain.tecnico.service.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

    @Autowired
    private TecnicoService service;

    @Transactional
    @PostMapping
    public ResponseEntity<TecnicoForm> create(@Valid @RequestBody TecnicoForm form) {
        var obj = service.create(form);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/tecnicos/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<TecnicoListView>> findAll() {
        List<TecnicoListView> listViews = service.findAll().stream().map(obj -> new TecnicoListView(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listViews);
    }

    @GetMapping("/porNomes")
    public ResponseEntity<List<TecnicoView>> findByNome(@RequestParam(name = "nome") String nome){
        List<TecnicoView> listView = service.findByNome(nome);
        return ResponseEntity.ok().body(listView);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoView> findById(@PathVariable Integer id) {
        TecnicoView view = new TecnicoView(service.findById(id));
        return ResponseEntity.ok().body(view);
    }

    @Transactional
    @PutMapping(value = "/{id}")
    public ResponseEntity<TecnicoUpForm> update(@PathVariable Integer id, @Valid @RequestBody TecnicoUpForm upForm) {
        TecnicoUpForm newUpForm = new TecnicoUpForm(service.update(id, upForm));
        return ResponseEntity.ok().body(newUpForm);
    }

    @Transactional
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
