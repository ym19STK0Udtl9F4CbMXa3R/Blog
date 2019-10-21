app.controller("archiveController", function ($scope, archiveService) {

    $scope.getYears = function () {
        archiveService.getYears().success(
            function (response) {
                $scope.years = response;
                if ($scope.years != null){
                    $scope.findArchiveBlog();
                }
            }
        );
    };

    $scope.findArchiveBlog = function () {
        archiveService.findArchiveBlog($scope.years).success(
            function (response) {
                $scope.archives = response;
                $scope.total = 0;
                for (var i = 0; i < $scope.archives.length; i++){
                    $scope.total += $scope.archives[i].data.length;
                }
            }
        );
    }

});