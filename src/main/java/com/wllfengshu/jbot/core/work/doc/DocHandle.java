package com.wllfengshu.jbot.core.work.doc;

import com.wllfengshu.jbot.web.entity.FieldInfo;
import com.wllfengshu.jbot.web.entity.TableInfo;
import com.wllfengshu.jbot.common.exception.CustomException;
import com.wllfengshu.jbot.common.model.RequestModel;
import com.wllfengshu.jbot.common.model.TableModel;
import com.wllfengshu.jbot.common.utils.FileUtil;
import com.wllfengshu.jbot.common.utils.StringUtil;

import java.util.List;

/**
 * 处理生成markdown文档
 * @author wllfengshu
 */
public class DocHandle {

    public static void start(RequestModel model)throws CustomException {
        //1、生成对应的md文件
        genFile(model);
    }

    private static void genFile(RequestModel model)throws CustomException {
        for (TableModel t:model.getTableModels()) {
            String entity=genData(model.getProjectName(),t.getTableNameFUDTU(),t.getTableNameFLDTU(),t.getTableInfo());
            FileUtil.createFile(model.getDocPath()+"/"+StringUtil.spotToSlash(t.getTableNameFUDTU())+"管理.md",entity);
        }
    }

    private static String genData(String projectName,String tableNameFUDTU,String tableNameFLDTU,TableInfo tableInfo){
        StringBuffer sb=new StringBuffer();
        sb.append(genHead(tableNameFUDTU));
        sb.append("\r\n## 一、数据字典\r\n\r\n");
        sb.append(genDataDictionary(tableInfo.getFields()));
        sb.append("\r\n## 二、接口文档\r\n\r");
        sb.append(genInterfaceInsert(projectName,tableNameFLDTU,tableInfo.getFields()));
        sb.append("\r\n<br/><br/>\r\n\r");
        sb.append(genInterfaceDelete(projectName,tableNameFLDTU,tableInfo.getFields()));
        sb.append("\r\n<br/><br/>\r\n\r");
        sb.append(genInterfaceUpdate(projectName,tableNameFLDTU,tableInfo.getFields()));
        sb.append("\r\n<br/><br/>\r\n\r");
        sb.append(genInterfaceSelect(projectName,tableNameFLDTU,tableInfo.getFields()));
        sb.append("\r\n<br/><br/>\r\n\r");
        sb.append(genInterfaceSelects(projectName,tableNameFLDTU,tableInfo.getFields()));
        sb.append("\r\n<br/><br/>\r\n\r");
        sb.append(genTail());
        return sb.toString();
    }

    /**
     * 生成头
     * @return
     */
    private static StringBuffer genHead(String tableNameFUDTU){
        StringBuffer sb=new StringBuffer();
        sb.append("# "+tableNameFUDTU+"管理\r\n\r\n");
        return sb;
    }

    /**
     * 生成尾
     * @return
     */
    private static String genTail(){
        return "\r\n<br/><br/>\r\n";
    }

    /**
     * 生成数据字典
     * @return
     */
    private static StringBuffer genDataDictionary(List<FieldInfo> fs) {
        StringBuffer sb = new StringBuffer();
        sb.append("注：PRI主键约束;UNI唯一约束;MUL可以重复\r\n\r\n");
        sb.append("| 编号 | 字段名 | 类型 | 是否允许空 | 约束 | 含义 | 备注 |\r\n");
        sb.append("| ---- | ---- | ---- | ---- | ---- | ---- | ---- |\r\n");
        for (int i=0;i<fs.size();i++){
            sb.append(" | "+(i+1)
                     +" | "+fs.get(i).getFieldName()
                     +" | "+fs.get(i).getColumnType()
                     +" | "+fs.get(i).getIsNullable()
                     +" | "+fs.get(i).getColumnKey()
                     +" | "+fs.get(i).getColumnComment()
                     +" |  |\r\n");
        }
        return sb;
    }

    /**
     * 生成增加的markdown
     * @return
     */
    private static StringBuffer genInterfaceInsert(String projectName,String tableNameFLDTU,List<FieldInfo> fs){
        StringBuffer sb = new StringBuffer();
        sb.append("### 1 增加\r\n\r\n");
        sb.append("`POST model/"+tableNameFLDTU+"s/"+tableNameFLDTU+"`\r\n\r\n");
        sb.append("#### 1.1 请求参数\r\n\r\n");
        sb.append(requestParamsEntity());
        sb.append("#### 1.2 响应参数\r\n\r\n");
        sb.append(responseParamsHeaders());
        sb.append("#### 1.3 请求响应示例\r\n\r\n");
        sb.append("```\r\n");
        sb.append("POST http://127.0.0.1:8080/"+projectName+"/"+tableNameFLDTU+"s/"+tableNameFLDTU+"\r\n");
        sb.append("```\r\n\r\n");
        sb.append("- (1) 请求头：request headers\r\n\r\n");
        sb.append(exampleRequestHeaders());
        sb.append("- (2) request payload\r\n\r\n");
        sb.append(fieldJson(fs));
        sb.append("- (3) response\r\n\r\n");
        sb.append(nullJson());
        sb.append("#### 1.4 异常示例\r\n\r\n");
        sb.append(exceptionExample(projectName));
        sb.append("#### 1.5 业务错误码\r\n\r\n");
        sb.append(errorCode());
        return sb;
    }

