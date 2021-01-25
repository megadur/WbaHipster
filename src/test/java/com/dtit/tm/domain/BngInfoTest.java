package com.dtit.tm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.dtit.tm.web.rest.TestUtil;

public class BngInfoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BngInfo.class);
        BngInfo bngInfo1 = new BngInfo();
        bngInfo1.setId(1L);
        BngInfo bngInfo2 = new BngInfo();
        bngInfo2.setId(bngInfo1.getId());
        assertThat(bngInfo1).isEqualTo(bngInfo2);
        bngInfo2.setId(2L);
        assertThat(bngInfo1).isNotEqualTo(bngInfo2);
        bngInfo1.setId(null);
        assertThat(bngInfo1).isNotEqualTo(bngInfo2);
    }
}
