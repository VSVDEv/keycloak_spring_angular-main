package com.vsvdev.repo;

import com.vsvdev.model.Foo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FooRepo extends JpaRepository<Foo, Integer> {


}
