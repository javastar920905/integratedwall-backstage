package com.javastar920905.integratedwallbackstage.controller;

import com.alibaba.fastjson.JSONArray;
import com.javastar920905.integratedwallbackstage.util.AliOSSConstants;
import com.javastar920905.integratedwallbackstage.util.AliOSSFileManageUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ouzhx on 2018/6/6.
 */
@RestController public class IndexController {


    /**
     * 根据分类获取文件列表
     *
     * @param keyPath
     * @return
     */
    @GetMapping(value = "/files/") public JSONArray listFiles(
        @RequestParam String keyPath) {
        JSONArray array = new JSONArray();
        array.addAll(AliOSSFileManageUtils.listFiles(keyPath, 500));
        return array;
    }

    /**
     * 获取分类列表
     *
     * @return
     */
    @GetMapping(value = "/category") public JSONArray listCategrory() {
        return AliOSSConstants.IntegratedWallKeyPath.getCategoryList();
    }
}
