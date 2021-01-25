package com.dtit.tm.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A LineInfo.
 */
@Entity
@Table(name = "line_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class LineInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "line_id")
    private String lineId;

    @Column(name = "uplink_port")
    private String uplinkPort;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "ts_last_query")
    private String tsLastQuery;

    @Column(name = "ts_last_query_success")
    private String tsLastQuerySuccess;

    @ManyToOne
    @JsonIgnoreProperties(value = "lineInfos", allowSetters = true)
    private BngInfo bngInfo;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLineId() {
        return lineId;
    }

    public LineInfo lineId(String lineId) {
        this.lineId = lineId;
        return this;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public String getUplinkPort() {
        return uplinkPort;
    }

    public LineInfo uplinkPort(String uplinkPort) {
        this.uplinkPort = uplinkPort;
        return this;
    }

    public void setUplinkPort(String uplinkPort) {
        this.uplinkPort = uplinkPort;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public LineInfo ipAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getTsLastQuery() {
        return tsLastQuery;
    }

    public LineInfo tsLastQuery(String tsLastQuery) {
        this.tsLastQuery = tsLastQuery;
        return this;
    }

    public void setTsLastQuery(String tsLastQuery) {
        this.tsLastQuery = tsLastQuery;
    }

    public String getTsLastQuerySuccess() {
        return tsLastQuerySuccess;
    }

    public LineInfo tsLastQuerySuccess(String tsLastQuerySuccess) {
        this.tsLastQuerySuccess = tsLastQuerySuccess;
        return this;
    }

    public void setTsLastQuerySuccess(String tsLastQuerySuccess) {
        this.tsLastQuerySuccess = tsLastQuerySuccess;
    }

    public BngInfo getBngInfo() {
        return bngInfo;
    }

    public LineInfo bngInfo(BngInfo bngInfo) {
        this.bngInfo = bngInfo;
        return this;
    }

    public void setBngInfo(BngInfo bngInfo) {
        this.bngInfo = bngInfo;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LineInfo)) {
            return false;
        }
        return id != null && id.equals(((LineInfo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LineInfo{" +
            "id=" + getId() +
            ", lineId='" + getLineId() + "'" +
            ", uplinkPort='" + getUplinkPort() + "'" +
            ", ipAddress='" + getIpAddress() + "'" +
            ", tsLastQuery='" + getTsLastQuery() + "'" +
            ", tsLastQuerySuccess='" + getTsLastQuerySuccess() + "'" +
            "}";
    }
}
