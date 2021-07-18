package com.vsvdev.repo;

import com.vsvdev.model.Endpoint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EndpointRepo extends JpaRepository<Endpoint, Integer> {
}
