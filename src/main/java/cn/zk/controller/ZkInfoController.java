package cn.zk.controller;

import cn.zk.common.AdminException;
import cn.zk.common.Resp;
import cn.zk.common.RespCode;
import cn.zk.entity.PathVO;
import cn.zk.entity.ZkInfo;
import cn.zk.service.ZkInfoService;
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
                                      @RequestParam(value = "name", required = false, defaultValue = "/") String name,
                                      @RequestParam(value = "id", required = false, defaultValue = "") String id,
                                      ModelMap modelMap) {
        Resp<List<PathVO>> userResp = new Resp<>();
        try {

            userResp.success(zkInfoService.listZkChildrenPath(alias, name, id));
        } catch (AdminException e) {
            log.error("获取alias={}, path={}失败: {}", alias, id, e.getCodeMsg());
            userResp.fail(e);
        } catch (Exception e) {
            log.error("获取alias={}, path={}异常", alias, id, e);
            userResp.fail(RespCode.ERROR_99999);
        }
        return userResp;
    }
}
