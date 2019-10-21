app.service("userService", function ($http) {

    // 获取全部用户
    this.findAll = function () {
        return $http.get("../admin/users/findAll");
    };

    // 分页搜索
    this.search = function (num, size, searchEntity) {
        return $http.post("../admin/users/search?num="+ num +"&size=" + size, searchEntity)
    };

    // 添加
    this.add = function (entity) {
        return $http.post("../admin/users", entity);
    };

    // 修改
    this.update = function (entity) {
        return $http.put("../admin/users", entity);
    };

    // 根据id查询
    this.findOne = function (id) {
        return $http.get("../admin/users/" + id);
    };

    // 根据id删除
    this.delete = function (id) {
        return $http.delete("../admin/users/" + id);
    };

    // 根据id恢复
    this.recover = function (id) {
        return $http.put("../admin/users/" + id);
    }


});