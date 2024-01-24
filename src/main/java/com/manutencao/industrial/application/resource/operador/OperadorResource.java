package com.manutencao.industrial.application.resource.operador;

import com.manutencao.industrial.domain.dto.operador.response.OperadorListResponse;
import com.manutencao.industrial.domain.dto.operador.response.OperadorResponse;
import com.manutencao.industrial.domain.dto.operador.resquest.OperadorRequest;
import com.manutencao.industrial.domain.dto.operador.resquest.OperadorUpRequest;
import com.manutencao.industrial.domain.dto.tecnico.response.TecnicoResponse;
import com.manutencao.industrial.domain.service.operador.OperadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/operadores")
public class OperadorResource {

    private final OperadorService service;

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid OperadorRequest request, UriComponentsBuilder uriBuilder){
        request = new OperadorRequest(service.create(request));
        var uri = uriBuilder.path("/operadores/{id}").buildAndExpand(request.getId()).toUri();
        return ResponseEntity.created(uri).body(new OperadorResponse(request));
    }

    @GetMapping
    public ResponseEntity<List<OperadorListResponse>> findAll(){
        List<OperadorListResponse> listResponse = service.findAll().stream().map(o -> new OperadorListResponse(o)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listResponse);
    }

    @GetMapping("/porNomes")
    public ResponseEntity<List<OperadorResponse>> findByNome(@RequestParam(name = "nome") String nome) {
        List<OperadorResponse> listView = service.findByNome(nome).stream().map(t -> new OperadorResponse(t)).collect(Collectors.toList());;
        return ResponseEntity.ok().body(listView);
    }

    @GetMapping("/porPaginas")
    public ResponseEntity<Page<OperadorResponse>> findAllByPage(@PageableDefault(size = 5, sort = {"nome"}) Pageable paginacao) {
        var page = service.findAllByPage(paginacao).map(OperadorResponse::new);
        return ResponseEntity.ok(page);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<OperadorResponse> findById(@PathVariable Integer id) {
        var response = new OperadorResponse(service.findById(id));
        return ResponseEntity.ok().body(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<OperadorListResponse> update(@PathVariable Integer id, @Valid @RequestBody OperadorUpRequest upRequest) {
        upRequest = new OperadorUpRequest(service.update(id, upRequest));
        return ResponseEntity.ok().body(new OperadorListResponse(upRequest));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
