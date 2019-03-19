# web模块

这是jbot的web服务模块,它提供restful接口和前端页面

## 包含内容

```lua
web
├── setting ── 负责设置数据库信息，并返回表信息
├── produce ── 开始生成目标项目
```

## 技术选型
```lua
MVC框架：Spring boot
容器：Spring
ORM框架：Mybatis
数据库连接池：Druid
接口测试框架：Swagger2

前端布局框架：Bootstrap
前端JS框架：AngularJS + jQuery
```

## 接口文档

### 1 设置项目信息
`POST /jbot/setting/`

#### 1.1 请求参数

参数名 | 类型 |是否必须 | 位置 | 描述 | 举例 | 备注
---- | ---- | ---- | ---- | ---- | ---- | ----
connectInfo | json | 是 | body | 数据库连接实体类（数据库连接信息） | `{"dbIp":"127.0.0.1","dbPort":"3306","dbName":"test","dbUsername":"root","dbPassword":"root","dbDriver":"com.mysql.jdbc.Driver"}` | 

#### 1.2 响应参数

参数名 | 类型 | 是否必须 | 描述 | 备注
---- | ---- | ---- | ---- | ----
dbName | string | 是 | 数据库名
tableName | string | 是 | 表名 |
fieldName | string | 是 | 字段名称（eg:username） |
fieldType | string | 是 | 字段类型（eg:varchar） |
columnComment | string | 是 | 列注释（eg:用户名） |
isNullable | string | 是 | 是否允许为空（eg:NO） |
columnType | string | 是 | 列类型（eg:varchar(32)） |
columnKey | string | 是 | 列约束（eg:PRI主键约束;UNI唯一约束;MUL可以重复） |
tables | array | 是 | 表的集合 |
fields | array | 是 | 字段的集合 |

#### 1.3 请求响应示例

##### (1)request url
`POST http://localhost:8080/jbot/setting`

##### (2)request headers
```json
{
  "Accept": "*/*",
  "Content-Type": "application/json;charset=utf-8"
}
```

##### (3)request payload
```json
{
    "dbIp": "127.0.0.1",
    "dbPort": "3306",
    "dbName": "test",
    "dbUsername": "root",
    "dbPassword": "root",
    "dbDriver": "com.mysql.jdbc.Driver"
}
```

##### (4)response body
```json
{
  "data": [
    {
      "dbName": "test",
      "tables": [
        {
          "tableName": "t_user",
          "fields": [
            {
              "fieldName": "id",
              "fieldType": "int"
            },
            {
              "fieldName": "username",
              "fieldType": "varchar"
            },
            {
              "fieldName": "password",
              "fieldType": "varchar"
            },
            {
              "fieldName": "create_time",
              "fieldType": "datetime"
            },
            {
              "fieldName": "update_time",
              "fieldType": "datetime"
            },
            {
              "fieldName": "delete_time",
              "fieldType": "datetime"
            },
            {
              "fieldName": "is_delete",
              "fieldType": "int"
            }
          ]
        }
      ]
    }
  ]
}
```

#### 1.4 异常示例

```json
{
  "isSuccess": false,
  "msg": "项目名不能为关键字"
}
```

#### 1.5 业务错误码

错误码 | 描述 | 解决方案
--- | --- | ---
410 | 项目名不能为关键字 | 检查项目名
411 | 包名不能为关键字 | 检查包名
412 | 生成项目失败 | 请刷新浏览器重试


### 2 生成目标项目
`POST /jbot/produce/`

#### 2.1 请求参数

参数名 | 类型 |是否必须 | 位置 | 描述 | 举例 | 备注
---- | ---- | ---- | ---- | ---- | ---- | ----
projectName | string | 是 | query | 项目名 | jbot | 不能是关键字
packageName | string | 是 | query | 包名（不包含项目名） | com.wllfengshu | 不能是关键字
dbInfo | json | 是 | body | 数据库实体类（选择的表的集合） | `{"dbName":"test","tables":[{"fields":[{"fieldName":"id","fieldType":"int"}],"tableName":"t_user"}]}` | 

#### 2.2 响应参数

参数名 | 类型 | 是否必须 | 描述 | 备注
---- | ---- | ---- | ---- | ----
isSuccess | bool | 否 | 是否成功 | 仅失败时有，成功直接返回“项目名.zip”的文件流
msg | string | 否 | 错误信息 | 仅失败时有，成功直接返回“项目名.zip”的文件流

#### 2.3 请求响应示例

##### (1)request url
`POST http://localhost:8080/jbot/produce?projectName=jbot&packageName=com.wllfengshu`

##### (2)request headers
```json
{
  "Accept": "*/*",
  "Content-Type": "application/json;charset=utf-8"
}
```

##### (3)request payload
```json
{
    "dbName": "test",
    "tables": [
        {
            "tableName": "t_user",
            "fields": [
                {
                    "fieldName": "id",
                    "fieldType": "int"
                },
                {
                    "fieldName": "username",
                    "fieldType": "varchar"
                },
                {
                    "fieldName": "password",
                    "fieldType": "varchar"
                },
                {
                    "fieldName": "create_time",
                    "fieldType": "datetime"
                },
                {
                    "fieldName": "update_time",
                    "fieldType": "datetime"
                },
                {
                    "fieldName": "delete_time",
                    "fieldType": "datetime"
                },
                {
                    "fieldName": "is_delete",
                    "fieldType": "int"
                }
            ]
        }
    ]
}
```

##### (4)response body
```
“项目名.zip”的文件流
```

#### 1.4 异常示例

```json
{
  "isSuccess": false,
  "msg": "项目名不能为关键字"
}
```

#### 1.5 业务错误码

错误码 | 描述 | 解决方案
--- | --- | ---
410 | 项目名不能为关键字 | 检查项目名
411 | 包名不能为关键字 | 检查包名
412 | 生成项目失败 | 请刷新浏览器重试