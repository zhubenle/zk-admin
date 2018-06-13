package cn.zk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <br/>
 * Created on 2018/6/12 19:32.
 *
 * @author zhubenle
 */
@Controller
public class ZkInfoController {

    @GetMapping(value = "/zkinfos")
    public String zkinfos() {
        return "views/zkinfos";
    }
}
