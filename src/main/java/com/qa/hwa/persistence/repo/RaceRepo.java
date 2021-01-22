package com.qa.hwa.persistence.repo;

import com.qa.hwa.persistence.domain.Race;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceRepo extends JpaRepository<Race, Long> {

}
