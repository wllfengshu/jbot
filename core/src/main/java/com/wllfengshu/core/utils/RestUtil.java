package com.wllfengshu.core.utils;

import com.wllfengshu.common.entity.TableInfo;
import com.wllfengshu.common.utils.StringUtil;

/**
 * 生成rest层文件
 */
public class RestUtil {

    public static String genRest(String projectName,String packageName,TableInfo tableInfo){
        String tableNameFUDTU=StringUtil.toFirstCharUpperCase(StringUtil.underlineToHump(StringUtil.delTUnderline(tableInfo.getTableName())));
        String serviceClassName=packageName+"."+projectName+".service."+tableNameFUDTU+"Service";
        String entityClassName=packageName+"."+projectName+".entity."+tableNameFUDTU;
        String pack=packageName+"."+projectName+".rest";
        StringBuffer serviceImpl=new StringBuffer();
        serviceImpl.append(genHead(tableNameFUDTU,pack,serviceClassName,entityClassName));
        serviceImpl.append(genMember(tableNameFUDTU));
        serviceImpl.append(genInsert(tableNameFUDTU));
        serviceImpl.append(genDelete(tableNameFUDTU));
        serviceImpl.append(genUpdate(tableNameFUDTU));
        serviceImpl.append(genSelect(tableNameFUDTU));
        serviceImpl.append(genSelectList());
        serviceImpl.append(genTail());
        return serviceImpl.toString();
    }

    /**
     * 生成头
     * @param tableNameFUDTU
     * @param pack
     * @param serviceClassName
     * @param entityClassName
     * @return
     */
    private static String genHead(String tableNameFUDTU,String pack,String serviceClassName,String entityClassName){
        return  "package "+pack+";\n\n" +
                "import "+serviceClassName+";\n" +
                "import "+entityClassName+";\n" +
                "import io.swagger.annotations.*;\n" +
                "import org.slf4j.Logger;\n" +
                "import org.slf4j.LoggerFactory;\n" +
                "import org.springframework.beans.factory.annotation.Autowired;\n" +
                "import org.springframework.web.bind.annotation.*;\n\n" +
                "import javax.servlet.http.HttpServletRequest;\n" +
                "import javax.servlet.http.HttpServletResponse;\n" +
                "import java.util.*;\n\n" +
                "@RestController\n" +
                "@RequestMapping(\"/"+tableNameFUDTU+"s\")\n" +
                "public class UserRest {\n\n";
    }

    /**
     * 生成尾
     * @return
     */
    private static String genTail(){
        return "\n}\n";
    }

    /**
     * 生成成员变量
     * @param tableNameFUDTU
     * @return
     */
    private static String genMember(String tableNameFUDTU){
        return  "    @Autowired\n" +
                "    private "+tableNameFUDTU+"Service service;\n\n" +
                "    private Logger logger = LoggerFactory.getLogger(getClass());\n\n";
    }

    /**
     * 生成插入语句
     * @param tableNameFUDTU
     * @return
     */
    private static String genInsert(String tableNameFUDTU){
        return  "    @ApiOperation(value = \"插入\",httpMethod = \"POST\")\n" +
                "    @ApiResponses({\n" +
                "            @ApiResponse(code=400, message=\"IllegalParam\")\n" +
                "    })\n" +
                "    @RequestMapping(value = \"/"+tableNameFUDTU+"\",method = RequestMethod.POST)\n" +
                "    public Map<String, Object> insert(\n" +
                "            HttpServletRequest request,\n" +
                "            HttpServletResponse response,\n" +
                "            @RequestBody "+tableNameFUDTU+" entity){\n" +
                "        return service.insert(entity);\n" +
                "    }\n\n";
    }

    /**
     * 生成删除语句
     * * @param tableNameFUDTU
     * @return
     */
    private static String genDelete(String tableNameFUDTU){
        return  "    @ApiOperation(value = \"删除\",httpMethod = \"DELETE\")\n" +
                "    @ApiImplicitParams({\n" +
                "            @ApiImplicitParam(name = \"id\", value = \"ID\", required = true, dataType = \"int\",paramType = \"path\")\n" +
                "    })\n" +
                "    @ApiResponses({\n" +
                "            @ApiResponse(code=400, message=\"IllegalParam\")\n" +
                "    })\n" +
                "    @RequestMapping(value = \"/"+tableNameFUDTU+"/{id}\",method = RequestMethod.DELETE)\n" +
                "    public Map<String, Object> delete(\n" +
                "            @PathVariable(\"id\") Integer id,\n" +
                "            HttpServletRequest request,\n" +
                "            HttpServletResponse response){\n" +
                "        return service.delete"+tableNameFUDTU+"(id);\n" +
                "    }\n\n";
    }

    /**
     * 生成更新语句
     * @param tableNameFUDTU
     * @return
     */
    private static String genUpdate(String tableNameFUDTU){
        return  "    @ApiOperation(value = \"修改\",httpMethod = \"PUT\")\n" +
                "    @ApiResponses({\n" +
                "            @ApiResponse(code=400, message=\"IllegalParam\")\n" +
                "    })\n" +
                "    @RequestMapping(value = \"/"+tableNameFUDTU+"\",method = RequestMethod.PUT)\n" +
                "    public Map<String, Object> update(\n" +
                "            HttpServletRequest request,\n" +
                "            HttpServletResponse response,\n" +
                "            @RequestBody "+tableNameFUDTU+" entity){\n" +
                "        return service.update(entity);\n" +
                "    }\n\n";
    }

    /**
     * 生成查询语句（单条）
     * @param tableNameFUDTU
     * @return
     */
    private static String genSelect(String tableNameFUDTU){
        return  "    @ApiOperation(value = \"按ID查询\",httpMethod = \"GET\")\n" +
                "    @ApiImplicitParams({\n" +
                "            @ApiImplicitParam(name = \"id\", value = \"ID\", required = true, dataType = \"int\",paramType = \"path\")\n" +
                "    })\n" +
                "    @ApiResponses({\n" +
                "            @ApiResponse(code=400, message=\"IllegalParam\")\n" +
                "    })\n" +
                "    @RequestMapping(value = \"/"+tableNameFUDTU+"/{id}\",method = RequestMethod.GET)\n" +
                "    public Map<String, Object> select(\n" +
                "            @PathVariable(\"id\") Integer id,\n" +
                "            HttpServletRequest request,\n" +
                "            HttpServletResponse response){\n" +
                "        return service.select(id);\n" +
                "    }\n\n";
    }

    /**
     * 生成查询语句（多条）
     * @return
     */
    private static String genSelectList(){
        return  "    @ApiOperation(value = \"查询\",httpMethod = \"GET\")\n" +
                "    @ApiImplicitParams({\n" +
                "    })\n" +
                "    @ApiResponses({\n" +
                "            @ApiResponse(code=400, message=\"IllegalParam\")\n" +
                "    })\n" +
                "    @RequestMapping(value = \"/\",method = RequestMethod.GET)\n" +
                "    public Map<String, Object> selects(\n" +
                "            HttpServletRequest request,\n" +
                "            HttpServletResponse response){\n" +
                "        Map<String,Object> params = new HashMap<>();\n" +
                "        return service.selects(params);\n" +
                "    }\n\n";
    }
}
