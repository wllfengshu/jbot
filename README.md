# java项目生成器

[![GitHub stars](https://img.shields.io/github/stars/wllfengshu/jbot.svg?style=social&label=Stars)](https://github.com/wllfengshu/jbot)

## 项目介绍

jbot项目可以一键生成 swagger + springBoot + spring + mybatis 项目

## 项目结构
```lua
jbot
├── web ── web模块
├── core ── 核心模块
├── model ── 模板模块
├── common ── 公共组件模块
```

## 技术选型

### 后端技术:
技术 | 名称
----|------
Spring Framework | 容器  
SpringMVC | MVC框架
MyBatis | ORM框架
Druid | 数据库连接池
Swagger2 | 接口测试框架
Maven | 项目构建管理

#### 前端技术:
技术 | 名称 
----|------
jQuery | 函式库
Bootstrap | 前端框架
AngularJS | JS框架

## 架构图

![架构图](https://raw.githubusercontent.com/wllfengshu/image/master/jbot/framework.png)

## 模块依赖

![模块依赖](https://raw.githubusercontent.com/wllfengshu/image/master/jbot/dependent.png)

## 模块介绍

> **common**
- common：公共组件
> **core**
- core：负责把模板项目变为目标项目
> **model**
- model：提供目标项目的模板
> **web**
- web：提供对外web接口

## 环境搭建

### 开发工具:
- MySql: 数据库
- Tomcat: 应用服务器

### 开发环境：
- Jdk8+
- Mysql5.5+
- Maven3.5+

### 资源下载
- JDK8 [https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html "JDK8")
- Maven [http://maven.apache.org/download.cgi](http://maven.apache.org/download.cgi "Maven")
- Tomcat [https://tomcat.apache.org/download-80.cgi](https://tomcat.apache.org/download-80.cgi "Tomcat")

## 目标项目规范约定
```lua
1、生成的entity、dao、service、serviceImpl、rest分别放到对应的包里，文件名称为表名首字母大写、去掉下划线、驼峰结构；
2、生成的entity的属性名为表字段名的驼峰形式；
3、serviceImpl放的在service包里的impl包里；
4、生成的rest包含swagger，可以使用http://localhost:8080/swagger-ui.html访问；
5、生成的每张表都对应“增删改查”的方法，可以直接使用；
```

## 演示地址

演示地址：

## 预览图

- 使用http://localhost:8080/swagger-ui.html 访问

![swagger](https://raw.githubusercontent.com/wllfengshu/image/master/jbot/swagger.png)

- 使用http://localhost:8080/index.html 访问

![index](https://raw.githubusercontent.com/wllfengshu/image/master/jbot/web.png)

- 生成的项目的效果图（使用http://localhost:8080/swagger-ui.html 访问）

![index](https://raw.githubusercontent.com/wllfengshu/image/master/jbot/genSwagger.png)

- 生成的项目结构

![index](https://raw.githubusercontent.com/wllfengshu/image/master/jbot/genProject.png)

- 生成的项目entity

![index](https://raw.githubusercontent.com/wllfengshu/image/master/jbot/genEntity.png)

- 生成的项目mapper

![index](https://raw.githubusercontent.com/wllfengshu/image/master/jbot/genMapper.png)

## jbot相关博客
