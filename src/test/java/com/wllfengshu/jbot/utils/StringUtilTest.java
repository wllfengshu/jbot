package com.wllfengshu.jbot.utils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class StringUtilTest {

    @Test
    public void testSplitFtlSuffix() {
        assert "startup.sh".equals(StringUtil.splitFtlSuffix("startup.sh.ftl"));
    }

    @Test
    public void testReplace() {
        List<String> ping = new ArrayList<>();
        ping.add("exception\\WebResponse.java.ftl");
        ping.forEach(System.out::println);

        for(String i:ping){
            i=i.replace('\\','/');
        }

        ping.forEach(System.out::println);
    }
}