package com.vsvdev.controller;

import com.vsvdev.dto.ResponseMessage;
import com.vsvdev.model.Endpoint;
import com.vsvdev.model.EndpointDto;
import com.vsvdev.repo.EndpointRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/endpoint")
@CrossOrigin
public class EndpointController {

  private final EndpointRepo repo;

  @Autowired
  public EndpointController(EndpointRepo repo) {
    this.repo = repo;
  }

  @GetMapping("/list")
  @RolesAllowed("backend-user")
  public ResponseEntity<List<Endpoint>> list(Principal principal) {
    String pr = principal.getName();
    List<Endpoint> from = repo.findAll().stream().filter(foo -> foo.getUserName().contains(pr)).collect(Collectors.toList());
    if (from.size() > 0) {
      return new ResponseEntity(from, HttpStatus.OK);
    }
    return new ResponseEntity(new ArrayList<Endpoint>(), HttpStatus.OK);
  }

  @RolesAllowed("backend-user")
  @GetMapping("/detail/{id}")
  public ResponseEntity<Endpoint> detail(@PathVariable("id") int id) {
    if (repo.existsById(id)) {
      Endpoint endpoint = repo.findById(id).get();
      Endpoint reEndpoint = new Endpoint(endpoint.getId(), endpoint.getName());
      return new ResponseEntity(reEndpoint, HttpStatus.OK);
    }

    return new ResponseEntity(new Endpoint(), HttpStatus.OK);
  }

  @RolesAllowed("backend-admin")
  @PostMapping("/create")
  public ResponseEntity<?> create(@RequestBody Endpoint endpoint, Principal principal) {
    String userName = principal.getName();
    Endpoint endpoint1 = new Endpoint(endpoint.getName(), Collections.singletonList(userName));
    repo.save(endpoint1);
    return new ResponseEntity(new ResponseMessage("created"), HttpStatus.CREATED);
  }

  @RolesAllowed("backend-admin")
  @PutMapping("/update/{id}")
  public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody EndpointDto endpointDto, Principal principal) {
    Endpoint toUpd = repo.findById(id).orElse(null);
    if (toUpd != null) {
      if (endpointDto.getName().length() > 0) toUpd.setName(endpointDto.getName());
      if (endpointDto.getUserName().length() > 0)
        toUpd.getUserName().add(endpointDto.getUserName());
      repo.save(toUpd);
      return new ResponseEntity(new ResponseMessage("updated"), HttpStatus.OK);
    }

    return new ResponseEntity(new ResponseMessage("not found"), HttpStatus.OK);
  }

  @RolesAllowed("backend-admin")
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> delete(@PathVariable("id") int id) {
    if (repo.existsById(id)) {
      repo.deleteById(id);
      return new ResponseEntity(new ResponseMessage("deleted"), HttpStatus.OK);
    }
    return new ResponseEntity(new ResponseMessage("not found"), HttpStatus.OK);
  }
}
