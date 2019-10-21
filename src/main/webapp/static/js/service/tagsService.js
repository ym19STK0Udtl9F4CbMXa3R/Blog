app.service("tagsService", function ($http) {

    // 分页搜索
    this.search = function (tagId, num, size) {
        return $http.post("../blogPage2?num="+ num +"&size=" + size, tagId)
    };

    // 获取所有有效标签
    this.findValidTag = function () {
        return $http.get("../admin/tags");
    };
    
    this.findArticleTag = function (articleIds) {
        return $http.post("../findArticleTag", articleIds)
    }

});