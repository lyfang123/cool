package com.uwaytech.qiniu.ctrl;

import com.uwaytech.material.dao.MaterialMapper;
import com.uwaytech.qiniu.bo.QiNiuResourceBody;
import com.uwaytech.qiniu.bo.QiNiuResourceItem;
import com.uwaytech.qiniu.service.QiNiuService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by moyi on 2016-06-06.
 */
@RestController
@RequestMapping(value = "/qiniu")
public class QiNiuController {

    @Resource
    QiNiuService qiNiuService;

    @Resource
    MaterialMapper materialMapper;

    //生成上传凭证
    @RequestMapping(value = "/v0.1/token", method = RequestMethod.GET)
    public Object getUploadToken(){
        Map<String,String> map = new HashMap<>();
        String token = qiNiuService.getUpToken();
        map.put("uptoken",token);
        return map;
    }

    //生成上传凭证
    @RequestMapping(value = "/v0.1/open/token", method = RequestMethod.GET)
    public Object getOpenUploadToken(){
        Map<String,String> map = new HashMap<>();
        String token = qiNiuService.getOpenBucketToken();
        map.put("uptoken",token);
        return map;
    }

    /**
     * 视频持久化处理回调接口
     * @param body
     */
    @RequestMapping(value = "/v0.1/video/notify", method = RequestMethod.POST)
    public void videoCallback(@RequestBody QiNiuResourceBody body){
        //TODO 正常情况更新素材表,保存返回信息
        if (body.getCode() == 0) {
            QiNiuResourceItem[] items = body.getItems();
            for (QiNiuResourceItem item : items){
                if (item.getCode() == 0){

                }else{
                    //有失败项

                }
            }
//            Material material = new Material();
//            MaterialExample example = new MaterialExample();
//
//            materialMapper.updateByExample(material, example);
            System.out.println("转换成功: "+ body);
        }else {
            System.out.println("转换失败："+ body);
        }

    }

    @RequestMapping(value = "/v0.1/doc/notify", method = RequestMethod.POST)
    public void documentCallback(@RequestBody QiNiuResourceBody body){

    }

    @RequestMapping(value = "/v0.1/res/{id}", method = RequestMethod.GET)
    public Object download(@PathVariable Long id){

        return null;
    }

}
