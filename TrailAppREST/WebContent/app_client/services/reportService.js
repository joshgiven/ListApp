var app = angular.module('ngTrailApp');

app.factory('reportService', function($http, authService){
  var service = {};

  service.createReport = function(report){
  return $http({
    method : 'POST',
    url : 'api/auth/trails/'+report.trail.id+'/reports' ,
    headers : {
      'Content-Type' : 'application/json',
      'x-access-token' : authService.getToken()
    },
    data : report
  });
};

service.updateReport = function(report){
	console.log(report)
  return $http({
    method : 'PUT',
    url : 'api/auth/trails/reports/'+ report.id,
    headers : {
      'Content-Type' : 'application/json',
      'x-access-token' : authService.getToken()
    },
    data : report
  });
};
return service;

});
