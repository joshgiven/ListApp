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
    	  $window.location.reload();
      })
      .catch(function(err) {
        console.log('Edit Report Failed');
      });
  };
  

  
  vm.getInitialReport = function(){
	  console.log("In initial report")
	  if(vm.reportInfo){
		  vm.report = Object.assign({}, vm.reportInfo);
		  vm.report.timestamp = new Date(vm.report.timestamp);
	  }else{
		  vm.report={};
		  vm.report.tstatuses=[{},{},{}]
	  }
	  return vm.report;
  }
  

};

module.component('reportFormComponent', {
  templateUrl : 'app_client/templates/report-form-component.view.html',
  controller : reportFormController,
  bindings :{
    trail : '=',
    reportInfo : '=',
    edit : '<'
    	
  }
});
