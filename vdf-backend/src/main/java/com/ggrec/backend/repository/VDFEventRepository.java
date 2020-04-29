package com.ggrec.backend.repository;

import com.ggrec.backend.domain.VDFEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VDFEventRepository extends JpaRepository<VDFEvent, Long> {

}
