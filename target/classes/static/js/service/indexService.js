app.service("indexService", function ($http) {

    // 分页查询
    this.search = function (num, size) {
        return $http.get("../blogPage?num="+ num +"&size=" + size)
    };

    // 分类top
    this.typeTop = function () {
        return $http.get("../typeTop")
    };

    // 标签top
    this.tagTop = function () {
        return $http.get("../tagTop")
    };

    // 文章top
    this.blogTop = function () {
        return $http.get("../blogTop")
    };


});