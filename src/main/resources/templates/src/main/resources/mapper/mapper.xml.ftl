<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${packageName}.${projectName}.dao.${tableName4FUH}DAO" >

    <resultMap id="baseResultMap" type="${packageName}.${projectName}.entity.${tableName4FUH}Entity">
    <#list fields as field>
        <result column="${field.fieldName}" property="${stringUtil.underlineToHump(field.fieldName)}"/>
    </#list>
    </resultMap>

    <sql id="baseQueryWhere">

    </sql>

</mapper>