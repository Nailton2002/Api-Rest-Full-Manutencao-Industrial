package com.manutencao.industrial.application.resource.tecnico;

import com.manutencao.industrial.domain.dto.operador.response.OperadorResponse;
import com.manutencao.industrial.domain.dto.tecnico.response.TecnicoListRespose;
import com.manutencao.industrial.domain.dto.tecnico.resquest.TecnicoRequest;
import com.manutencao.industrial.domain.dto.tecnico.resquest.TecnicoUpRequest;
import com.manutencao.industrial.domain.dto.tecnico.response.TecnicoResponse;
import com.manutencao.industrial.domain.entity.tecnico.Tecnico;
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
        Tecnico obj = service.create(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/tecnicos/{id}").buildAndExpand(request.getId()).toUri();
        return ResponseEntity.created(uri).body(new TecnicoResponse(obj));
    }

    @GetMapping
    public ResponseEntity<List<TecnicoListRespose>> findAll() {
        List<TecnicoListRespose> listResponse = service.findAll().stream().map(t -> new TecnicoListRespose(t)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listResponse);
    }

    @GetMapping("/porNomes")
    public ResponseEntity<List<TecnicoResponse>> findByNome(@RequestParam(name = "nome") String nome) {
        List<TecnicoResponse> listResponse = service.findByNome(nome).stream().map(t -> new TecnicoResponse(t)).collect(Collectors.toList());;
        return ResponseEntity.ok().body(listResponse);
    }

    @GetMapping("/porCpf")
    public ResponseEntity<List<TecnicoResponse>> findByCpf(@RequestParam(name = "cpf") String cpf){
        List<TecnicoResponse> listByCpf = service.findByCpf(cpf).stream().map(t-> new TecnicoResponse(t)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listByCpf);
    }

    @GetMapping("/porPaginas")
    public ResponseEntity<Page<TecnicoResponse>> findAllByPage(@PageableDefault(size = 5, sort = {"nome"}) Pageable paginacao) {
        Page<TecnicoResponse> pageResponse = service.findAllByPage(paginacao).map(TecnicoResponse::new);
        return ResponseEntity.ok(pageResponse);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoResponse> findById(@PathVariable Integer id) {
        Tecnico objId = service.findById(id);
        return ResponseEntity.ok().body(new TecnicoResponse(objId));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TecnicoListRespose> update(@PathVariable Integer id, @RequestBody TecnicoUpRequest upRequest) {
        Tecnico ïdUpRequest = service.update(id, upRequest);
        return ResponseEntity.ok().body(new TecnicoListRespose(ïdUpRequest));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
