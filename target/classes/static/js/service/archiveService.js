app.service("archiveService", function ($http) {

    this.getYears = function () {
        return $http.get("../archive/years");
    };

    this.findArchiveBlog = function (years) {
        return $http.post("../archive/blogs", years)
    }

});