# core模块

这是jbot的核心模块,它负责生成目标项目

## 包含内容

```lua
core
├── before ── 负责core开始前的准备工作
|   ├── 建立请求的model,完成数据初始化
|   ├── 生成项目结构
|   ├── 复制model项目的dockerfile、pom.xml、startup.sh、readme.md文件
|   ├── 复制model项目的application.properties、logback.xml文件
├── work ── 生成目标项目
|   ├── 生成mapper层文件
|   ├── 生成dao、entity、rest、service、serviceImpl层文件
|   ├── 生成doc文档层文件
|   ├── 使用替换的方式完成生成其他文件
├── after ── 负责core结束后的善后工作
|   ├── 把生成的目标项目压缩为zip文件
```

## 技术选型
```lua
线程池：FixedThreadPool + Future
```
