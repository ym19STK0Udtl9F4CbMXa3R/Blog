app.controller("tagsController", function ($scope,$location, $controller, tagsService) {

    $controller("baseController", {$scope: $scope});
    $controller("typesController", {$scope: $scope});

    $scope.tagId = '0';

    //	分页条件查询
    $scope.search = function (num, size) {
        tagsService.search($scope.tagId, num, size).success(
            function (response) {
                $scope.list = response.rows;	//显示当前页数据
                $scope.paginationConf.totalItems = response.total;		//更新总记录数

                $scope.articleIds = [];
                for (var i = 0; i < $scope.list.length; i++){
                    $scope.articleIds.push($scope.list[i].id)
                }
                if ($scope.articleIds.length > 0){
                    $scope.findArticleTag();
                }
            }
        )
    };

    // 获取文章的标签
    $scope.findArticleTag = function(){
        tagsService.findArticleTag($scope.articleIds).success(
            function (response) {
                $scope.articleTags = response;
            }
        );
    };

    // 所有有效标签
    $scope.findValidTag = function () {
        tagsService.findValidTag().success(
            function (response) {
                $scope.tagList = response;
                $scope.tagList.size = response.length;
            }
        )
    };

    $scope.initTag = function(){
        var id = $location.search()["id"];
        if (id != null){
            $scope.tagId = id;
        }
    };

    $scope.changeSearchTag = function (id) {
        $scope.tagId = id;
    };

});