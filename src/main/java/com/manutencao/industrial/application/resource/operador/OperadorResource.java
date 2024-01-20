package com.manutencao.industrial.application.resource.operador;

import com.manutencao.industrial.domain.dto.operador.resquest.OperadorForm;
import com.manutencao.industrial.domain.dto.operador.resquest.OperadorUpForm;
import com.manutencao.industrial.domain.dto.operador.response.OperadorListView;
import com.manutencao.industrial.domain.dto.operador.response.OperadorView;
import com.manutencao.industrial.domain.service.operador.OperadorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/operadores")
public class OperadorResource {

    @Autowired
    private OperadorServiceImpl service;

    @Transactional
    @PostMapping
    public ResponseEntity salvar(@RequestBody @Valid OperadorForm dados, UriComponentsBuilder uriBuilder){
        var obj = service.create(dados);
        var uri = uriBuilder.path("/operadores/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(new OperadorView(obj));
    }

    @GetMapping
    public ResponseEntity<List<OperadorListView>> listar(){
        List<OperadorListView> listViews = service.findAll();
        return ResponseEntity.ok().body(listViews);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OperadorView> findById(@PathVariable Integer id) {
        var view = service.findById(id);
        return ResponseEntity.ok().body(new OperadorView(view));
    }

    @Transactional
    @PutMapping(value = "/{id}")
    public ResponseEntity<OperadorUpForm> update(@PathVariable Integer id, @Valid @RequestBody OperadorUpForm upForm) {
        OperadorUpForm newUpForm = new OperadorUpForm(service.update(id, upForm));
        return ResponseEntity.ok().body(newUpForm);
    }

    @Transactional
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
