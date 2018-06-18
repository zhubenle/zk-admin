package cn.zk.entity;

import cn.zk.util.DateUtils;
import org.apache.zookeeper.data.Stat;

import java.util.Date;

/**
 * <br/>
 * Created on 2018/6/18 14:28.
 *
 * @author zhubenle
 */
public class PathDataVO {
    private Long czxId;
    private Long mzxId;
    private Long cTime;
    private String cTimeStr;
    private Long mTime;
    private String mTimeStr;
    private Integer version;
    private Integer cVersion;
    private Integer aVersion;
    private Long ephemeralOwner;
    private Integer dataLength;
    private Integer numChildren;
    private Long pzxId;

    private String data;

    public PathDataVO(Stat stat, String data) {
        this.czxId = stat.getCzxid();
        this.mzxId = stat.getMzxid();
        this.cTime = stat.getCtime();
        if (cTime != 0) {
            this.cTimeStr = DateUtils.format(new Date(cTime), DateUtils.DATETIME_MS_FORMATTER1.get());
        }
        this.mTime = stat.getMtime();
        if (mTime != 0) {
            this.mTimeStr = DateUtils.format(new Date(mTime), DateUtils.DATETIME_MS_FORMATTER1.get());
        }
        this.version = stat.getVersion();
        this.cVersion = stat.getCversion();
        this.aVersion = stat.getAversion();
        this.ephemeralOwner = stat.getEphemeralOwner();
        this.dataLength = stat.getDataLength();
        this.numChildren = stat.getNumChildren();
        this.pzxId = stat.getPzxid();

        this.data = data;
    }

    public String getcTimeStr() {
        return cTimeStr;
    }

    public void setcTimeStr(String cTimeStr) {
        this.cTimeStr = cTimeStr;
    }

    public String getmTimeStr() {
        return mTimeStr;
    }

    public void setmTimeStr(String mTimeStr) {
        this.mTimeStr = mTimeStr;
    }

    public Long getCzxId() {
        return czxId;
    }

    public void setCzxId(Long czxId) {
        this.czxId = czxId;
    }

    public Long getMzxId() {
        return mzxId;
    }

    public void setMzxId(Long mzxId) {
        this.mzxId = mzxId;
    }

    public Long getcTime() {
        return cTime;
    }

    public void setcTime(Long cTime) {
        this.cTime = cTime;
    }

    public Long getmTime() {
        return mTime;
    }

    public void setmTime(Long mTime) {
        this.mTime = mTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getcVersion() {
        return cVersion;
    }

    public void setcVersion(Integer cVersion) {
        this.cVersion = cVersion;
    }

    public Integer getaVersion() {
        return aVersion;
    }

    public void setaVersion(Integer aVersion) {
        this.aVersion = aVersion;
    }

    public Long getEphemeralOwner() {
        return ephemeralOwner;
    }

    public void setEphemeralOwner(Long ephemeralOwner) {
        this.ephemeralOwner = ephemeralOwner;
    }

    public Integer getDataLength() {
        return dataLength;
    }

    public void setDataLength(Integer dataLength) {
        this.dataLength = dataLength;
    }

    public Integer getNumChildren() {
        return numChildren;
    }

    public void setNumChildren(Integer numChildren) {
        this.numChildren = numChildren;
    }

    public Long getPzxId() {
        return pzxId;
    }

    public void setPzxId(Long pzxId) {
        this.pzxId = pzxId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PathDataVO{" +
                "czxId=" + czxId +
                ", mzxId=" + mzxId +
                ", cTime=" + cTime +
                ", mTime=" + mTime +
                ", version=" + version +
                ", cVersion=" + cVersion +
                ", aVersion=" + aVersion +
                ", ephemeralOwner=" + ephemeralOwner +
                ", dataLength=" + dataLength +
                ", numChildren=" + numChildren +
                ", pzxId=" + pzxId +
                ", data='" + data + '\'' +
                '}';
    }
}
