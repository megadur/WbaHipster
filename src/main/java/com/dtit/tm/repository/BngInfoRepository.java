package com.dtit.tm.repository;

import com.dtit.tm.domain.BngInfo;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the BngInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BngInfoRepository extends JpaRepository<BngInfo, Long> {
}
