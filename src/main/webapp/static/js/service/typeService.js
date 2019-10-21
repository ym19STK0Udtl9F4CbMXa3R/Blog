app.service("typeService", function ($http) {


    // 分页搜索
    this.search = function (num, size, searchEntity) {
        return $http.post("../admin/types/search?num="+ num +"&size=" + size, searchEntity)
    };

    // 添加
    this.add = function (entity) {
        return $http.post("../admin/types", entity);
    };

    // 修改
    this.update = function (entity) {
        return $http.put("../admin/types", entity);
    };

    // 根据id查询
    this.findOne = function (id) {
        return $http.get("../admin/types/" + id);
    };

    // 根据id删除
    this.delete = function (id) {
        return $http.delete("../admin/types/" + id);
    };

    // 根据id恢复
    this.recover = function (id) {
        return $http.put("../admin/types/" + id);
    };

    // 获取所有有效分类
    this.findValidType = function () {
        return $http.get("../admin/types");
    };

});