app.controller("particularController", function ($scope, $sce, $location, particularService) {

    $scope.findBlogParticulars = function () {
        var id = $location.search()["id"];
        if (id != null){
            particularService.findBlogParticulars(id).success(
                function (response) {
                    $scope.entity = response;
                    $scope.content = $sce.trustAsHtml($scope.entity.articleContent.content);
                }
            )
        }
    };

    // 所有有效标签
    $scope.findValidTag = function () {
        particularService.findValidTag().success(
            function (response) {
                $scope.tagList = response;
                $scope.tagList.size = response.length;
            }
        )
    };

    // 通过标签id查找标签名称
    $scope.findNameById = function (id) {
        for (var i = 0; i < $scope.tagList.length; i++){
            if ($scope.tagList[i].id === id){
                return $scope.tagList[i].name;
            }
        }
        return null;
    };

});