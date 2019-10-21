app.controller("typeController", function ($scope, $location, $controller, typeService) {

    $controller("baseController", {$scope: $scope});
    $controller("loginController", {$scope: $scope});

    $scope.searchEntity = {'isEffective' : true};
    //	分页条件查询
    $scope.search = function (num, size) {
        typeService.search(num, size, $scope.searchEntity).success(
            function (response) {
                $scope.list = response.rows;	//显示当前页数据
                $scope.paginationConf.totalItems = response.total;		//更新总记录数
            }
        )
    };

    // 根据id获取分类
    $scope.findOne = function(){
        var id = $location.search()["id"];
        if (id != null){
            typeService.findOne(id).success(
                function (response) {
                    $scope.entity = response;
                }
            )
        }
    };

    // 添加或修改分类
    $scope.save = function () {
        var serviceObject;
        if ($scope.entity.id != null) {
            serviceObject = typeService.update($scope.entity);
        }else {
            serviceObject = typeService.add($scope.entity);
        }
        serviceObject.success(
            function (response) {
                if (response.code === 200){
                    // $scope.successMesg = "操作成功";
                    alert("操作成功");
                    location.href = "types.html";
                    $scope.entity = {};	//清空
                }else {
                    $scope.errorMesg = response.message;
                }
            }
        )
    };

    // 根据id删除分类
    $scope.delete = function (id) {
        typeService.delete(id).success(
            function (response) {
                if (response.code === 200){
                    $scope.successMesg = "删除成功";
                    $scope.reloadList();
                }else {
                    $scope.errorMesg = response.message;
                }
            }
        )
    };

    // 根据id恢复分类
    $scope.recover = function (id) {
        typeService.recover(id).success(
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

    // 所有有效分类
    $scope.findValidType = function () {
        typeService.findValidType().success(
            function (response) {
                $scope.typeList = response;
            }
        )
    };

    // 通过分类id查找分类名称
    $scope.findNameById = function (id) {
        for (var i = 0; i < $scope.typeList.length; i++){
          if ($scope.typeList[i].id === id){
              return $scope.typeList[i].name;
          }
        }
        return null;
    }

});