    /**
     * 生成删除的markdown
     * @return
     */
    private static StringBuffer genInterfaceDelete(String projectName,String tableNameFLDTU,List<FieldInfo> fs){
        StringBuffer sb = new StringBuffer();
        sb.append("### 2 删除\r\n\r\n");
        sb.append("`DELETE "+projectName+"/"+tableNameFLDTU+"s/"+tableNameFLDTU+"/{id}`\r\n\r\n");
        sb.append("#### 2.1 请求参数\r\n\r\n");
        sb.append(requestParamsId(fs));
        sb.append("#### 2.2 响应参数\r\n\r\n");
        sb.append(responseParamsHeaders());
        sb.append("#### 2.3 请求响应示例\r\n\r\n");
        sb.append("```\r\n");
        sb.append("DELETE http://127.0.0.1:8080/"+projectName+"/"+tableNameFLDTU+"s/"+tableNameFLDTU+"/1\r\n");
        sb.append("```\r\n\r\n");
        sb.append("- (1) 请求头：request headers\r\n\r\n");
        sb.append(exampleRequestHeaders());
        sb.append("- (2) request payload\r\n\r\n");
        sb.append(nullJson());
        sb.append("- (3) response\r\n\r\n");
        sb.append(nullJson());
        sb.append("#### 2.4 异常示例\r\n\r\n");
        sb.append(exceptionExample(projectName));
        sb.append("#### 2.5 业务错误码\r\n\r\n");
        sb.append(errorCode());
        return sb;
    }

    /**
     * 生成修改的markdown
     * @return
     */
    private static StringBuffer genInterfaceUpdate(String projectName,String tableNameFLDTU,List<FieldInfo> fs){
        StringBuffer sb = new StringBuffer();
        sb.append("### 3 修改\r\n\r\n");
        sb.append("`PUT "+projectName+"/"+tableNameFLDTU+"s/"+tableNameFLDTU+"`\r\n\r\n");
        sb.append("#### 3.1 请求参数\r\n\r\n");
        sb.append(requestParamsEntity());
        sb.append("#### 3.2 响应参数\r\n\r\n");
        sb.append(responseParamsHeaders());
        sb.append("#### 3.3 请求响应示例\r\n\r\n");
        sb.append("```\r\n");
        sb.append("PUT http://127.0.0.1:8080/"+projectName+"/"+tableNameFLDTU+"s/"+tableNameFLDTU+"\r\n");
        sb.append("```\r\n\r\n");
        sb.append("- (1) 请求头：request headers\r\n\r\n");
        sb.append(exampleRequestHeaders());
        sb.append("- (2) request payload\r\n\r\n");
        sb.append(fieldJson(fs));
        sb.append("- (3) response\r\n\r\n");
        sb.append(nullJson());
        sb.append("#### 3.4 异常示例\r\n\r\n");
        sb.append(exceptionExample(projectName));
        sb.append("#### 3.5 业务错误码\r\n\r\n");
        sb.append(errorCode());
        return sb;
    }

    /**
     * 生成按id查询的markdown
     * @return
     */
    private static StringBuffer genInterfaceSelect(String projectName,String tableNameFLDTU,List<FieldInfo> fs){
        StringBuffer sb = new StringBuffer();
        sb.append("### 4 通过ID查询\r\n\r\n");
        sb.append("`GET "+projectName+"/"+tableNameFLDTU+"s/"+tableNameFLDTU+"/{id}`\r\n\r\n");
        sb.append("#### 4.1 请求参数\r\n\r\n");
        sb.append(requestParamsId(fs));
        sb.append("#### 4.2 响应参数\r\n\r\n");
        sb.append(fieldList(fs));
        sb.append("#### 4.3 请求响应示例\r\n\r\n");
        sb.append("```\r\n");
        sb.append("GET http://127.0.0.1:8080/"+projectName+"/"+tableNameFLDTU+"s/"+tableNameFLDTU+"/1\r\n");
        sb.append("```\r\n\r\n");
        sb.append("- (1) 请求头：request headers\r\n\r\n");
        sb.append(exampleRequestHeaders());
        sb.append("- (2) request payload\r\n\r\n");
        sb.append(nullJson());
        sb.append("- (3) response\r\n\r\n");
        sb.append(fieldJson(fs));
        sb.append("#### 4.4 异常示例\r\n\r\n");
        sb.append(exceptionExample(projectName));
        sb.append("#### 4.5 业务错误码\r\n\r\n");
        sb.append(errorCode());
        return sb;
    }

