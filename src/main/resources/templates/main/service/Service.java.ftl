package ${packageName}.${projectName}.service;

import ${packageName}.${projectName}.entity.${tableName4FUH}Entity;
import ${packageName}.${projectName}.exception.CustomException;

import java.util.Map;

public interface ${tableName4FUH}Service {

    Map<String, Object> insert(${tableName4FUH}Entity entity, String sessionId) throws CustomException;

    Map<String, Object> delete(Integer id, String sessionId) throws CustomException;

    Map<String, Object> update(${tableName4FUH}Entity entity, String sessionId) throws CustomException;

    Map<String, Object> select(Integer id, String sessionId) throws CustomException;

    Map<String, Object> selects(Map<String, Object> params, String sessionId) throws CustomException;

}
