app.controller("commentController", function ($scope, $location, $controller, commentService, loginService) {

    $controller("particularController", {$scope: $scope});

    // $scope.comment = {"chatHead": ""};
    $scope.save = function () {
        var id = $location.search()["id"];
        if (id != null){
            $scope.comment.articleId = id;
        }
        commentService.add($scope.comment).success(
            function (response) {
                if (response.code === 200){
                    alert("评论成功");
                    location.reload();
                }else {
                    alert("评论失败")
                }
            }
        );
    };

    $scope.findComment = function () {
        var id = $location.search()["id"];
        if (id != null){
            commentService.find(id).success(
                function (response) {
                    $scope.comments = response;
                }
            );
        }
    };

    $scope.comment = {"nikeName": "", "email": "", "parentId": "", "parentNikeName": ""};
    $scope.reply = function (parentId, parentNickname) {
        $("[name='content']").attr("placeholder", "@"+parentNickname).focus();
        $scope.comment.parentId = parentId;
        $scope.comment.parentNikeName = parentNickname;
        console.info(parentId, parentNickname);
        $(window).scrollTo($('#comment-form'),500);
    };

    // 获取登录账号的信息
    $scope.getLoginInfo = function () {
        loginService.getLoginInfo().success(
            function (response) {
                $scope.comment.nikeName = response.name;
                $scope.comment.email = response.email;
            }
        )
    };

});