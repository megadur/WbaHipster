package com.dtit.tm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.dtit.tm.web.rest.TestUtil;

public class LineInfoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LineInfo.class);
        LineInfo lineInfo1 = new LineInfo();
        lineInfo1.setId(1L);
        LineInfo lineInfo2 = new LineInfo();
        lineInfo2.setId(lineInfo1.getId());
        assertThat(lineInfo1).isEqualTo(lineInfo2);
        lineInfo2.setId(2L);
        assertThat(lineInfo1).isNotEqualTo(lineInfo2);
        lineInfo1.setId(null);
        assertThat(lineInfo1).isNotEqualTo(lineInfo2);
    }
}
