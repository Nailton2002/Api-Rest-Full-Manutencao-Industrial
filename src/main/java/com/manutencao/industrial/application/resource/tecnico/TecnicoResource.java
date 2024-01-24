package com.manutencao.industrial.application.resource.tecnico;

import com.manutencao.industrial.domain.dto.tecnico.response.TecnicoListRespose;
import com.manutencao.industrial.domain.dto.tecnico.resquest.TecnicoRequest;
import com.manutencao.industrial.domain.dto.tecnico.resquest.TecnicoUpRequest;
import com.manutencao.industrial.domain.dto.tecnico.response.TecnicoResponse;
import com.manutencao.industrial.domain.service.tecnico.TecnicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

    private final TecnicoService service;

    @PostMapping
    public ResponseEntity<TecnicoResponse> create(@Valid @RequestBody TecnicoRequest request) {
        request = new TecnicoRequest(service.create(request));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/tecnicos/{id}").buildAndExpand(request.getId()).toUri();
        return ResponseEntity.created(uri).body(new TecnicoResponse(request));
    }

    @GetMapping
    public ResponseEntity<List<TecnicoListRespose>> findAll() {
        List<TecnicoListRespose> listDto = service.findAll().stream().map(t -> new TecnicoListRespose(t)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping("/porNomes")
    public ResponseEntity<List<TecnicoResponse>> findByNome(@RequestParam(name = "nome") String nome) {
        List<TecnicoResponse> listView = service.findByNome(nome).stream().map(t -> new TecnicoResponse(t)).collect(Collectors.toList());;
        return ResponseEntity.ok().body(listView);
    }

    @GetMapping("/porPaginas")
    public ResponseEntity<Page<TecnicoResponse>> findAllByPage(@PageableDefault(size = 5, sort = {"nome"}) Pageable paginacao) {
        var page = service.findAllByPage(paginacao).map(TecnicoResponse::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoResponse> findById(@PathVariable Integer id) {
        TecnicoResponse view = new TecnicoResponse(service.findById(id));
        return ResponseEntity.ok().body(view);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TecnicoListRespose> update(@PathVariable Integer id, @Valid @RequestBody TecnicoUpRequest upRequest) {
        upRequest = new TecnicoUpRequest(service.update(id, upRequest));
        return ResponseEntity.ok().body(new TecnicoListRespose(upRequest));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
