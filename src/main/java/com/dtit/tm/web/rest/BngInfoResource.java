package com.dtit.tm.web.rest;

import com.dtit.tm.domain.BngInfo;
import com.dtit.tm.repository.BngInfoRepository;
import com.dtit.tm.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.dtit.tm.domain.BngInfo}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class BngInfoResource {

    private final Logger log = LoggerFactory.getLogger(BngInfoResource.class);

    private static final String ENTITY_NAME = "bngInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BngInfoRepository bngInfoRepository;

    public BngInfoResource(BngInfoRepository bngInfoRepository) {
        this.bngInfoRepository = bngInfoRepository;
    }

    /**
     * {@code POST  /bng-infos} : Create a new bngInfo.
     *
     * @param bngInfo the bngInfo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bngInfo, or with status {@code 400 (Bad Request)} if the bngInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bng-infos")
    public ResponseEntity<BngInfo> createBngInfo(@RequestBody BngInfo bngInfo) throws URISyntaxException {
        log.debug("REST request to save BngInfo : {}", bngInfo);
        if (bngInfo.getId() != null) {
            throw new BadRequestAlertException("A new bngInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BngInfo result = bngInfoRepository.save(bngInfo);
        return ResponseEntity.created(new URI("/api/bng-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bng-infos} : Updates an existing bngInfo.
     *
     * @param bngInfo the bngInfo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bngInfo,
     * or with status {@code 400 (Bad Request)} if the bngInfo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bngInfo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bng-infos")
    public ResponseEntity<BngInfo> updateBngInfo(@RequestBody BngInfo bngInfo) throws URISyntaxException {
        log.debug("REST request to update BngInfo : {}", bngInfo);
        if (bngInfo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BngInfo result = bngInfoRepository.save(bngInfo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bngInfo.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /bng-infos} : get all the bngInfos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bngInfos in body.
     */
    @GetMapping("/bng-infos")
    public List<BngInfo> getAllBngInfos() {
        log.debug("REST request to get all BngInfos");
        return bngInfoRepository.findAll();
    }

    /**
     * {@code GET  /bng-infos/:id} : get the "id" bngInfo.
     *
     * @param id the id of the bngInfo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bngInfo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bng-infos/{id}")
    public ResponseEntity<BngInfo> getBngInfo(@PathVariable Long id) {
        log.debug("REST request to get BngInfo : {}", id);
        Optional<BngInfo> bngInfo = bngInfoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(bngInfo);
    }

    /**
     * {@code DELETE  /bng-infos/:id} : delete the "id" bngInfo.
     *
     * @param id the id of the bngInfo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bng-infos/{id}")
    public ResponseEntity<Void> deleteBngInfo(@PathVariable Long id) {
        log.debug("REST request to delete BngInfo : {}", id);
        bngInfoRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
