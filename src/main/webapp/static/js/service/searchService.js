app.service("searchService", function ($http) {

    // 分页搜索
    this.search = function (num, size, searchData) {
        return $http.get("../search?searchData="+ searchData +"&num="+ num +"&size=" + size)
    };

    // 获取所有有效分类
    this.findValidType = function () {
        return $http.get("../admin/types");
    };

});