package com.future.dubbo.service.impl;import com.future.dubbo.service.Hello;import org.apache.dubbo.common.URL;import org.apache.dubbo.config.annotation.DubboService;import org.springframework.stereotype.Component;/** * @Description: * @Author: lilei58 * @Date: Created in 2022/1/26 上午9:47 */@Component@DubboService(interfaceClass = Hello.class)public class HelloWrapper2 implements Hello {    private Hello hello;    /**     * 传入具体的实现类     * @param hello     */    public HelloWrapper2(Hello hello) {        this.hello = hello;    }    @Override    public void sayHello() {        // AOP        System.out.println("before2");        hello.sayHello();        System.out.println("after2");    }    @Override    public void helloForUrl(URL url) {    }}