package com.wllfengshu.core.utils;

/**
 * 生成rest层文件
 */
public class RestUtil {

    public static String genRest(String tableNameFLDTU,String tableNameFUDTU,String serviceClassName,String entityClassName,String restPack){
        StringBuffer sb=new StringBuffer();
        sb.append(genHead(tableNameFLDTU,tableNameFUDTU,restPack,serviceClassName,entityClassName));
        sb.append(genMember(tableNameFLDTU,tableNameFUDTU));
        sb.append(genInsert(tableNameFLDTU,tableNameFUDTU));
        sb.append(genDelete(tableNameFLDTU,tableNameFUDTU));
        sb.append(genUpdate(tableNameFLDTU,tableNameFUDTU));
        sb.append(genSelect(tableNameFLDTU,tableNameFUDTU));
        sb.append(genSelectList(tableNameFLDTU));
        sb.append(genTail());
        return sb.toString();
    }

    /**
     * 生成头
     * @return
     */
    private static String genHead(String tableNameFLDTU,String tableNameFUDTU,String restPack,String serviceClassName,String entityClassName){
        StringBuffer sb=new StringBuffer();
        sb.append("package "+restPack+";\r\n\r\n");
        sb.append("import "+serviceClassName+";\r\n");
        sb.append("import "+entityClassName+";\r\n");
        sb.append("import io.swagger.annotations.*;\r\n");
        sb.append("import org.slf4j.Logger;\r\n");
        sb.append("import org.slf4j.LoggerFactory;\r\n");
        sb.append("import org.springframework.beans.factory.annotation.Autowired;\r\n");
        sb.append("import org.springframework.web.bind.annotation.*;\r\n");
        sb.append("import javax.servlet.http.HttpServletRequest;\r\n");
        sb.append("import javax.servlet.http.HttpServletResponse;\r\n");
        sb.append("import java.util.*;\r\n\r\n");
        sb.append("@RestController\r\n");
        sb.append("@RequestMapping(\"/"+tableNameFLDTU+"s\")\r\n");
        sb.append("public class "+tableNameFUDTU+"Rest {\r\n\r\n");
        return sb.toString();
    }

    /**
     * 生成尾
     * @return
     */
    private static String genTail(){
        return "\r\n}\r\n";
    }

    /**
     * 生成成员变量
     * @return
     */
    private static String genMember(String tableNameFLDTU,String tableNameFUDTU){
        StringBuffer sb=new StringBuffer();
        sb.append("\r\t@Autowired\r\n");
        sb.append("\r\tprivate "+tableNameFUDTU+"Service "+tableNameFLDTU+"Service;\r\n\r\n");
        sb.append("\r\tprivate Logger logger = LoggerFactory.getLogger(getClass());\r\n\r\n");
        return sb.toString();
    }

    /**
     * 生成插入语句
     * @return
     */
    private static String genInsert(String tableNameFLDTU,String tableNameFUDTU){
        StringBuffer sb=new StringBuffer();
        sb.append("\r\t@ApiOperation(value = \"插入\",httpMethod = \"POST\")\r\n");
        sb.append("\r\t@ApiResponses({\r\n");
        sb.append("\r\t\r\t@ApiResponse(code=400, message=\"IllegalParam\")\r\n");
        sb.append("\r\t})\r\n");
        sb.append("\r\t@RequestMapping(value = \"/"+tableNameFLDTU+"\",method = RequestMethod.POST)\r\n");
        sb.append("\r\tpublic Map<String, Object> insert(\r\n");
        sb.append("\r\t\r\t\r\tHttpServletRequest request,\r\n");
        sb.append("\r\t\r\t\r\tHttpServletResponse response,\r\n");
        sb.append("\r\t\r\t\r\t@RequestBody "+tableNameFUDTU+" entity){\r\n");
        sb.append("\r\t\r\treturn "+tableNameFLDTU+"Service.insert(entity);\r\n");
        sb.append("\r\t}\r\n\r\n");
        return sb.toString();
    }

