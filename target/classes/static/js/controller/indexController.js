app.controller("indexController", function ($scope, $controller, indexService) {

    $controller("baseController", {$scope: $scope});
    $controller("typeController", {$scope: $scope});

    //	分页条件查询
    $scope.search = function (num, size) {
        indexService.search(num, size).success(
            function (response) {
                $scope.list = response.rows;	//显示当前页数据
                $scope.paginationConf.totalItems = response.total;		//更新总记录数
            }
        )
    };

    $scope.typeTop = function () {
        indexService.typeTop().success(
            function (response) {
                $scope.typeTopList = response;
            }
        )
    };

    $scope.tagTop = function () {
        indexService.tagTop().success(
            function (response) {
                $scope.tagTopList = response;
            }
        )
    };

    $scope.blogTop = function () {
        indexService.blogTop().success(
            function (response) {
                $scope.blogTopList = response;
            }
        )
    };

});