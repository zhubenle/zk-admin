package cn.zk.entity;

import cn.zk.util.DateUtils;
import lombok.Data;
import org.apache.zookeeper.data.Stat;

import java.util.Date;

/**
 * <br/>
 * Created on 2018/6/18 14:28.
 *
 * @author zhubenle
 */
@Data
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
        this.cTimeStr = "";
        if (cTime != 0) {
            this.cTimeStr = DateUtils.format(new Date(cTime), DateUtils.DATETIME_MS_FORMATTER1.get());
        }
        this.mTime = stat.getMtime();
        this.mTimeStr = "";
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
}
