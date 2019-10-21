app.service("typesService", function ($http) {

    // 分页搜索
    this.search = function (num, size, searchEntity) {
        return $http.post("../blogPage?num="+ num +"&size=" + size, searchEntity)
    };

    // 获取所有有效分类
    this.findValidType = function () {
        return $http.get("../admin/types");
    };

});