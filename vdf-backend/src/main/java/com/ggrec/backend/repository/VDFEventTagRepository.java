package com.ggrec.backend.repository;

import com.ggrec.backend.domain.VDFEventTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VDFEventTagRepository extends JpaRepository<VDFEventTag, Long> {
}
