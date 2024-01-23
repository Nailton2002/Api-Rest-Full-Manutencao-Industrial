package com.manutencao.industrial.application.resource.tecnico;

import com.manutencao.industrial.domain.dto.operador.response.OperadorListView;
import com.manutencao.industrial.domain.dto.tecnico.response.TecnicoListView;
import com.manutencao.industrial.domain.dto.tecnico.resquest.TecnicoForm;
import com.manutencao.industrial.domain.dto.tecnico.resquest.TecnicoUpForm;
import com.manutencao.industrial.domain.dto.tecnico.response.TecnicoView;
import com.manutencao.industrial.domain.entity.tecnico.Tecnico;
import com.manutencao.industrial.domain.service.tecnico.TecnicoService;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

    @Autowired
    private TecnicoService service;


    @PostMapping
    public ResponseEntity<TecnicoView> create(@Valid @RequestBody TecnicoForm request) {
        request = new TecnicoForm(service.create(request));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/tecnicos/{id}").buildAndExpand(request.getId()).toUri();
        return ResponseEntity.created(uri).body(new TecnicoView(request));
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<TecnicoListView> update(@PathVariable Integer id, @Valid @RequestBody TecnicoUpForm upForm) {
        upForm = new TecnicoUpForm(service.update(id, upForm));
        return ResponseEntity.ok().body(new TecnicoListView(upForm));
    }

    @GetMapping
    public ResponseEntity<List<TecnicoListView>> findAll() {
        List<TecnicoListView> listDto = service.findAll().stream().map(obj -> new TecnicoListView(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping("/porNomes")
    public ResponseEntity<List<TecnicoView>> findByNome(@RequestParam(name = "nome") String nome) {
        List<TecnicoView> listView = service.findByNome(nome).stream().map(t -> new TecnicoView(t)).collect(Collectors.toList());;
        return ResponseEntity.ok().body(listView);
    }

    @GetMapping("/porPaginas")
    public ResponseEntity<Page<TecnicoView>> findByPage(@PageableDefault(size = 5, sort = {"nome"}) Pageable paginacao) {
        var page = service.findByPage(paginacao).map(TecnicoView::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoView> findById(@PathVariable Integer id) {
        TecnicoView view = new TecnicoView(service.findById(id));
        return ResponseEntity.ok().body(view);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
