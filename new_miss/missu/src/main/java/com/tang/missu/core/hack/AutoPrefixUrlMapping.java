package com.tang.missu.core.hack;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;


/* 根据目录结构生成api前缀 */
public class AutoPrefixUrlMapping extends RequestMappingHandlerMapping {

    @Override
    protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
        // 先接收RequestMappingInfo
        RequestMappingInfo mappingInfo = super.getMappingForMethod(method, handlerType);
        String packageName = this.getPrefix(handlerType); // com.tang.missu.api.v1.a.BannerController
        // 再修改路由信息
        if(mappingInfo !=null) { // 判非空
            String prefix = this.getPrefix(handlerType);
            // 生成新的mappingInfo /v1,并合并原有的mappingInfo /banner/test
            RequestMappingInfo newMappingInfo =  RequestMappingInfo.paths(prefix).build().combine(mappingInfo);
            return  newMappingInfo;
        }
        return mappingInfo;
    }

    // 获取配置文件中的根包名
    @Value("${missu.api-package}")
    private String apiPackagePath;

    // 获取文件所在路径名
    private String getPrefix(Class<?> handlerType){ // 这里只需要取出来/v1
        String packageName = handlerType.getPackage().getName();
        String docPath = packageName.replaceAll(this.apiPackagePath,"");
        return docPath.replace(".","/"); // 将.v1换成 /v1
    }
}
