app.service("uploadService", function ($http) {

    this.uploadFile = function () {
        var formData = new FormData();
        formData.append("file", file.files[0]);
        return $http({
            method: 'post',
            url: '/upload',
            data: formData,
            // 文件上传
            headers: {'Content-type': undefined},
            transformRequest: angular.identity  //字节化
        });
    }

});