package com.vsvdev.controller;

import com.vsvdev.dto.FooDto;
import com.vsvdev.dto.ResponseMessage;
import com.vsvdev.model.Foo;
import com.vsvdev.repo.FooRepository;

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
@RequestMapping("/foo")
@CrossOrigin
public class FooController {
  private final FooRepository repo;

  @Autowired
  public FooController(FooRepository repo) {
    this.repo = repo;
  }


  @GetMapping("/list")
  @RolesAllowed("backend-user")
  public ResponseEntity<List<Foo>> list(Principal principal) {
    String pr = principal.getName();
    List<Foo> from = repo.findAll().stream().filter(foo -> foo.getUserName().contains(pr)).collect(Collectors.toList());
    if (from.size() > 0) {
      return new ResponseEntity(from, HttpStatus.OK);
    }
    return new ResponseEntity(new ArrayList<Foo>(), HttpStatus.OK);
  }


  @RolesAllowed("backend-user")
  @GetMapping("/detail/{id}")
  public ResponseEntity<Foo> detail(@PathVariable("id") int id) {

    if (repo.existsById(id)) {
      Foo foo = repo.findById(id).get();
      Foo retFoo = new Foo(foo.getId(), foo.getName());
      return new ResponseEntity(retFoo, HttpStatus.OK);
    }

    return new ResponseEntity(new Foo(), HttpStatus.OK);
  }

  @RolesAllowed("backend-admin")
  @PostMapping("/create")
  public ResponseEntity<?> create(@RequestBody Foo foo, Principal principal) {
       String userName = principal.getName();
    Foo foo1 = new Foo(foo.getName(), Collections.singletonList(userName));
    repo.save(foo1);
    return new ResponseEntity(new ResponseMessage("created"), HttpStatus.CREATED);
  }

  @RolesAllowed("backend-admin")
  @PutMapping("/update/{id}")
  public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody FooDto foo) {

    Foo toUpd = repo.findById(id).orElse(null);
    if (toUpd != null) {
      if (foo.getName().length() > 0) toUpd.setName(foo.getName());
      if (foo.getUserName().length() > 0) toUpd.getUserName().add(foo.getUserName());
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
