app.service("blogService", function ($http) {

    // 分页搜索
    this.search = function (num, size, searchEntity) {
        return $http.post("../admin/blogs/search?num="+ num +"&size=" + size, searchEntity)
    };

    // 添加
    this.add = function (entity) {
        return $http.post("../admin/blogs", entity);
    };

    // 发布
    this.publish = function (entity) {
        return $http.post("../admin/blogs/publish", entity);
    };

    // 修改
    this.update = function (entity) {
        return $http.put("../admin/blogs", entity);
    };

    // 根据id查询
    this.findOne = function (id) {
        return $http.get("../admin/blogs/" + id);
    };

    // 根据id删除
    this.delete = function (id) {
        return $http.delete("../admin/blogs/" + id);
    };

    // 根据id恢复
    this.recover = function (id) {
        return $http.put("../admin/blogs/" + id);
    }

});