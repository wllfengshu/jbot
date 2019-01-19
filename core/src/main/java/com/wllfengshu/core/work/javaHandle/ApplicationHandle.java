package com.wllfengshu.core.work.javaHandle;

import com.wllfengshu.common.utils.FileUtil;
import com.wllfengshu.common.utils.StringUtil;
import com.wllfengshu.core.model.RequestModel;

public class ApplicationHandle {

    public static void start(RequestModel requestModel){
        //1、生成对应的spring boot的Application文件
        genFile(requestModel);
    }

    private static void genFile(RequestModel requestModel){
        String entity=genApplication(requestModel.getProjectName(),requestModel.getPackageName(),requestModel.getDaoPack());
        FileUtil.createFile(requestModel.getPackageBasePath()+"/"+StringUtil.toFirstCharUpperCase(requestModel.getProjectName())+"Application.java",entity);
    }

    private static String genApplication(String projectName,String packageName,String daoPack){
        StringBuffer sb = new StringBuffer();
        sb.append("package "+packageName+"."+projectName+";\r\n\r\n");
        sb.append("import org.mybatis.spring.annotation.MapperScan;\r\n");
        sb.append("import org.springframework.boot.SpringApplication;\r\n");
        sb.append("import org.springframework.boot.autoconfigure.SpringBootApplication;\r\n");
        sb.append("import org.springframework.context.annotation.EnableAspectJAutoProxy;\r\n");
        sb.append("import springfox.documentation.swagger2.annotations.EnableSwagger2;\r\n\r\n");
        sb.append("@SpringBootApplication\r\n");
        sb.append("@EnableSwagger2\r\n");
        sb.append("@EnableAspectJAutoProxy\r\n");
        sb.append("@MapperScan(\""+daoPack+"\")\r\n");
        sb.append("public class "+StringUtil.toFirstCharUpperCase(projectName)+"Application {\r\n\r\n");
        sb.append("\tpublic static void main(String[] args) {\r\n");
        sb.append("\t\tSpringApplication.run("+StringUtil.toFirstCharUpperCase(projectName)+"Application.class, args);\r\n");
        sb.append("\t}\r\n\r\n");
        sb.append("}\r\n\r\n");
        return sb.toString();
    }
}
