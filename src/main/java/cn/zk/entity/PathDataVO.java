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
    private Long ctime;
    private String ctimeStr;
    private Long mtime;
    private String mtimeStr;
    private Integer version;
    private Integer cversion;
    private Integer aversion;
    private Long ephemeralOwner;
    private Integer dataLength;
    private Integer numChildren;
    private Long pzxId;

    private String data;

    public PathDataVO(Stat stat, String data) {
        this.czxId = stat.getCzxid();
        this.mzxId = stat.getMzxid();
        this.ctime = stat.getCtime();
        this.ctimeStr = "";
        if (ctime != 0) {
            this.ctimeStr = DateUtils.format(new Date(ctime), DateUtils.DATETIME_MS_FORMATTER1.get());
        }
        this.mtime = stat.getMtime();
        this.mtimeStr = "";
        if (mtime != 0) {
            this.mtimeStr = DateUtils.format(new Date(mtime), DateUtils.DATETIME_MS_FORMATTER1.get());
        }
        this.version = stat.getVersion();
        this.cversion = stat.getCversion();
        this.aversion = stat.getAversion();
        this.ephemeralOwner = stat.getEphemeralOwner();
        this.dataLength = stat.getDataLength();
        this.numChildren = stat.getNumChildren();
        this.pzxId = stat.getPzxid();

        this.data = data;
    }
}
