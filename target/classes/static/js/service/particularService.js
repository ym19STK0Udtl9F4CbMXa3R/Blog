app.service("particularService", function ($http) {

    // 根据id查询
    this.findBlogParticulars = function (id) {
        return $http.get("../blog/" + id);
    };

    // 获取所有有效标签
    this.findValidTag = function () {
        return $http.get("../admin/tags");
    };

});