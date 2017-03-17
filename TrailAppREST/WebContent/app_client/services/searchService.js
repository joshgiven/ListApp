var app = angular.module('ngTrailApp');

app.factory('searchService', function($http){
  var service = {};

  var searchAPI = 'api/search/trails';

  service.reqParams = function(searchObj) {
    //var obj = {name:'Boulder', state:'Colorado', radius:25};
    //var s = '?city=Boulder&state=Colorado&radius=25';

    var s = "?" + Object.keys(searchObj)
                        .filter((key) => searchObj[key])
                        .map((key) => key + "=" + searchObj[key] )
                        .join("&");

    return s;
  };

  service.execute = function(searchObj) {
    var url = searchAPI + service.reqParams(searchObj);
    //console.log(url);

    return $http({
      method : 'GET',
      url : url,
      headers : {
        //'x-access-token' : authService.getToken()
      }
    });
  };

  return service;
});
