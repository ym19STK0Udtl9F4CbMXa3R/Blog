app.controller("logController", function ($scope, $controller, logService) {

    $controller("baseController", {$scope: $scope});
    $controller("loginController", {$scope: $scope});


    $scope.searchEntity = {"ip" : ''};
    //	分页条件查询
    $scope.search = function (num, size) {
        logService.search(num, size, $scope.searchEntity).success(
            function (response) {
                $scope.list = response.rows;	//显示当前页数据
                $scope.paginationConf.totalItems = response.total;		//更新总记录数
            }
        )
    };

});