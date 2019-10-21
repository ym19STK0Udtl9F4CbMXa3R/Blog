app.controller("userController", function ($scope, $location, $controller, userService, loginService, uploadService) {

    $controller("baseController", {$scope: $scope});
    $controller("loginController", {$scope: $scope});

    // 错误信息
    $scope.errorMessage = null;

    // 判断用户类型
    $scope.userType = function(type){
        if (type === true) {
            return '管理员';
        }
        return '用户';
    };

    // 获取全部用户
    $scope.findAll = function () {
        userService.findAll().success(
            function (response) {
                $scope.list = response;
            }
        )
    };

    $scope.searchEntity = {'isEffective' : true};

    //	分页条件查询
    $scope.search = function (num, size) {
        userService.search(num, size, $scope.searchEntity).success(
            function (response) {
                $scope.list = response.rows;	//显示当前页数据
                $scope.paginationConf.totalItems = response.total;		//更新总记录数
            }
        )
    };

    // 根据id获取用户
    $scope.findOne = function(){
        var id = $location.search()["id"];
        if (id != null){
            userService.findOne(id).success(
                function (response) {
                    $scope.entity = response;
                }
            )
        }
    };

    $scope.entity = {'chatHead': '', "email": ''};
    // 添加或修改用户
    $scope.save = function () {
        var serviceObject;
        if ($scope.entity.id != null) {
            serviceObject = userService.update($scope.entity);
        }else {
            serviceObject = userService.add($scope.entity);
        }
        console.info($scope.entity);
        serviceObject.success(
            function (response) {
                if (response.code === 200){
                    // $scope.successMesg = "操作成功";
                    alert("操作成功");
                    location.href = "users.html";
                }else {
                    $scope.errorMessage = response.message;
                }
                $scope.entity = {};	//清空
            }
        )
    };

    // 根据id删除用户
    $scope.delete = function (id) {
        userService.delete(id).success(
            function (response) {
                if (response.code === 200){
                    $scope.successMesg = "冻结成功";
                    $scope.reloadList();
                }else {
                    $scope.errorMessage = response.message;
                }
            }
        )
    };

    // 根据id恢复用户
    $scope.recover = function (id) {
        userService.recover(id).success(
            function (response) {
                if (response.code === 200){
                    $scope.successMesg = "恢复成功";
                    $scope.reloadList();
                }else {
                    $scope.errorMessage = response.message;
                }
            }
        )
    };

    // 头像上传
    $scope.uploadFile=function () {
        uploadService.uploadFile().success(
            function (response) {
                if (response.code === 200){
                    $scope.entity.chatHead = response.message;
                } else {
                    $scope.errorMessage = response.message;
                }
            }).error(function () {
            $scope.errorMessage = "上传发生错误";
        });
    }

});