package ${packageName}.${projectName}.service.impl;

import ${packageName}.${projectName}.dao.${tableName4FUH}DAO;
import ${packageName}.${projectName}.entity.${tableName4FUH}Entity;
import ${packageName}.${projectName}.exception.CustomException;
import ${packageName}.${projectName}.service.${tableName4FUH}Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ${tableName4FUH}ServiceImpl implements ${tableName4FUH}Service {

    @Autowired
    private ${tableName4FUH}DAO ${tableName4FLH}DAO;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Map<String, Object> insert(${tableName4FUH}Entity entity, String sessionId) throws CustomException {
        logger.info("insert entity:{}", entity);
        Map<String, Object> result = new HashMap<>();
        ${tableName4FLH}DAO.insert(entity);
        return result;
    }

    @Override
    public Map<String, Object> delete(Integer id, String sessionId) throws CustomException {
        logger.info("delete id:{}", id);
        Map<String, Object> result = new HashMap<>();
        ${tableName4FLH}DAO.deleteByPrimaryKey(id);
        return result;
    }

    @Override
    public Map<String, Object> update(${tableName4FUH}Entity entity, String sessionId) throws CustomException {
        logger.info("update entity:{}", entity);
        Map<String, Object> result = new HashMap<>();
        ${tableName4FLH}DAO.updateByPrimaryKey(entity);
        return result;
    }

    @Override
    public Map<String, Object> select(Integer id, String sessionId) throws CustomException {
        logger.info("select id:{}", id);
        Map<String, Object> result = new HashMap<>();
        result.put("data", ${tableName4FLH}DAO.selectByPrimaryKey(id));
        return result;
    }

    @Override
    public Map<String, Object> selects(Map<String, Object> params, String sessionId) throws CustomException {
        logger.info("selects params:{}", params);
        Map<String, Object> result = new HashMap<>();

        return result;
    }

}
