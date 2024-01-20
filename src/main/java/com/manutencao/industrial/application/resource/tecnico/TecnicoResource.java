package com.manutencao.industrial.application.resource.tecnico;

import com.manutencao.industrial.domain.dto.tecnico.response.TecnicoListView;
import com.manutencao.industrial.domain.dto.tecnico.resquest.TecnicoForm;
import com.manutencao.industrial.domain.dto.tecnico.resquest.TecnicoUpForm;
import com.manutencao.industrial.domain.dto.tecnico.response.TecnicoView;
import com.manutencao.industrial.domain.service.tecnico.TecnicoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

    @Autowired
    private TecnicoServiceImpl service;

    @Transactional
    @PostMapping
    public ResponseEntity<TecnicoListView> create(@Valid @RequestBody TecnicoForm form) {
        var obj = service.create(form);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/tecnicos/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<TecnicoListView>> findAll() {
        List<TecnicoListView> listViews = service.findAll();
        return ResponseEntity.ok().body(listViews);
    }

    @GetMapping("/porNomes")
    public ResponseEntity<List<TecnicoView>> findByNome(@RequestParam(name = "nome") String nome){
        List<TecnicoView> listView = service.findByNome(nome);
        return ResponseEntity.ok().body(listView);
    }

    @GetMapping("/porPaginas")
    public ResponseEntity<Page<TecnicoView>> findByPage(@PageableDefault(size = 5, sort = {"nome"})Pageable paginacao){
        var page = service.findByPage(paginacao).map(TecnicoView::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoView> findById(@PathVariable Integer id) {
        TecnicoView view = new TecnicoView(service.findById(id));
        return ResponseEntity.ok().body(view);
    }

    @Transactional
    @PutMapping(value = "/{id}")
    public ResponseEntity<TecnicoUpForm> update(@PathVariable Integer id, @Valid @RequestBody TecnicoUpForm upForm) {
        var newUpForm = new TecnicoUpForm(service.update(id, upForm));
        return ResponseEntity.ok().body(newUpForm);
    }

    @Transactional
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
