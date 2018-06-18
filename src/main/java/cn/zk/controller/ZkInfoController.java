package cn.zk.controller;

import cn.zk.common.AdminException;
import cn.zk.common.Resp;
import cn.zk.common.RespCode;
import cn.zk.entity.PathDataVO;
import cn.zk.entity.PathVO;
import cn.zk.entity.ZkInfo;
import cn.zk.service.ZkInfoService;
import cn.zk.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.util.List;

/**
 * <br/>
 * Created on 2018/6/12 19:32.
 *
 * @author zhubenle
 */
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Controller
public class ZkInfoController {

    private final static String ZKINFOS_FAIL_MESSAGE = "zkInfosFailMessage";
    private final static String ZKINFO_FAIL_MESSAGE = "zkInfoFailMessage";

    private final ZkInfoService zkInfoService;

    @GetMapping(value = "/zkinfos")
    public String zkInfos(ModelMap modelMap, @ModelAttribute(ZKINFOS_FAIL_MESSAGE) String zkInfosFailMessage) {
        modelMap.addAttribute("zkinfos", zkInfoService.listAll());
        modelMap.addAttribute(ZKINFOS_FAIL_MESSAGE, zkInfosFailMessage);
        return "views/zkinfos";
    }

    @DeleteMapping(value = "/zkinfo/{alias}")
    @ResponseBody
    public Resp<String> deleteZkInfo(@PathVariable(value = "alias") String alias) {
        Resp<String> userResp = new Resp<>();
        try {
            zkInfoService.deleteZkInfoByAlias(alias);
            userResp.success(null);
        } catch (AdminException e) {
            log.error("删除zkInfo失败: {}", e.getCodeMsg());
            userResp.fail(e);
        } catch (Exception e) {
            log.error("删除zkInfo异常", e);
            userResp.fail(RespCode.ERROR_99999);
        }
        return userResp;
    }

    @PostMapping(value = "/zkinfo")
    public String saveZkInfo(ZkInfo zkInfo, RedirectAttributesModelMap modelMap) {
        try {
            zkInfoService.saveZkInfo(zkInfo);
        } catch (AdminException e) {
            log.error("删除zkInfo失败: {}", e.getCodeMsg());
            modelMap.addFlashAttribute(ZKINFOS_FAIL_MESSAGE, e.getCodeMsg());
        } catch (Exception e) {
            log.error("删除zkInfo异常", e);
            modelMap.addFlashAttribute(ZKINFOS_FAIL_MESSAGE, e.getMessage());
        }
        return "redirect:zkinfos";
    }

    @GetMapping(value = "/zkinfo/{alias}")
    public String toZkInfo(@PathVariable(value = "alias") String alias,
                           ModelMap modelMap) {
        try {
            List<ZkInfo> zkInfos = zkInfoService.listAll();
            modelMap.addAttribute("zkinfos", zkInfos);
            modelMap.addAttribute("zkinfo", zkInfos.stream()
                    .filter(zkInfo -> zkInfo.getAlias().equals(alias))
                    .findFirst()
                    .orElseThrow(() -> new AdminException(RespCode.ERROR_10004)));
        } catch (AdminException e) {
            log.error("获取alias={}失败: {}", alias, e.getCodeMsg());
            modelMap.addAttribute(ZKINFO_FAIL_MESSAGE, e.getCodeMsg());
        } catch (Exception e) {
            log.error("获取alias={}异常", alias, e);
            modelMap.addAttribute(ZKINFO_FAIL_MESSAGE, e.getMessage());
        }
        return "views/zkinfo";
    }

    @GetMapping(value = "/path/{alias}")
    @ResponseBody
    public Resp<List<PathVO>> getPath(@PathVariable(value = "alias") String alias,
                                      @RequestParam(value = "id", required = false, defaultValue = "") String id) {
        Resp<List<PathVO>> userResp = new Resp<>();
        try {
            userResp.success(zkInfoService.listZkChildrenPath(alias, id));
        } catch (AdminException e) {
            log.error("获取alias={}, path={}子路径失败: {}", alias, id, e.getCodeMsg());
            userResp.fail(e);
        } catch (Exception e) {
            log.error("获取alias={}, path={}子路径异常", alias, id, e);
            userResp.fail(RespCode.ERROR_99999);
        }
        return userResp;
    }

    @DeleteMapping(value = "/path/{alias}")
    @ResponseBody
    public Resp<String> deletePath(@PathVariable(value = "alias") String alias,
                                   @RequestParam(value = "dataVersion") Integer dataVersion,
                                   @RequestParam(value = "pathId") String pathId) {
        Resp<String> userResp = new Resp<>();
        try {
            zkInfoService.deletePath(alias, pathId, dataVersion);
            userResp.success(null);
        } catch (AdminException e) {
            log.error("删除alias={}, path={}节点失败: {}", alias, pathId, e.getCodeMsg());
            userResp.fail(e);
        } catch (Exception e) {
            log.error("删除alias={}, path={}节点异常", alias, pathId, e);
            userResp.fail(RespCode.ERROR_99999);
        }
        return userResp;
    }

    @PostMapping(value = "/path/{alias}")
    @ResponseBody
    public Resp<String> createPath(@PathVariable(value = "alias") String alias,
                                   @RequestParam(value = "pathId") String pathId,
                                   @RequestParam(value = "data", required = false) String data,
                                   @RequestParam(value = "createMode", required = false, defaultValue = "0") Integer createMode) {
        Resp<String> userResp = new Resp<>();
        try {
            userResp.success(zkInfoService.createPath(alias, pathId, data, createMode));
        } catch (AdminException e) {
            log.error("创建alias={}, path={}节点失败: {}", alias, pathId, e.getCodeMsg());
            userResp.fail(e);
        } catch (Exception e) {
            log.error("创建alias={}, path={}节点异常", alias, pathId, e);
            userResp.fail(RespCode.ERROR_99999);
        }
        return userResp;
    }

    @GetMapping(value = "/path/data/{alias}")
    @ResponseBody
    public Resp<PathDataVO> getPathData(@PathVariable(value = "alias") String alias,
                                        @RequestParam(value = "pathId", required = false, defaultValue = "") String pathId) {
        Resp<PathDataVO> userResp = new Resp<>();
        try {
            userResp.success(StringUtils.isEmpty(pathId) ? null : zkInfoService.getPathData(alias, pathId));
        } catch (AdminException e) {
            log.error("获取alias={}, path={}数据失败: {}", alias, pathId, e.getCodeMsg());
            userResp.fail(e);
        } catch (Exception e) {
            log.error("获取alias={}, path={}数据异常", alias, pathId, e);
            userResp.fail(RespCode.ERROR_99999);
        }
        return userResp;
    }
}
