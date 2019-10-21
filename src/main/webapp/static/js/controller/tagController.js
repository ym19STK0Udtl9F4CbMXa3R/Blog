app.controller("tagController", function ($scope, $location, $controller, tagService) {

    $controller("baseController", {$scope: $scope});
    $controller("loginController", {$scope: $scope});

    $scope.searchEntity = {'isEffective' : true};
    //	分页条件查询
    $scope.search = function (num, size) {
        tagService.search(num, size, $scope.searchEntity).success(
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
            tagService.findOne(id).success(
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
            serviceObject = tagService.update($scope.entity);
        }else {
            serviceObject = tagService.add($scope.entity);
        }
        serviceObject.success(
            function (response) {
                if (response.code === 200){
                    // $scope.successMesg = "操作成功";
                    alert("操作成功");
                    location.href = "tags.html";
                    $scope.entity = {};	//清空
                }else {
                    $scope.errorMesg = response.message;
                }
            }
        )
    };

    // 根据id删除分类
    $scope.delete = function (id) {
        tagService.delete(id).success(
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
        tagService.recover(id).success(
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

    // 所有有效标签
    $scope.findValidTag = function () {
        tagService.findValidTag().success(
            function (response) {
                var tagData = {data:[]};
                for (var i = 0; i < response.length; i++){
                    tagData.data.push({"id": response[i].id, "text": response[i].name})
                }
                $scope.tagList = tagData;
            }
        )
    }

});