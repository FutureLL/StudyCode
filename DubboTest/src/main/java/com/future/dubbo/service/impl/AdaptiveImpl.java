package com.future.dubbo.service.impl;import com.future.dubbo.service.Hello;import org.apache.dubbo.common.URL;import org.apache.dubbo.common.extension.Adaptive;import org.apache.dubbo.config.annotation.DubboService;import org.springframework.context.annotation.Primary;import org.springframework.stereotype.Component;/** * @Description: * @Author: lilei58 * @Date: Created in 2022/2/1 下午3:23 */@Primary@Adaptive@Component@DubboService(interfaceClass = Hello.class)public class AdaptiveImpl implements Hello {    @Override    public void getHello() {    }    @Override    public void getHelloForUrl(URL url) {    }}