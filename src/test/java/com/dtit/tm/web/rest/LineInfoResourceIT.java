package com.dtit.tm.web.rest;

import com.dtit.tm.WbaHipsterApp;
import com.dtit.tm.config.TestSecurityConfiguration;
import com.dtit.tm.domain.LineInfo;
import com.dtit.tm.repository.LineInfoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link LineInfoResource} REST controller.
 */
@SpringBootTest(classes = { WbaHipsterApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class LineInfoResourceIT {

    private static final String DEFAULT_LINE_ID = "AAAAAAAAAA";
    private static final String UPDATED_LINE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_UPLINK_PORT = "AAAAAAAAAA";
    private static final String UPDATED_UPLINK_PORT = "BBBBBBBBBB";

    private static final String DEFAULT_IP_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_IP_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_TS_LAST_QUERY = "AAAAAAAAAA";
    private static final String UPDATED_TS_LAST_QUERY = "BBBBBBBBBB";

    private static final String DEFAULT_TS_LAST_QUERY_SUCCESS = "AAAAAAAAAA";
    private static final String UPDATED_TS_LAST_QUERY_SUCCESS = "BBBBBBBBBB";

    @Autowired
    private LineInfoRepository lineInfoRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLineInfoMockMvc;

    private LineInfo lineInfo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LineInfo createEntity(EntityManager em) {
        LineInfo lineInfo = new LineInfo()
            .lineId(DEFAULT_LINE_ID)
            .uplinkPort(DEFAULT_UPLINK_PORT)
            .ipAddress(DEFAULT_IP_ADDRESS)
            .tsLastQuery(DEFAULT_TS_LAST_QUERY)
            .tsLastQuerySuccess(DEFAULT_TS_LAST_QUERY_SUCCESS);
        return lineInfo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LineInfo createUpdatedEntity(EntityManager em) {
        LineInfo lineInfo = new LineInfo()
            .lineId(UPDATED_LINE_ID)
            .uplinkPort(UPDATED_UPLINK_PORT)
            .ipAddress(UPDATED_IP_ADDRESS)
            .tsLastQuery(UPDATED_TS_LAST_QUERY)
            .tsLastQuerySuccess(UPDATED_TS_LAST_QUERY_SUCCESS);
        return lineInfo;
    }

    @BeforeEach
    public void initTest() {
        lineInfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createLineInfo() throws Exception {
        int databaseSizeBeforeCreate = lineInfoRepository.findAll().size();
        // Create the LineInfo
        restLineInfoMockMvc.perform(post("/api/line-infos").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lineInfo)))
            .andExpect(status().isCreated());

        // Validate the LineInfo in the database
        List<LineInfo> lineInfoList = lineInfoRepository.findAll();
        assertThat(lineInfoList).hasSize(databaseSizeBeforeCreate + 1);
        LineInfo testLineInfo = lineInfoList.get(lineInfoList.size() - 1);
        assertThat(testLineInfo.getLineId()).isEqualTo(DEFAULT_LINE_ID);
        assertThat(testLineInfo.getUplinkPort()).isEqualTo(DEFAULT_UPLINK_PORT);
        assertThat(testLineInfo.getIpAddress()).isEqualTo(DEFAULT_IP_ADDRESS);
        assertThat(testLineInfo.getTsLastQuery()).isEqualTo(DEFAULT_TS_LAST_QUERY);
        assertThat(testLineInfo.getTsLastQuerySuccess()).isEqualTo(DEFAULT_TS_LAST_QUERY_SUCCESS);
    }

    @Test
    @Transactional
    public void createLineInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = lineInfoRepository.findAll().size();

        // Create the LineInfo with an existing ID
        lineInfo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLineInfoMockMvc.perform(post("/api/line-infos").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lineInfo)))
            .andExpect(status().isBadRequest());

        // Validate the LineInfo in the database
        List<LineInfo> lineInfoList = lineInfoRepository.findAll();
        assertThat(lineInfoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllLineInfos() throws Exception {
        // Initialize the database
        lineInfoRepository.saveAndFlush(lineInfo);

        // Get all the lineInfoList
        restLineInfoMockMvc.perform(get("/api/line-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(lineInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].lineId").value(hasItem(DEFAULT_LINE_ID)))
            .andExpect(jsonPath("$.[*].uplinkPort").value(hasItem(DEFAULT_UPLINK_PORT)))
            .andExpect(jsonPath("$.[*].ipAddress").value(hasItem(DEFAULT_IP_ADDRESS)))
            .andExpect(jsonPath("$.[*].tsLastQuery").value(hasItem(DEFAULT_TS_LAST_QUERY)))
            .andExpect(jsonPath("$.[*].tsLastQuerySuccess").value(hasItem(DEFAULT_TS_LAST_QUERY_SUCCESS)));
    }
    
    @Test
    @Transactional
    public void getLineInfo() throws Exception {
        // Initialize the database
        lineInfoRepository.saveAndFlush(lineInfo);

        // Get the lineInfo
        restLineInfoMockMvc.perform(get("/api/line-infos/{id}", lineInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(lineInfo.getId().intValue()))
            .andExpect(jsonPath("$.lineId").value(DEFAULT_LINE_ID))
            .andExpect(jsonPath("$.uplinkPort").value(DEFAULT_UPLINK_PORT))
            .andExpect(jsonPath("$.ipAddress").value(DEFAULT_IP_ADDRESS))
            .andExpect(jsonPath("$.tsLastQuery").value(DEFAULT_TS_LAST_QUERY))
            .andExpect(jsonPath("$.tsLastQuerySuccess").value(DEFAULT_TS_LAST_QUERY_SUCCESS));
    }
    @Test
    @Transactional
    public void getNonExistingLineInfo() throws Exception {
        // Get the lineInfo
        restLineInfoMockMvc.perform(get("/api/line-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLineInfo() throws Exception {
        // Initialize the database
        lineInfoRepository.saveAndFlush(lineInfo);

        int databaseSizeBeforeUpdate = lineInfoRepository.findAll().size();

        // Update the lineInfo
        LineInfo updatedLineInfo = lineInfoRepository.findById(lineInfo.getId()).get();
        // Disconnect from session so that the updates on updatedLineInfo are not directly saved in db
        em.detach(updatedLineInfo);
        updatedLineInfo
            .lineId(UPDATED_LINE_ID)
            .uplinkPort(UPDATED_UPLINK_PORT)
            .ipAddress(UPDATED_IP_ADDRESS)
            .tsLastQuery(UPDATED_TS_LAST_QUERY)
            .tsLastQuerySuccess(UPDATED_TS_LAST_QUERY_SUCCESS);

        restLineInfoMockMvc.perform(put("/api/line-infos").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedLineInfo)))
            .andExpect(status().isOk());

        // Validate the LineInfo in the database
        List<LineInfo> lineInfoList = lineInfoRepository.findAll();
        assertThat(lineInfoList).hasSize(databaseSizeBeforeUpdate);
        LineInfo testLineInfo = lineInfoList.get(lineInfoList.size() - 1);
        assertThat(testLineInfo.getLineId()).isEqualTo(UPDATED_LINE_ID);
        assertThat(testLineInfo.getUplinkPort()).isEqualTo(UPDATED_UPLINK_PORT);
        assertThat(testLineInfo.getIpAddress()).isEqualTo(UPDATED_IP_ADDRESS);
        assertThat(testLineInfo.getTsLastQuery()).isEqualTo(UPDATED_TS_LAST_QUERY);
        assertThat(testLineInfo.getTsLastQuerySuccess()).isEqualTo(UPDATED_TS_LAST_QUERY_SUCCESS);
    }

    @Test
    @Transactional
    public void updateNonExistingLineInfo() throws Exception {
        int databaseSizeBeforeUpdate = lineInfoRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLineInfoMockMvc.perform(put("/api/line-infos").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lineInfo)))
            .andExpect(status().isBadRequest());

        // Validate the LineInfo in the database
        List<LineInfo> lineInfoList = lineInfoRepository.findAll();
        assertThat(lineInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLineInfo() throws Exception {
        // Initialize the database
        lineInfoRepository.saveAndFlush(lineInfo);

        int databaseSizeBeforeDelete = lineInfoRepository.findAll().size();

        // Delete the lineInfo
        restLineInfoMockMvc.perform(delete("/api/line-infos/{id}", lineInfo.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<LineInfo> lineInfoList = lineInfoRepository.findAll();
        assertThat(lineInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
