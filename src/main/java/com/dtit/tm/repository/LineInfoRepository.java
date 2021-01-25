package com.dtit.tm.repository;

import com.dtit.tm.domain.LineInfo;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the LineInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LineInfoRepository extends JpaRepository<LineInfo, Long> {
}
