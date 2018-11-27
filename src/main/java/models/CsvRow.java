package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="test_case")
public class CsvRow {

    @Id
    @Column(name="index")
    private Integer index;

    @Column(name="ssoid")
    private String ssoid;

    @Column(name = "ts")
    private LocalDateTime ts;

    @Column(name="grp")
    private String grp;

    @Column(name="type")
    private String type;

    @Column(name="subtype")
    private String subtype;

    @Column(name="url")
    private String url;

    @Column(name="orgid")
    private String orgid;

    @Column(name="formid")
    private String formid;

    @Column(name="ltpa")
    private String ltpa;

    @Column(name="sudirresponse")
    private String sudirresponse;

    @Column(name="ymdh")
    private LocalDateTime ymdh;

   public Integer getIndex() { return index; }

   public void setIndex(Integer index) { this.index = index; }

    public String getSsoid() {
        return ssoid;
    }

    public void setSsoid(String ssoid) {
        this.ssoid = ssoid;
    }

    public LocalDateTime getTs() {
        return ts;
    }

    public void setTs(LocalDateTime ts) {
        this.ts = ts;
    }

    public String getGrp() {
        return grp;
    }

    public void setGrp(String grp) {
        this.grp = grp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public String getFormid() {
        return formid;
    }

    public void setFormid(String formid) {
        this.formid = formid;
    }

    public String getLtpa() {
        return ltpa;
    }

    public void setLtpa(String ltpa) {
        this.ltpa = ltpa;
    }

    public String getSudirresponse() {
        return sudirresponse;
    }

    public void setSudirresponse(String sudirresponse) {
        this.sudirresponse = sudirresponse;
    }

    public LocalDateTime getYmdh() {
        return ymdh;
    }

    public void setYmdh(LocalDateTime ymdh) {
        this.ymdh = ymdh;
    }
}