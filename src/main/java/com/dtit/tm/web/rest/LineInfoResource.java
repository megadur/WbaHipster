package com.dtit.tm.web.rest;

import com.dtit.tm.domain.LineInfo;
import com.dtit.tm.repository.LineInfoRepository;
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
 * REST controller for managing {@link com.dtit.tm.domain.LineInfo}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class LineInfoResource {

    private final Logger log = LoggerFactory.getLogger(LineInfoResource.class);

    private static final String ENTITY_NAME = "lineInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LineInfoRepository lineInfoRepository;

    public LineInfoResource(LineInfoRepository lineInfoRepository) {
        this.lineInfoRepository = lineInfoRepository;
    }

    /**
     * {@code POST  /line-infos} : Create a new lineInfo.
     *
     * @param lineInfo the lineInfo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new lineInfo, or with status {@code 400 (Bad Request)} if the lineInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/line-infos")
    public ResponseEntity<LineInfo> createLineInfo(@RequestBody LineInfo lineInfo) throws URISyntaxException {
        log.debug("REST request to save LineInfo : {}", lineInfo);
        if (lineInfo.getId() != null) {
            throw new BadRequestAlertException("A new lineInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LineInfo result = lineInfoRepository.save(lineInfo);
        return ResponseEntity.created(new URI("/api/line-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /line-infos} : Updates an existing lineInfo.
     *
     * @param lineInfo the lineInfo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated lineInfo,
     * or with status {@code 400 (Bad Request)} if the lineInfo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the lineInfo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/line-infos")
    public ResponseEntity<LineInfo> updateLineInfo(@RequestBody LineInfo lineInfo) throws URISyntaxException {
        log.debug("REST request to update LineInfo : {}", lineInfo);
        if (lineInfo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LineInfo result = lineInfoRepository.save(lineInfo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, lineInfo.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /line-infos} : get all the lineInfos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of lineInfos in body.
     */
    @GetMapping("/line-infos")
    public List<LineInfo> getAllLineInfos() {
        log.debug("REST request to get all LineInfos");
        return lineInfoRepository.findAll();
    }

    /**
     * {@code GET  /line-infos/:id} : get the "id" lineInfo.
     *
     * @param id the id of the lineInfo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the lineInfo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/line-infos/{id}")
    public ResponseEntity<LineInfo> getLineInfo(@PathVariable Long id) {
        log.debug("REST request to get LineInfo : {}", id);
        Optional<LineInfo> lineInfo = lineInfoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(lineInfo);
    }

    /**
     * {@code DELETE  /line-infos/:id} : delete the "id" lineInfo.
     *
     * @param id the id of the lineInfo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/line-infos/{id}")
    public ResponseEntity<Void> deleteLineInfo(@PathVariable Long id) {
        log.debug("REST request to delete LineInfo : {}", id);
        lineInfoRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
