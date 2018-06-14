package cn.zk.controller;

import cn.zk.service.ZkInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

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

    private final ZkInfoService zkInfoService;

    @GetMapping(value = "/zkinfos")
    public String zkinfos(ModelMap modelMap) {
        modelMap.addAttribute("zkinfos", zkInfoService.listAll());
        return "views/zkinfos";
    }
}
