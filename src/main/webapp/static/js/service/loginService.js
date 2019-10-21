app.service("loginService", function ($http) {

    // 登录
    this.login = function (entity) {
        return $http.post("../admin/login", entity);
    };

    // 退出登录
    this.logout = function () {
      return $http.get("../admin/logout");
    };

    // 获取登录账号的信息
    this.getLoginInfo = function () {
        return $http.get("../admin/getLoginInfo");
    }

});