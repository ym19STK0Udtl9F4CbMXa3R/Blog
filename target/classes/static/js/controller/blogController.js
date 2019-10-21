app.controller("blogController", function ($scope, $location, $controller, uploadService, blogService) {

    $controller("baseController", {$scope: $scope});
    $controller("loginController", {$scope: $scope});
    $controller("typeController", {$scope: $scope});
    $controller("tagController", {$scope: $scope});

    $scope.searchEntity = {'isEffective' : true};
    //	分页条件查询
    $scope.search = function (num, size) {
        blogService.search(num, size, $scope.searchEntity).success(
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
            blogService.findOne(id).success(
                function (response) {
                    $scope.entity = response;
                    var contentData = response.articleContent.content;
                    $("#md-content textarea").val(contentData);
                    var tagData = [];
                    for (var i = 0; i < $scope.entity.tagIds.length; i++){
                        for (var j = 0; j < $scope.tagList.data.length; j++){
                            if ($scope.tagList.data[j].id === $scope.entity.tagIds[i]){
                                tagData.push($scope.tagList.data[j])
                            }
                        }
                    }
                    $scope.tags = tagData;
                }
            )
        }
    };

    $scope.entity = {"articleInfo":{"firstPicture": '', "flag": '原创',
            "isPublished": 'false',"isShareStatement" : 'false', "isAppreciation" : 'false', "isCommentabled": 'false', "isRecommend": 'false'},
                        "articleContent": {"content": ''},
                        "tagIds":''};

    $scope.changeFlag = function(flag){
      $scope.entity.articleInfo.flag = flag;
    };

    // 添加或修改分类
    $scope.save = function () {
        // alert($("#md-content textarea").val());
        $scope.entity.articleContent.content = $("#md-content textarea").val();
        var ids = [];
        for (var i = 0;i<$scope.tags.length; i++){
            ids.push($scope.tags[i].id)
        }
        $scope.entity.tagIds = ids;
        var serviceObject;
        if ($scope.entity.articleInfo.id != null) {
            serviceObject = blogService.update($scope.entity);
        }else {
            serviceObject = blogService.add($scope.entity);
        }
        serviceObject.success(
            function (response) {
                if (response.code === 200){
                    // $scope.successMesg = "操作成功";
                    alert("操作成功");
                    $scope.entity = {};	//清空
                    // $("#md-content textarea").val("");
                    location.href = "blogs.html";
                }else {
                    $scope.errorMesg = response.message;
                }
            }
        )
    };

    // 发布
    $scope.publish = function () {
        $scope.entity.articleContent.content = $("#md-content textarea").val();
        var ids = [];
        for (var i = 0;i<$scope.tags.length; i++){
            ids.push($scope.tags[i].id)
        }
        $scope.entity.tagIds = ids;
        blogService.publish($scope.entity).success(
            function (response) {
                if (response.code === 200){
                    // $scope.successMesg = "操作成功";
                    alert("操作成功");
                    $scope.entity = {};	//清空
                    // $("#md-content textarea").val("");
                    location.href = "blogs.html";
                }else {
                    $scope.errorMesg = response.message;
                }
            }
        )
    };

    // 根据id删除分类
    $scope.delete = function (id) {
        blogService.delete(id).success(
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
        blogService.recover(id).success(
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

    // 首图上传
    $scope.uploadFile=function () {
        uploadService.uploadFile().success(
            function (response) {
                if (response.code === 200){
                    $scope.entity.articleInfo.firstPicture = response.message;
                } else {
                    $scope.errorMessage = response.message;
                }
            }).error(function () {
            $scope.errorMessage = "上传发生错误";
        });
    }

});