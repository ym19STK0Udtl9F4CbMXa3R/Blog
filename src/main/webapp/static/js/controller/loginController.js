app.controller("loginController", function ($scope, loginService) {

    // 账号登录
    $scope.login = function () {
        // 判断用户名或密码是否为空
        if ($scope.entity.username === '' || $scope.entity.password === ''){
            return;
        }
        loginService.login($scope.entity).success(
            function (response) {
                if (response.code === 200){
                    location.href = "/admin/index.html";
                } else {
                    $scope.errorMessage = response.message;
                }
            }
        )
    };

    // 退出
    $scope.logout = function(){
        loginService.logout().success(
            function (response) {
                alert(response.message);
                location.href = "admin/login.html";
            }
        )
    };

    $scope.chatHead = '';
    // 获取登录账号的信息
    $scope.getLoginInfo = function () {
        loginService.getLoginInfo().success(
            function (response) {
                $scope.nickName = response.name;
                $scope.email = response.email;
                $scope.chatHead = response.chatHead;
            }
        )
    };

});