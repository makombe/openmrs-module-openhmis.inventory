(function() {
	'use strict';

	angular.module('app.pagination').service('PaginationService', PaginationService);

	PaginationService.$inject = [];

	function PaginationService() {
		var service;

		service = {
			pagingTo : pagingTo,
			pagingFrom : pagingFrom,
			paginateParams: paginateParams
		};

		return service;

		function pagingTo(currentPage, limit, totalNumOfResults) {
	    	if(currentPage <= 0){
	    		return limit;
	    	}
	    	else{
	    		var num = currentPage * limit;
	    		if(num > totalNumOfResults){
	    			return totalNumOfResults;
	    		}
	    		return num;
	    	}
		}
		
		function pagingFrom(currentPage, limit){
	    	return currentPage <= 1 ? 1 : (currentPage - 1) * limit;
		}
		
		function paginateParams(start, limit, includeRetired, q){
			var startIndex = ((start - 1) * limit) + 1;
			var params;
			
			if(includeRetired){
				params = {
					limit : limit,
					includeAll : true,
					startIndex : startIndex
				};
			}
			else{
				params = {
					limit : limit,
					startIndex : startIndex
				};
			}
			
			if(!angular.isUndefined(q) && q !== ''){
				params['q'] = q;
			}
			
			return params;
		}
	}
})();
