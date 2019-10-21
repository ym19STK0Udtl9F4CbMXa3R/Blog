app.controller("searchController", function ($scope, $location, $controller, searchService) {

    $controller("baseController", {$scope: $scope});

    $scope.searchData = "";
    //	分页条件查询
    $scope.search = function (num, size) {
        searchService.search(num, size, $scope.searchData).success(
            function (response) {
                $scope.list = response.rows;	//显示当前页数据
                $scope.paginationConf.totalItems = response.total;		//更新总记录数
            }
        )
    };

    // 所有有效分类
    $scope.findValidType = function () {
        searchService.findValidType().success(
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

    $scope.initSearchData = function(){
        var searchData = $location.search()["searchData"];
        if (searchData != null){
            $scope.searchData = searchData;
        }
    };

});