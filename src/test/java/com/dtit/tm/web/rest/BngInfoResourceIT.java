package com.dtit.tm.web.rest;

import com.dtit.tm.WbaHipsterApp;
import com.dtit.tm.config.TestSecurityConfiguration;
import com.dtit.tm.domain.BngInfo;
import com.dtit.tm.repository.BngInfoRepository;

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
 * Integration tests for the {@link BngInfoResource} REST controller.
 */
@SpringBootTest(classes = { WbaHipsterApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class BngInfoResourceIT {

    private static final String DEFAULT_END_SZ = "AAAAAAAAAA";
    private static final String UPDATED_END_SZ = "BBBBBBBBBB";

    private static final String DEFAULT_LINE_ID = "AAAAAAAAAA";
    private static final String UPDATED_LINE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PORT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PORT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TS_LAST_QUERY = "AAAAAAAAAA";
    private static final String UPDATED_TS_LAST_QUERY = "BBBBBBBBBB";

    private static final String DEFAULT_TS_LAST_QUERY_SUCCESS = "AAAAAAAAAA";
    private static final String UPDATED_TS_LAST_QUERY_SUCCESS = "BBBBBBBBBB";

    @Autowired
    private BngInfoRepository bngInfoRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBngInfoMockMvc;

    private BngInfo bngInfo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BngInfo createEntity(EntityManager em) {
        BngInfo bngInfo = new BngInfo()
            .endSz(DEFAULT_END_SZ)
            .lineId(DEFAULT_LINE_ID)
            .portName(DEFAULT_PORT_NAME)
            .tsLastQuery(DEFAULT_TS_LAST_QUERY)
            .tsLastQuerySuccess(DEFAULT_TS_LAST_QUERY_SUCCESS);
        return bngInfo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BngInfo createUpdatedEntity(EntityManager em) {
        BngInfo bngInfo = new BngInfo()
            .endSz(UPDATED_END_SZ)
            .lineId(UPDATED_LINE_ID)
            .portName(UPDATED_PORT_NAME)
            .tsLastQuery(UPDATED_TS_LAST_QUERY)
            .tsLastQuerySuccess(UPDATED_TS_LAST_QUERY_SUCCESS);
        return bngInfo;
    }

    @BeforeEach
    public void initTest() {
        bngInfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createBngInfo() throws Exception {
        int databaseSizeBeforeCreate = bngInfoRepository.findAll().size();
        // Create the BngInfo
        restBngInfoMockMvc.perform(post("/api/bng-infos").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bngInfo)))
            .andExpect(status().isCreated());

        // Validate the BngInfo in the database
        List<BngInfo> bngInfoList = bngInfoRepository.findAll();
        assertThat(bngInfoList).hasSize(databaseSizeBeforeCreate + 1);
        BngInfo testBngInfo = bngInfoList.get(bngInfoList.size() - 1);
        assertThat(testBngInfo.getEndSz()).isEqualTo(DEFAULT_END_SZ);
        assertThat(testBngInfo.getLineId()).isEqualTo(DEFAULT_LINE_ID);
        assertThat(testBngInfo.getPortName()).isEqualTo(DEFAULT_PORT_NAME);
        assertThat(testBngInfo.getTsLastQuery()).isEqualTo(DEFAULT_TS_LAST_QUERY);
        assertThat(testBngInfo.getTsLastQuerySuccess()).isEqualTo(DEFAULT_TS_LAST_QUERY_SUCCESS);
    }

    @Test
    @Transactional
    public void createBngInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bngInfoRepository.findAll().size();

        // Create the BngInfo with an existing ID
        bngInfo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBngInfoMockMvc.perform(post("/api/bng-infos").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bngInfo)))
            .andExpect(status().isBadRequest());

        // Validate the BngInfo in the database
        List<BngInfo> bngInfoList = bngInfoRepository.findAll();
        assertThat(bngInfoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBngInfos() throws Exception {
        // Initialize the database
        bngInfoRepository.saveAndFlush(bngInfo);

        // Get all the bngInfoList
        restBngInfoMockMvc.perform(get("/api/bng-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bngInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].endSz").value(hasItem(DEFAULT_END_SZ)))
            .andExpect(jsonPath("$.[*].lineId").value(hasItem(DEFAULT_LINE_ID)))
            .andExpect(jsonPath("$.[*].portName").value(hasItem(DEFAULT_PORT_NAME)))
            .andExpect(jsonPath("$.[*].tsLastQuery").value(hasItem(DEFAULT_TS_LAST_QUERY)))
            .andExpect(jsonPath("$.[*].tsLastQuerySuccess").value(hasItem(DEFAULT_TS_LAST_QUERY_SUCCESS)));
    }
    
    @Test
    @Transactional
    public void getBngInfo() throws Exception {
        // Initialize the database
        bngInfoRepository.saveAndFlush(bngInfo);

        // Get the bngInfo
        restBngInfoMockMvc.perform(get("/api/bng-infos/{id}", bngInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bngInfo.getId().intValue()))
            .andExpect(jsonPath("$.endSz").value(DEFAULT_END_SZ))
            .andExpect(jsonPath("$.lineId").value(DEFAULT_LINE_ID))
            .andExpect(jsonPath("$.portName").value(DEFAULT_PORT_NAME))
            .andExpect(jsonPath("$.tsLastQuery").value(DEFAULT_TS_LAST_QUERY))
            .andExpect(jsonPath("$.tsLastQuerySuccess").value(DEFAULT_TS_LAST_QUERY_SUCCESS));
    }
    @Test
    @Transactional
    public void getNonExistingBngInfo() throws Exception {
        // Get the bngInfo
        restBngInfoMockMvc.perform(get("/api/bng-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBngInfo() throws Exception {
        // Initialize the database
        bngInfoRepository.saveAndFlush(bngInfo);

        int databaseSizeBeforeUpdate = bngInfoRepository.findAll().size();

        // Update the bngInfo
        BngInfo updatedBngInfo = bngInfoRepository.findById(bngInfo.getId()).get();
        // Disconnect from session so that the updates on updatedBngInfo are not directly saved in db
        em.detach(updatedBngInfo);
        updatedBngInfo
            .endSz(UPDATED_END_SZ)
            .lineId(UPDATED_LINE_ID)
            .portName(UPDATED_PORT_NAME)
            .tsLastQuery(UPDATED_TS_LAST_QUERY)
            .tsLastQuerySuccess(UPDATED_TS_LAST_QUERY_SUCCESS);

        restBngInfoMockMvc.perform(put("/api/bng-infos").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedBngInfo)))
            .andExpect(status().isOk());

        // Validate the BngInfo in the database
        List<BngInfo> bngInfoList = bngInfoRepository.findAll();
        assertThat(bngInfoList).hasSize(databaseSizeBeforeUpdate);
        BngInfo testBngInfo = bngInfoList.get(bngInfoList.size() - 1);
        assertThat(testBngInfo.getEndSz()).isEqualTo(UPDATED_END_SZ);
        assertThat(testBngInfo.getLineId()).isEqualTo(UPDATED_LINE_ID);
        assertThat(testBngInfo.getPortName()).isEqualTo(UPDATED_PORT_NAME);
        assertThat(testBngInfo.getTsLastQuery()).isEqualTo(UPDATED_TS_LAST_QUERY);
        assertThat(testBngInfo.getTsLastQuerySuccess()).isEqualTo(UPDATED_TS_LAST_QUERY_SUCCESS);
    }

    @Test
    @Transactional
    public void updateNonExistingBngInfo() throws Exception {
        int databaseSizeBeforeUpdate = bngInfoRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBngInfoMockMvc.perform(put("/api/bng-infos").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bngInfo)))
            .andExpect(status().isBadRequest());

        // Validate the BngInfo in the database
        List<BngInfo> bngInfoList = bngInfoRepository.findAll();
        assertThat(bngInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBngInfo() throws Exception {
        // Initialize the database
        bngInfoRepository.saveAndFlush(bngInfo);

        int databaseSizeBeforeDelete = bngInfoRepository.findAll().size();

        // Delete the bngInfo
        restBngInfoMockMvc.perform(delete("/api/bng-infos/{id}", bngInfo.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BngInfo> bngInfoList = bngInfoRepository.findAll();
        assertThat(bngInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
