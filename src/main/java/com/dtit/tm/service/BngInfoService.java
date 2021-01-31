package com.dtit.tm.service;

import com.dtit.tm.domain.BngInfo;
import com.dtit.tm.repository.BngInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link BngInfo}.
 */
@Service
@Transactional
public class BngInfoService {

    private final Logger log = LoggerFactory.getLogger(BngInfoService.class);

    private final BngInfoRepository bngInfoRepository;

    public BngInfoService(BngInfoRepository bngInfoRepository) {
        this.bngInfoRepository = bngInfoRepository;
    }

    /**
     * Save a bngInfo.
     *
     * @param bngInfo the entity to save.
     * @return the persisted entity.
     */
    public BngInfo save(BngInfo bngInfo) {
        log.debug("Request to save BngInfo : {}", bngInfo);
        return bngInfoRepository.save(bngInfo);
    }

    /**
     * Get all the bngInfos.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BngInfo> findAll() {
        log.debug("Request to get all BngInfos");
        return bngInfoRepository.findAll();
    }


    /**
     * Get one bngInfo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BngInfo> findOne(Long id) {
        log.debug("Request to get BngInfo : {}", id);
        return bngInfoRepository.findById(id);
    }

    /**
     * Delete the bngInfo by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete BngInfo : {}", id);
        bngInfoRepository.deleteById(id);
    }
}
