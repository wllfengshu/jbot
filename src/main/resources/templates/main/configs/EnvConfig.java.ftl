package ${packageName}.${projectName}.configs;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 服务环境的统一配置，服务中使用的环境变量都要在此类中定义
 *
 * @author
 */
@Slf4j
@Data
@Order(0)
@Component
public class EnvConfig implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("程序环境变量:{}", this.toString());
        log.info("系统环境变量:{}", JSON.toJSONString(System.getenv()));
    }
}