    /**
     * 生成查询的markdown
     * @return
     */
    private static StringBuffer genInterfaceSelects(String projectName,String tableNameFLDTU,List<FieldInfo> fs){
        StringBuffer sb = new StringBuffer();
        sb.append("### 5 查询\r\n\r\n");
        sb.append("`GET "+projectName+"/"+tableNameFLDTU+"s`\r\n\r\n");
        sb.append("#### 5.1 请求参数\r\n\r\n");
        sb.append(requestParamsPage());
        sb.append("#### 5.2 响应参数\r\n\r\n");
        sb.append(fieldList(fs));
        sb.append("#### 5.3 请求响应示例\r\n\r\n");
        sb.append("```\r\n");
        sb.append("GET http://127.0.0.1:8080/"+projectName+"/"+tableNameFLDTU+"s\r\n");
        sb.append("```\r\n\r\n");
        sb.append("- (1) 请求头：request headers\r\n\r\n");
        sb.append(exampleRequestHeaders());
        sb.append("- (2) request payload\r\n\r\n");
        sb.append(nullJson());
        sb.append("- (3) response\r\n\r\n");
        sb.append(fieldJsonPage(fs));
        sb.append("#### 4.4 异常示例\r\n\r\n");
        sb.append(exceptionExample(projectName));
        sb.append("#### 4.5 业务错误码\r\n\r\n");
        sb.append(errorCode());
        return sb;
    }

    /**
     * 生成请求参数头
     * @return
     */
    private static StringBuffer requestParamsHeaders(){
        StringBuffer sb = new StringBuffer();
        sb.append("| 参数名 | 类型 | 是否允许空 | 位置 | 描述 | 举例 | 备注 |\r\n");
        sb.append("| ---- | ---- | ---- | ---- | ---- | ---- | ---- |\r\n");
        return sb;
    }

    /**
     * 生成请求参数-entity形式
     * @return
     */
    private static StringBuffer requestParamsEntity(){
        StringBuffer sb = new StringBuffer();
        sb.append(requestParamsHeaders());
        sb.append("| sessionId | string | 否 | header | SessionID | 2ab321c6-8869-4d20-a2d5-35e930e08132 |\r\n");
        sb.append("| entity | json | 否 | body | 实体类 |  |  |\r\n\r\n");
        return sb;
    }

    /**
     * 生成请求参数-id形式
     * @return
     */
    private static StringBuffer requestParamsId(List<FieldInfo> fs){
        //默认情况下，id为string类型
        String idType="string";
        for(FieldInfo f:fs){
            if ("id".equals(f.getFieldName())){
                idType=StringUtil.sqlType2JavaType(f.getFieldType());
                break;
            }
        }
        StringBuffer sb = new StringBuffer();
        sb.append(requestParamsHeaders());
        sb.append("| sessionId | string | 否 | header | SessionID | 2ab321c6-8869-4d20-a2d5-35e930e08132 |\r\n");
        sb.append("| id | "+idType+" | 否 | path | id | 1 |  |\r\n\r\n");
        return sb;
    }

    /**
     * 生成请求参数-page形式
     * @return
     */
    private static StringBuffer requestParamsPage(){
        StringBuffer sb = new StringBuffer();
        sb.append(requestParamsHeaders());
        sb.append("| sessionId | string | 否 | header | SessionID | 2ab321c6-8869-4d20-a2d5-35e930e08132 |\r\n");
        sb.append("| pageNo | int | 是 | query | 页码 | 0 | 从0开始,默认为0 |\r\n");
        sb.append("| pageSize | int | 是 | query | 页面大小 | 10 | 默认为10 |\r\n\r\n");
        return sb;
    }

    /**
     * 生成响应参数头
     * @return
     */
    private static StringBuffer responseParamsHeaders(){
        StringBuffer sb = new StringBuffer();
        sb.append("| 参数名 | 类型 | 是否允许空 | 描述 | 备注 |\r\n");
        sb.append("| ---- | ---- | ---- | ---- | ---- |\r\n");
        return sb;
    }

