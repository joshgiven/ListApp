var app = angular.module('ngTrailApp');

app.factory('searchService', function($http){
  var service = {};

  var searchAPI = 'api/search/trails';

  var googleSearchAPI = "https://maps.googleapis.com/maps/api/geocode/json?address=";

  service.reqParams = function(searchObj) {
    //var obj = {name:'Boulder', state:'Colorado', radius:25};
    //var s = '?city=Boulder&state=Colorado&radius=25';

    var s = "?" + Object.keys(searchObj)
                        .filter((key) => searchObj[key])
                        .map((key) => key + "=" + searchObj[key] )
                        .join("&");

    return s;
  };

  service.reqParamsAlt = function(lat, lng, rad) {
    //var obj = {name:'Boulder', state:'Colorado', radius:25};
    //var s = '?city=Boulder&state=Colorado&radius=25';

    var sA = "?" + Object.keys(searchObj)
                        .filter((key) => searchObj[key])
                        .map((key) => key + "=" + searchObj[key] )
                        .join("&");

    return sA;
  };

  service.googleParams = function(searchObj) {
    //var obj = {name:'Boulder', state:'Colorado', radius:25};
    //var s = '?city=Boulder&state=Colorado&radius=25';


    var sG = Object.keys(searchObj)
              .filter((key) => searchObj[key])
              .filter((key) => searchObj[key] != searchObj.radius)
              .map((key) => searchObj[key] )
              .join("+");
    return sG;
  };

  service.execute = function(searchObj) {
    var url = searchAPI + service.reqParams(searchObj);
    //console.log(url);
    //If city is not null logic here
    service.googleSearch(searchObj).
      then(function (gresp){
        console.log(gresp);
        searchObj.lat = gresp.data.results[0].geometry.location.lat;
        searchObj.lng = gresp.data.results[0].geometry.location.lng;
        var url = searchAPI + service.reqParams(searchObj);
        console.log(url);
      });


    return $http({
      method : 'GET',
      url : url,
      headers : {
        //'x-access-token' : authService.getToken()
      }
    });
  };

  service.googleSearch = function(searchObj) {
    var gUrl = googleSearchAPI + service.googleParams(searchObj);
    //console.log(url);

    return $http({
      method : 'GET',
      url : gUrl,
      headers : {
        //'x-access-token' : authService.getToken()
      }
    });
  };

  return service;
});
