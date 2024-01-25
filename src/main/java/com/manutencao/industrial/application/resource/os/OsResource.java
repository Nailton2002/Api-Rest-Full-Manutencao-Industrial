package com.manutencao.industrial.application.resource.os;

import com.manutencao.industrial.domain.dto.os.response.OsListResponse;
import com.manutencao.industrial.domain.dto.os.response.OsResponse;
import com.manutencao.industrial.domain.dto.os.resquest.OsRequest;
import com.manutencao.industrial.domain.dto.os.resquest.OsUpRequest;
import com.manutencao.industrial.domain.entity.os.OrdemServico;
import com.manutencao.industrial.domain.service.os.OsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/os")
public class OsResource {

    private final OsService service;

    @PostMapping
    public ResponseEntity<OsListResponse> create(@Valid @RequestBody OsRequest request) {
        OrdemServico obj = service.create(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(request.getId()).toUri();
        return ResponseEntity.created(uri).body(new OsListResponse(obj));
    }

    @GetMapping
    public ResponseEntity<List<OsListResponse>> findAll() {
        List<OsListResponse> listResponses = service.findAll().stream().map(obj -> new OsListResponse(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listResponses);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OsResponse> findById(@PathVariable Integer id) {
        OrdemServico objId = service.findById(id);
        return ResponseEntity.ok().body(new OsResponse(objId));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<OsListResponse> update(@Valid @RequestBody OsUpRequest upRequest) {
        OrdemServico update = service.update(upRequest);
        return ResponseEntity.ok().body(new OsListResponse(update));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/finalizando/os/{id}")
    public ResponseEntity<Void> finalizandoOS(@PathVariable Integer id){
        service.finalizarOS(id);
        return ResponseEntity.noContent().build();
    }

}