    /**
     * 生成删除语句
     * @return
     */
    private static String genDelete(String tableNameFLDTU,String tableNameFUDTU){
        StringBuffer sb=new StringBuffer();
        sb.append("\r\t@ApiOperation(value = \"删除\",httpMethod = \"DELETE\")\r\n");
        sb.append("\r\t@ApiImplicitParams({\r\n");
        sb.append("\r\t\r\t@ApiImplicitParam(name = \"id\", value = \"ID\", required = true, dataType = \"int\",paramType = \"path\")\r\n");
        sb.append("\r\t})\r\n");
        sb.append("\r\t@ApiResponses({\r\n");
        sb.append("\r\t\r\t@ApiResponse(code=400, message=\"IllegalParam\")\r\n");
        sb.append("\r\t})\r\n");
        sb.append("\r\t@RequestMapping(value = \"/"+tableNameFLDTU+"/{id}\",method = RequestMethod.DELETE)\r\n");
        sb.append("\r\tpublic Map<String, Object> delete(\r\n");
        sb.append("\r\t\r\t\r\t@PathVariable(\"id\") Integer id,\r\n");
        sb.append("\r\t\r\t\r\tHttpServletRequest request,\r\n");
        sb.append("\r\t\r\t\r\tHttpServletResponse response){\r\n");
        sb.append("\r\t\r\treturn "+tableNameFLDTU+"Service.delete(id);\r\n");
        sb.append("\r\t}\r\n\r\n");
        return sb.toString();
    }

    /**
     * 生成更新语句
     * @return
     */
    private static String genUpdate(String tableNameFLDTU,String tableNameFUDTU){
        StringBuffer sb=new StringBuffer();
        sb.append("\r\t@ApiOperation(value = \"修改\",httpMethod = \"PUT\")\r\n");
        sb.append("\r\t@ApiResponses({\r\n");
        sb.append("\r\t\r\t@ApiResponse(code=400, message=\"IllegalParam\")\r\n");
        sb.append("\r\t})\r\n");
        sb.append("\r\t@RequestMapping(value = \"/"+tableNameFLDTU+"\",method = RequestMethod.PUT)\r\n");
        sb.append("\r\tpublic Map<String, Object> update(\r\n");
        sb.append("\r\t\r\t\r\tHttpServletRequest request,\r\n");
        sb.append("\r\t\r\t\r\tHttpServletResponse response,\r\n");
        sb.append("\r\t\r\t\r\t@RequestBody "+tableNameFUDTU+" entity){\r\n");
        sb.append("\r\t\r\treturn "+tableNameFLDTU+"Service.update(entity);\r\n");
        sb.append("\r\t}\r\n\r\n");
        return sb.toString();
    }

    /**
     * 生成查询语句（单条）
     * @return
     */
    private static String genSelect(String tableNameFLDTU,String tableNameFUDTU){
        StringBuffer sb=new StringBuffer();
        sb.append("\r\t@ApiOperation(value = \"按ID查询\",httpMethod = \"GET\")\r\n");
        sb.append("\r\t@ApiImplicitParams({\r\n");
        sb.append("\r\t\r\t@ApiImplicitParam(name = \"id\", value = \"ID\", required = true, dataType = \"int\",paramType = \"path\")\r\n");
        sb.append("\r\t})\r\n");
        sb.append("\r\t@ApiResponses({\r\n");
        sb.append("\r\t\r\t@ApiResponse(code=400, message=\"IllegalParam\")\r\n");
        sb.append("\r\t})\r\n");
        sb.append("\r\t@RequestMapping(value = \"/"+tableNameFLDTU+"/{id}\",method = RequestMethod.GET)\r\n");
        sb.append("\r\tpublic Map<String, Object> select(\r\n");
        sb.append("\r\t\r\t\r\t@PathVariable(\"id\") Integer id,\r\n");
        sb.append("\r\t\r\t\r\tHttpServletRequest request,\r\n");
        sb.append("\r\t\r\t\r\tHttpServletResponse response){\r\n");
        sb.append("\r\t\r\treturn "+tableNameFLDTU+"Service.select(id);\r\n");
        sb.append("\r\t}\r\n\r\n");
        return sb.toString();
    }

    /**
     * 生成查询语句（多条）
     * @return
     */
    private static String genSelectList(String tableNameFLDTU){
        StringBuffer sb=new StringBuffer();
        sb.append("\r\t@ApiOperation(value = \"查询\",httpMethod = \"GET\")\r\n");
        sb.append("\r\t@ApiImplicitParams({\r\n");
        sb.append("\r\t})\r\n");
        sb.append("\r\t @ApiResponses({\r\n");
        sb.append("\r\t\r\t@ApiResponse(code=400, message=\"IllegalParam\")\r\n");
        sb.append("\r\t})\r\n");
        sb.append("\r\t@RequestMapping(value = \"/\",method = RequestMethod.GET)\r\n");
        sb.append("\r\tpublic Map<String, Object> selects(\r\n");
        sb.append("\r\t\r\t\r\tHttpServletRequest request,\r\n");
        sb.append("\r\t\r\t\r\tHttpServletResponse response){\r\n");
        sb.append("\r\t\r\t\r\tMap<String,Object> params = new HashMap<>();\r\n");
        sb.append("\r\t\r\treturn "+tableNameFLDTU+"Service.selects(params);\r\n");
        sb.append("\r\t}\r\n");
        return sb.toString();
    }
}
