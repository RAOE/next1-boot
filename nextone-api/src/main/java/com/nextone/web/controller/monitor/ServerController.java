package com.nextone.web.controller.monitor;

import com.nextone.pojo.server.Server;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 服务器监控
 * 
 * @author xuyuanfeng
 */
@Controller
@RequestMapping("/monitor")
public class ServerController
{
    private String prefix = "monitor/server";

    @GetMapping("/server")
    public String server(ModelMap model) throws Exception
    {
        Server server = new Server();
        server.copyTo();
        model.put("server", server);
        return prefix + "/server";
    }
}