    /**
     * 生成请求示例头request headers
     * @return
     */
    private static StringBuffer exampleRequestHeaders(){
        StringBuffer sb = new StringBuffer();
        sb.append("```\r\n");
        sb.append("sessionId: d56006cc-a698-437c-a580-6fcb8e86cc62\r\n");
        sb.append("Content-Type: application/json;charset=utf-8\r\n");
        sb.append("```\r\n\r\n");
        return sb;
    }

    /**
     * 生成实体类对应的json形式
     * @param fs
     * @return
     */
    private static StringBuffer fieldJson(List<FieldInfo> fs){
        StringBuffer sb = new StringBuffer();
        sb.append("```json\r\n");
        sb.append("{\r\n");
        for (FieldInfo f:fs){
            switch (StringUtil.sqlType2JavaType(f.getFieldType())){
                case "Boolean":
                    sb.append("\t\""+f.getFieldName()+"\" : true,\r\n");
                    break;
                case "Integer":
                case "Long":
                    sb.append("\t\""+f.getFieldName()+"\" : 0,\r\n");
                    break;
                case "Double":
                    sb.append("\t\""+f.getFieldName()+"\" : 0.0,\r\n");
                    break;
                case "Date":
                    sb.append("\t\""+f.getFieldName()+"\" : \"2019-01-01 00:00:00\",\r\n");
                    break;
                default:
                    sb.append("\t\""+f.getFieldName()+"\" : \"string\",\r\n");
            }
        }
        //删除最后多的一个逗号
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append("}\r\n");
        sb.append("```\r\n\r\n");
        return sb;
    }

    /**
     * 生成实体类对应的list形式(列表)
     * @param fs
     * @return
     */
    private static StringBuffer fieldList(List<FieldInfo> fs){
        StringBuffer sb = new StringBuffer();
        sb.append(responseParamsHeaders());
        for(FieldInfo f:fs){
            sb.append("| "+f.getFieldName()+" | "+StringUtil.sqlType2JavaType(f.getFieldType())+" | "+f.getIsNullable()+" | "+f.getColumnComment()+" |  |\r\n");
        }
        return sb;
    }

    /**
     * 生成分页时实体类对应的json形式
     * @param fs
     * @return
     */
    private static StringBuffer fieldJsonPage(List<FieldInfo> fs){
        StringBuffer sb = new StringBuffer();
        sb.append("```json\r\n");
        sb.append("{\r\n");
        sb.append("\t\"total\": 1,\r\n");
        sb.append("\t\"data\": [\r\n");
        sb.append("\t\t{\r\n");
        for (FieldInfo f:fs){
            switch (StringUtil.sqlType2JavaType(f.getFieldType())){
                case "Boolean":
                    sb.append("\t\t\t\""+f.getFieldName()+"\" : true,\r\n");
                    break;
                case "Integer":
                case "Long":
                    sb.append("\t\t\t\""+f.getFieldName()+"\" : 0,\r\n");
                    break;
                case "Double":
                    sb.append("\t\t\t\""+f.getFieldName()+"\" : 0.0,\r\n");
                    break;
                case "Date":
                    sb.append("\t\t\t\""+f.getFieldName()+"\" : \"2019-01-01 00:00:00\",\r\n");
                    break;
                default:
                    sb.append("\t\t\t\""+f.getFieldName()+"\" : \"string\",\r\n");
            }
        }
        //删除最后多的一个逗号
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append("\t\t}\r\n");
        sb.append("\t]\r\n");
        sb.append("}\r\n");
        sb.append("```\r\n\r\n");
        return sb;
    }

    /**
     * 生成一个空的json
     * @return
     */
    private static StringBuffer nullJson(){
        StringBuffer sb = new StringBuffer();
        sb.append("```json\r\n\r\n");
        sb.append("```\r\n\r\n");
        return sb;
    }

    /**
     * 生成异常示例
     * @param projectName
     * @return
     */
    private static StringBuffer exceptionExample(String projectName){
        StringBuffer sb = new StringBuffer();
        sb.append("```json\r\n");
        sb.append("{\r\n");
        sb.append("\t\"errorMessage\": \"没有权限\",\r\n");
        sb.append("\t\"errorCode\": 14001,\r\n");
        sb.append("\t\"instanceId\": \""+projectName+"-xyzws\"\r\n");
        sb.append("}\r\n");
        sb.append("```\r\n\r\n");
        return sb;
    }

    /**
     * 生成业务错误码
     * @return
     */
    private static StringBuffer errorCode(){
        StringBuffer sb = new StringBuffer();
        sb.append("| 错误码 | 描述 | 解决方案 |\r\n");
        sb.append("| ---- | ---- | ---- |\r\n");
        sb.append("| 14001 | 没有权限 | 检查该用户的权限 |\r\n\r\n");
        return sb;
    }
}
