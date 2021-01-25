package com.dtit.tm.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A BngInfo.
 */
@Entity
@Table(name = "bng_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BngInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "end_sz")
    private String endSz;

    @Column(name = "line_id")
    private String lineId;

    @Column(name = "port_name")
    private String portName;

    @Column(name = "ts_last_query")
    private String tsLastQuery;

    @Column(name = "ts_last_query_success")
    private String tsLastQuerySuccess;

    @OneToMany(mappedBy = "bngInfo")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<LineInfo> lineInfos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEndSz() {
        return endSz;
    }

    public BngInfo endSz(String endSz) {
        this.endSz = endSz;
        return this;
    }

    public void setEndSz(String endSz) {
        this.endSz = endSz;
    }

    public String getLineId() {
        return lineId;
    }

    public BngInfo lineId(String lineId) {
        this.lineId = lineId;
        return this;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public String getPortName() {
        return portName;
    }

    public BngInfo portName(String portName) {
        this.portName = portName;
        return this;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public String getTsLastQuery() {
        return tsLastQuery;
    }

    public BngInfo tsLastQuery(String tsLastQuery) {
        this.tsLastQuery = tsLastQuery;
        return this;
    }

    public void setTsLastQuery(String tsLastQuery) {
        this.tsLastQuery = tsLastQuery;
    }

    public String getTsLastQuerySuccess() {
        return tsLastQuerySuccess;
    }

    public BngInfo tsLastQuerySuccess(String tsLastQuerySuccess) {
        this.tsLastQuerySuccess = tsLastQuerySuccess;
        return this;
    }

    public void setTsLastQuerySuccess(String tsLastQuerySuccess) {
        this.tsLastQuerySuccess = tsLastQuerySuccess;
    }

    public Set<LineInfo> getLineInfos() {
        return lineInfos;
    }

    public BngInfo lineInfos(Set<LineInfo> lineInfos) {
        this.lineInfos = lineInfos;
        return this;
    }

    public BngInfo addLineInfo(LineInfo lineInfo) {
        this.lineInfos.add(lineInfo);
        lineInfo.setBngInfo(this);
        return this;
    }

    public BngInfo removeLineInfo(LineInfo lineInfo) {
        this.lineInfos.remove(lineInfo);
        lineInfo.setBngInfo(null);
        return this;
    }

    public void setLineInfos(Set<LineInfo> lineInfos) {
        this.lineInfos = lineInfos;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BngInfo)) {
            return false;
        }
        return id != null && id.equals(((BngInfo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BngInfo{" +
            "id=" + getId() +
            ", endSz='" + getEndSz() + "'" +
            ", lineId='" + getLineId() + "'" +
            ", portName='" + getPortName() + "'" +
            ", tsLastQuery='" + getTsLastQuery() + "'" +
            ", tsLastQuerySuccess='" + getTsLastQuerySuccess() + "'" +
            "}";
    }
}
