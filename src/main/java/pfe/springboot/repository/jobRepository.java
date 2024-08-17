package pfe.springboot.repository;

import pfe.springboot.entities.Job;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface jobRepository extends JpaRepository<Job, Long> {

}
