package ${packageName}.${projectName}.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangll
 */
@Slf4j
@Api(tags = "健康管理")
@RestController
@RequestMapping("/health")
@RequiredArgsConstructor
public class HealthRest {

    @ApiOperation(value = "健康检查", httpMethod = "GET", notes = "可用")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity health() {
        log.debug("收到健康检查请求");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

