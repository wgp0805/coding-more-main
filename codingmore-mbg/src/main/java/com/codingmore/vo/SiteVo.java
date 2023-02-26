package com.codingmore.vo;

import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SiteVo {

    private Long siteId;

    @ApiModelProperty("站点名称")
    private String siteName;

    @ApiModelProperty("站点介绍")
    private String siteDesc;

    @ApiModelProperty("关键字")
    private String keywords;

    @ApiModelProperty("修改时间")
    private Date updateTime;

    @ApiModelProperty("属性")
    private Map<String,Object> attribute;
    private String attributeStr;

    public String getAttributeStr() {
        if(attribute != null) {
            //这里的objMapper是jackSon的转换对象，可以进行各种类型和json之间的转化 https://blog.csdn.net/qq_42017395/article/details/107555339
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                // todo 字符串转对象，但是这里看返回的还是字符串，不清楚这里的意义
                return  objectMapper.writeValueAsString(attribute);
            } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return attributeStr;
    }
}
