app.service("commentService", function ($http) {

    this.add = function (comment) {
        return $http.post("../comment/save", comment);
    };

    this.find = function (id) {
        return $http.get("../comment/" + id);
    }

});