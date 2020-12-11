var app=angular.module('jbot',[]);
var baseUrl="/jbot/v2";

//添加自定义拦截器
app.config(["$httpProvider", function ($httpProvider) {
    $httpProvider.interceptors.push('loadingInterceptor');
    $httpProvider.interceptors.push('errorInterceptor');
 }]);

//自定义拦截器 --加载loading蒙版
app.factory('loadingInterceptor', function($rootScope) {
    var timestampMarker = {
        request: function (config) {
            $("#loading").show();
            return config;
        },
        response: function (response) {
            $("#loading").hide();
            return response;
        }
    };
    return timestampMarker;
});

//自定义拦截器 --统一异常处理
app.factory('errorInterceptor',function($q){
    return {
        request: function(config) {
            return config;
        },
        requestError: function(err) {
            return $q.reject(err);
        },
        response: function(res) {
            return res;
        },
        responseError: function(err) {
            if(400 === err.status){
                var _data=err.data instanceof ArrayBuffer?JSON.parse(String.fromCharCode.apply(null, new Uint8Array(err.data))):err.data;
                switch (_data["errorCode"]){
                    case 10001:
                        alert("数据库地址不合法");
                        break;
                    case 10002:
                        alert("数据库端口不合法");
                        break;
                    case 10003:
                        alert("数据库名不合法");
                        break;
                    case 10004:
                        alert("数据库用户名不合法");
                        break;
                    case 10005:
                        alert("数据库密码不合法");
                        break;
                    case 10006:
                        alert("项目名不合法");
                        break;
                    case 10007:
                        alert("包名不合法");
                        break;
                    case 10008:
                        alert("生成项目失败");
                        break;
                    case 10009:
                        alert("从用户数据库中获取表信息异常");
                        break;
                    case 10010:
                        alert("下载文件异常");
                        break;
                    case 10011:
                        alert("替换文件内容异常");
                        break;
                    case 10012:
                        alert("压缩文件异常");
                        break;
                    default:
                        alert("操作失败，请稍后重试...");
                }
            }else{
                alert("系统异常，请稍后重试...");
            }
            $("#loading").hide();
            return $q.reject(err);
        }
    };
});

//控制器
app.controller('jbotController',function($scope,$http){

    //初始化项目
    $scope.initProjectFun=function(){
        $http.get(
            baseUrl+'/init'
        ).success(
            function(_data){
                var _dbInfo = _data["data"];
                if(!_dbInfo){
                    alert("获取服务器的数据库配置失败，请联系管理员！");
                    return;
                }
                $scope.dbIp=_dbInfo["dbIp"];
                $scope.dbPort=_dbInfo["dbPort"];
                $scope.dbName=_dbInfo["dbName"];
                $scope.dbUsername=_dbInfo["dbUsername"];
                $scope.dbPassword=_dbInfo["dbPassword"];
            }
        );
    };

    //设置项目
    $scope.settingProjectFun=function(){
        if(!$scope.dbIp){
            alert("数据库地址不能为空");
            return;
        }
        if(!$scope.dbPort){
            alert("数据库端口不能为空");
            return;
        }
        if(!$scope.dbName){
            alert("数据库名不能为空");
            return;
        }
        if(!$scope.dbUsername){
            alert("数据库用户名不能为空");
            return;
        }
        var _connectInfo = {
            dbIp: $scope.dbIp,
            dbPort: $scope.dbPort,
            dbName: $scope.dbName,
            dbUsername: $scope.dbUsername,
            dbPassword: $scope.dbPassword
        };
        $http.post(
            baseUrl+'/setting',
            _connectInfo
        ).success(
            function(_data){
                var _tableInfo = _data["data"];
                if(!_tableInfo){
                    alert("该数据库是空");
                    return;
                }
                $scope.todos=_tableInfo;
            }
        );
    };

    //用户勾选的表名的集合
    $scope.selectTables=[];

    //更新勾选
    $scope.updateSelection=function($event,table){
        if($event.target.checked){
            $scope.selectTables.push(table);
        }else{
            var index = $scope.selectTables.indexOf(table);
            $scope.selectTables.splice(index,1);
        }
    };

    //生成项目
    $scope.produceProjectFun=function(){
        if(!$scope.projectName){
            alert("项目名不能为空");
            return;
        }
        if(!$scope.packageName){
            alert("包名不能为空");
            return;
        }
        $http.post(
            baseUrl+'/produce?projectName='+$scope.projectName+'&packageName='+$scope.packageName,
            $scope.selectTables,
            {responseType:'arraybuffer'}
        ).success(
            function(_data){
                var blob = new Blob([_data],{type: 'application/zip'});
                var a = document.createElement("a");
                a.href = window.URL.createObjectURL(blob);
                a.download = $("#projectName").val()+'.zip';
                a.target = '_blank';
                a.click();
            }
        );
    }
});