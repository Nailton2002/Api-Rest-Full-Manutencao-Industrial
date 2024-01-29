package com.manutencao.industrial.application.resource.operador;

import com.manutencao.industrial.domain.dto.operador.response.OperadorListResponse;
import com.manutencao.industrial.domain.dto.operador.response.OperadorResponse;
import com.manutencao.industrial.domain.dto.operador.resquest.OperadorRequest;
import com.manutencao.industrial.domain.dto.operador.resquest.OperadorUpRequest;
import com.manutencao.industrial.domain.dto.tecnico.response.TecnicoResponse;
import com.manutencao.industrial.domain.entity.operador.Operador;
import com.manutencao.industrial.domain.service.operador.OperadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/operadores")
public class OperadorResource {

    private final OperadorService service;

    @PostMapping
    public ResponseEntity<OperadorResponse> create(@Valid @RequestBody  OperadorRequest request){
        Operador obj = service.create(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/operadoes/{id}").buildAndExpand(request.getId()).toUri();
        return ResponseEntity.created(uri).body(new OperadorResponse(obj));
    }

    @GetMapping
    public ResponseEntity<List<OperadorListResponse>> findAll(){
        List<OperadorListResponse> listResponse = service.findAll().stream().map(o -> new OperadorListResponse(o)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listResponse);
    }

    @GetMapping("/porNomes")
    public ResponseEntity<List<OperadorResponse>> findByNome(@RequestParam(name = "nome") String nome) {
        List<OperadorResponse> listResponse = service.findByNome(nome).stream().map(o -> new OperadorResponse(o)).collect(Collectors.toList());;
        return ResponseEntity.ok().body(listResponse);
    }

    @GetMapping("porCpf")
    public ResponseEntity<List<OperadorResponse>> findByCPF(@RequestParam(name = "cpf") String cpf){
        List<OperadorResponse> listbyCPF = service.findByCPF(cpf).stream().map(o -> new OperadorResponse(o)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listbyCPF);
    }
    @GetMapping("/porPaginas")
    public ResponseEntity<Page<OperadorResponse>> findAllByPage(@PageableDefault(size = 5, sort = {"nome"}) Pageable paginacao) {
        Page<OperadorResponse> responsePage = service.findAllByPage(paginacao).map(OperadorResponse::new);
        return ResponseEntity.ok(responsePage);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<OperadorResponse> findById(@PathVariable Integer id) {
        Operador objId = service.findById(id);
        return ResponseEntity.ok().body(new OperadorResponse(objId));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<OperadorListResponse> update(@PathVariable Integer id, @Valid @RequestBody OperadorUpRequest upRequest) {
        Operador idUpRequest = service.update(id, upRequest);
        return ResponseEntity.ok().body(new OperadorListResponse(idUpRequest));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
