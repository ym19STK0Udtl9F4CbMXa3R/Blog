app.controller("typesController", function ($scope,$location, $controller, typesService) {

    $controller("baseController", {$scope: $scope});

    $scope.searchEntity = {'isEffective' : true};
    //	分页条件查询
    $scope.search = function (num, size) {
        typesService.search(num, size, $scope.searchEntity).success(
            function (response) {
                $scope.list = response.rows;	//显示当前页数据
                $scope.paginationConf.totalItems = response.total;		//更新总记录数
            }
        )
    };

    // 所有有效分类
    $scope.findValidType = function () {
        typesService.findValidType().success(
            function (response) {
                $scope.typeList = response;
                $scope.typeList.size = response.length;
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
    };

    $scope.initType = function(){
        var id = $location.search()["id"];
        if (id != null){
            $scope.searchEntity.typeId = id;
        }
    };

    $scope.changeSearchType = function (id) {
        $scope.searchEntity.typeId = id;
    };

});