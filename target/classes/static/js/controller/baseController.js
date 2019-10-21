app.controller("baseController", function ($scope) {

    $scope.showData = function(data){
          alert(data);
    };

    /**
     * 分页控件配置
     * currentPage：当前页
     * totalItems：记录总数
     * itemsPerPage：每页记录数
     * perPageOptions：分页选项
     * onChange：当页码变更后自动触发的方法
     */
    $scope.paginationConf = {
        currentPage: 1,
        totalItems: 10,
        itemsPerPage: 5,
        perPageOptions: [5, 10, 20],
        onChange: function(){
            $scope.reloadList();
        }
    };

    // 刷新列表
    $scope.reloadList = function(){
        $scope.search($scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
    };

    $scope.selectIds = [];		// 用户勾选的ID集合
    //用户勾选复选框
    $scope.updateSelection = function ($event, id) {
        if ($event.target.checked){
            $scope.selectIds.push(id);		//push向集合添加元素
        } else {
            var index = $scope.selectIds.indexOf(id);		//查询值的位置
            $scope.selectIds.splice(index, 1);		//参数1：移除的位置（下标） 参数2：移除的长度
        }
    };

    $scope.stringToJson = function (jsonString, key) {
        var json = JSON.parse(jsonString);      //将字符串转换成json或集合
        var value = "";

        for (var i = 0; i < json.length ; i++) {
            // 第一个不添加
            if (i > 0){
                value += ","
            }
            value += json[i][key];
        }

        return value;
    };

    $scope.successMesg = '';
    $scope.errorMesg = '';

    $scope.closeSuccessMesg = function () {
        $scope.successMesg = '';
    };

    $scope.closeErrorMesg = function () {
        $scope.errorMesg = '';
    }

});