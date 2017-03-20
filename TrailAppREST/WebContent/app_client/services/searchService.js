var app = angular.module('ngTrailApp');


app.factory('searchService', function($http, $location, gLocatorService, errorXferService){
  var service = {};
  var searchAPI  = 'api/search/trails';
  var keywordAPI = 'api/search/keyword';

  service.reqParams = function(searchObj) {
    //var obj = {name:'Boulder', state:'Colorado', radius:25};
    //var s = '?city=Boulder&state=Colorado&radius=25';

    var s = "?" + Object.keys(searchObj)
                        .filter((key) => searchObj[key])
                        .map((key) => key + "=" + searchObj[key] )
                        .join("&");
    return s;
  };

  service.findTrailsByKeyword = function(keywords){
    if(typeof keywords === 'string')
    keywords = [keywords];

    var paramString = function(words) {
      return '?k=' + words.map(x => x.trim().replace(/\s+/, '+'))
                        .join('+');
    };

    var url = keywordAPI + paramString(keywords);

    return $http.get(url)
      .then(function(resp) {
        return Object.assign([], resp.data);
      })
      .catch(function(resp) {
        errorXferService.putError(
          ['trails search by keyword error: ', resp.status, resp.statusText].join(' '));
        $location.path('/error');
      });

  };

  service.findTrails = function(searchObj) {
    // chaz: If city is not null logic here
    // josh: I changed the search form to require city, state & radius

    // I heard you like Promises, so I put Promises in your Promises...
    return gLocatorService.findLocation(searchObj.city, searchObj.state)
      .then(function(loc) {
        searchObj.lat = loc.lat;
        searchObj.lng = loc.lng;

        var url = searchAPI + service.reqParams(searchObj);
        //console.log(url);

        return $http({
          method : 'GET',
          url : url,
          headers : {
            //'x-access-token' : authService.getToken()
          }
        })
        .then(function(resp) {
          //console.log('trails found',resp.data);
          return Object.assign([], resp.data);
        })
        .catch(function(resp) {
          errorXferService.putError(
            ['trails search error: ', resp.status, resp.statusText].join(' '));
          $location.path('/error');
        });
      });
  };

  return service;
});

app.factory('gLocatorService', function($http, $q){
  var service = {};
  var googleSearchAPI = "https://maps.googleapis.com/maps/api/geocode/json?address=";

  // let's cache our lookups
  service.lookups = {
    // 'Denver+Colorado' : {
    //   city : 'Denver', state : 'Colorado',
    //   lat : '39.7392358', lng : '-104.990251'
    // }
  };

  service.googleParams = function(city, state) {
    return [city, state].join('+');
  };

  service.findLocation = function(city, state) {
    var key = service.googleParams(city, state);

    if(service.lookups[key]) {
      return $q(function(resolve, reject){
        setTimeout(function(){
          //console.log('my promise');
          resolve(service.lookups[key]);
        }, 100);
      });
    }

    var gUrl = googleSearchAPI + key;
    //console.log(gUrl);

    return $http.get(gUrl)
      .then(function(gresp){
        var loc = {
          city : city,
          state : state,
          lat : gresp.data.results[0].geometry.location.lat,
          lng : gresp.data.results[0].geometry.location.lng
        };

        service.lookups[key] = loc;

        return loc;
      });
  };

  return service;
});
