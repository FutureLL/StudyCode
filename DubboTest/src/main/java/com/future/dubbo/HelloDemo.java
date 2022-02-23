package com.future.dubbo;import com.future.dubbo.service.Hello;import org.apache.dubbo.common.URL;import org.apache.dubbo.common.extension.ExtensionLoader;import java.io.IOException;import java.util.HashMap;import java.util.Map;/** * @Description: * @Author: lilei58 * @Date: Created in 2022/1/19 上午8:51 */public class HelloDemo {    public static void main(String[] args) {        ExtensionLoader<Hello> extensionLoader = ExtensionLoader.getExtensionLoader(Hello.class);        Map<String, String> map = new HashMap<>();        // 指定那个,注入那个实现类        map.put("hello", "helloImpl");        URL url = new URL("", "", 1, map);        // 拿名字 chinaHelloImpl 所对应的实现类,这个名字在 META-INF.dubbo 中对应的接口名的文件中进行了配置        // chinaHelloImpl = com.future.dubbo.service.impl.ChinaHelloImpl        Hello helloImpl = extensionLoader.getExtension("chinaHelloImpl");        //helloImpl.getHelloForUrl(url);        // 自动注入        // AOP        try {            System.in.read();        } catch (IOException e) {            e.printStackTrace();        }    }}