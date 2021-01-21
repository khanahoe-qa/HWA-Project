package com.qa.hwa.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.hwa.persistence.domain.Rider;

@Repository
public interface RiderRepo extends JpaRepository<Rider, Long> {

}
