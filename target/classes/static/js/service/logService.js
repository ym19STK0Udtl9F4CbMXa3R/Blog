app.service("logService", function ($http) {

    // 分页搜索
    this.search = function (num, size, searchEntity) {
        return $http.post("../admin/logs/search?num=" + num + "&size=" + size, searchEntity)
    };

});