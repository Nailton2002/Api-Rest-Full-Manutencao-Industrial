package com.manutencao.industrial.application.resource.os;

import com.manutencao.industrial.domain.dto.os.resquest.OsForm;
import com.manutencao.industrial.domain.dto.os.resquest.OsUpForm;
import com.manutencao.industrial.domain.dto.os.response.OsListView;
import com.manutencao.industrial.domain.dto.os.response.OsView;
import com.manutencao.industrial.domain.service.os.OsServiceImpl;
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
@RequestMapping(value = "/os")
public class OsResource {

	@Autowired
	private OsServiceImpl service;

	@Transactional
	@PostMapping
	public ResponseEntity<OsForm> create(@Valid @RequestBody OsForm form) {
		form = new OsForm(service.create(form));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(form.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@Transactional
	@PutMapping(value = "/{id}")
	public ResponseEntity<OsUpForm> update(@Valid @RequestBody OsUpForm upForm) {
		var newUpForm = new OsUpForm(service.update(upForm));
		return ResponseEntity.ok().body(upForm);
	}

	@Transactional
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id ){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<OsView> findById(@PathVariable Integer id) {
		OsView obj = new OsView(service.findById(id));
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping
	public ResponseEntity<List<OsListView>> findAll() {
		List<OsListView> list = service.findAll().stream().map(obj -> new OsListView(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(list);
	}






}
