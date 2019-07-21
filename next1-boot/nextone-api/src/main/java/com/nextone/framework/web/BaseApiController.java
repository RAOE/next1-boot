package com.nextone.framework.web;

import com.nextone.framework.service.BaseService;
import org.springframework.web.bind.annotation.RestController;

/**
 * 通用的rest接口 用于拓展
 * @author 徐塬峰
 * @email 986771570@qq.com
 * @date 2019-06-30
 * @description
 **/
@RestController
public class BaseApiController<T,S extends BaseService<T>>{


}
