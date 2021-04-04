server:
  port: 8080
  servlet:
    context-path: /${projectName}
spring:
  application:
    name: ${projectName}
  datasource:
    url: <#noparse>${</#noparse>db_url:jdbc:mysql://localhost:3306/test<#noparse>}</#noparse>
    username: <#noparse>${</#noparse>db_username:root<#noparse>}</#noparse>
    password: <#noparse>${</#noparse>db_password:root<#noparse>}</#noparse>
    driver-class-name: com.mysql.jdbc.Driver
    type: com.alibaba.druid.pool.DruidDataSource
  druid:
    initial-size: 5
    min-idle: 5
    max-active: 20
    max-wait: 60000
    time-between-eviction-runs-millis: 60000
    min-evictable-idle-time-millis: 300000
    validation-query: SELECT 1
    test-while-idle: true
    test-on-borrow: false
    test-on-return: false
    pool-prepared-statements: true
    max-pool-prepared-statement-per-connection-size: 20
    filters: stat,wall,log4j
    use-global-data-source-stat: true
    connection-properties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=500
mybatis:
  mapper-locations: classpath:/mapper/*.xml
  type-aliases-package: ${packageName}.${projectName}.entity
  configuration:
    map-underscore-to-camel-case: true
mapper:
  mappers: ${packageName}.${projectName}.utils.MapperUtil
  not-empty: false
  identity: MYSQL
pagehelper:
  helper-dialect: mysql
  reasonable: true
  params: count=countSql
  support-methods-arguments: true
logging:
  level:
    ${packageName}.${projectName}.dao: debug




