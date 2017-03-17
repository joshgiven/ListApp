var module = angular.module('ngTrailApp');

var reportFormController = function(reportService, $location, $window) {
  var vm = this;

  vm.createReport = function(report) {
    report.trail = vm.trail;
    reportService.createReport(report)
      .then(function(resp){
        console.log(report.trail.id);
        // $location.path('/trail/'+report.trail.id);
        $window.location.reload();
      })
      .catch(function(err) {
        console.log('create report failed!');
      });
  };

  vm.updateReport = function(report) {
    reportService.updateReport(report)
      .then(function(resp){
        $location.path('/trail/'+report.trail.id);
        $location.reload();
      })
      .catch(function(err) {
        console.log('login failed!');
      });
  };

};

module.component('reportFormComponent', {
  templateUrl : 'app_client/templates/report-form-component.view.html',
  controller : reportFormController,
  bindings :{
    trail : '='
  }
});
