package com.dtit.tm.service;

import com.dtit.tm.domain.LineInfo;
import com.dtit.tm.repository.LineInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link LineInfo}.
 */
@Service
@Transactional
public class LineInfoService {

    private final Logger log = LoggerFactory.getLogger(LineInfoService.class);

    private final LineInfoRepository lineInfoRepository;

    public LineInfoService(LineInfoRepository lineInfoRepository) {
        this.lineInfoRepository = lineInfoRepository;
    }

    /**
     * Save a lineInfo.
     *
     * @param lineInfo the entity to save.
     * @return the persisted entity.
     */
    public LineInfo save(LineInfo lineInfo) {
        log.debug("Request to save LineInfo : {}", lineInfo);
        return lineInfoRepository.save(lineInfo);
    }

    /**
     * Get all the lineInfos.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<LineInfo> findAll() {
        log.debug("Request to get all LineInfos");
        return lineInfoRepository.findAll();
    }


    /**
     * Get one lineInfo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<LineInfo> findOne(Long id) {
        log.debug("Request to get LineInfo : {}", id);
        return lineInfoRepository.findById(id);
    }

    /**
     * Delete the lineInfo by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete LineInfo : {}", id);
        lineInfoRepository.deleteById(id);
    }
}
