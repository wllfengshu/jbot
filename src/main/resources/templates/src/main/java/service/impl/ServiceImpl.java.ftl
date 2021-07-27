package ${packageName}.${projectName}.service.impl;

import ${packageName}.${projectName}.dao.${tableName4FUH}DAO;
import ${packageName}.${projectName}.entity.${tableName4FUH}Entity;
import ${packageName}.${projectName}.exception.CustomException;
import ${packageName}.${projectName}.service.${tableName4FUH}Service;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ${tableName4FUH}ServiceImpl implements ${tableName4FUH}Service {

    @NonNull
    private ${tableName4FUH}DAO ${tableName4FLH}DAO;

    @Override
    public Map<String, Object> insert(${tableName4FUH}Entity entity, String sessionId) throws CustomException {
        log.info("insert entity:{}", entity);
        Map<String, Object> result = new HashMap<>();
        ${tableName4FLH}DAO.insertSelective(entity);
        return result;
    }

    @Override
    public Map<String, Object> delete(Integer id, String sessionId) throws CustomException {
        log.info("delete id:{}", id);
        Map<String, Object> result = new HashMap<>();
        ${tableName4FLH}DAO.deleteByPrimaryKey(id);
        return result;
    }

    @Override
    public Map<String, Object> update(${tableName4FUH}Entity entity, String sessionId) throws CustomException {
        log.info("update entity:{}", entity);
        Map<String, Object> result = new HashMap<>();
        ${tableName4FLH}DAO.updateByPrimaryKey(entity);
        return result;
    }

    @Override
    public Map<String, Object> select(Integer id, String sessionId) throws CustomException {
        log.info("select id:{}", id);
        Map<String, Object> result = new HashMap<>();
        result.put("data", ${tableName4FLH}DAO.selectByPrimaryKey(id));
        return result;
    }

    @Override
    public Map<String, Object> selects(Map<String, Object> params, String sessionId) throws CustomException {
        log.info("selects params:{}", params);
        Map<String, Object> result = new HashMap<>();
        return result;
    }

}
