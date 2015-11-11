/*
 *
 * This file contains the code for the global validation rules.
 *
 * @project   FourT Portal
 * @date      1-Nov-2015
 * @author    Abubacker Siddik A, Chennai, India <abuabdul86@hotmail.com>
 * @licensor  Apache License 2.0
 * @site      
 *
 */

/*global validation rules and messages */

var FOURT = window.FOURT || {};

(function(window, document, $, FOURT) {
    "use strict";
    FOURT.validationUtil = (function() {

        var validationRules = function() {

        	var taskDescValidator = {
            		selector: '.taskDetailDesc',
                    validators: {
                        notEmpty: {
                            message: 'The task detail is required and cannot be empty'
                        }
                    }
                },
                taskDurationValidator = {
               		selector: '.taskDetailDuration',
                    validators: {
                         notEmpty: {
                            message: 'The task duration is required and cannot be empty'
                         },
                         numeric: {
                        	 message: 'Please enter valid duration in hours'
                         }
                     }
        		},
        		taskStatusValidator = {
                   		selector: '.taskDetailStatus',
                        validators: {
                             notEmpty: {
                                message: 'The task status is required and cannot be empty'
                             }
                         }
            	},
        		taskIndex=0;
        	
            $('#resourceTaskTrackerForm').bootstrapValidator({
                live: 'enabled',
                message: 'This value is not valid',
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    /* for task form */
                    resourceName: {
                        validators: {
                            notEmpty: {
                                message: 'Please select a resource name'
                            }
                        }
                    },
                    taskDate: {
                        validators: {
                            notEmpty: {
                                message: 'The task date is required and cannot be empty'
                            }, 
                            date: {
                                format: 'DD/MM/YYYY',
                                message: 'The date is not a valid format'
                            }
                        }
                    },
                    taskDetailDesc: taskDescValidator,
                    taskDetailDuration: taskDurationValidator,
                    taskDetailStatus: taskStatusValidator
                }
            }).on('click','.add-task',function(){
            	  taskIndex++;
                  var $template = $('.task-details-block'),
                      $clone    = $template.clone().removeClass('hide').removeAttr('class').attr('data-task-index',taskIndex).insertBefore($template);

                  // Update the name attributes
                  $clone.find('[name="taskDetailList.taskDesc"]').removeAttr('id').attr('name', 'taskDetailList[' + taskIndex + '].taskDesc').end()
                      	.find('[name="taskDetailList.duration"]').removeAttr('id').attr('name', 'taskDetailList[' + taskIndex + '].duration').end()
                      	.find('[name="taskDetailList.status"]').removeAttr('id').attr('name', 'taskDetailList['+ taskIndex +'].status').end();

                  bootstrapValidatorObj('#resourceTaskTrackerForm').addField('taskDetailDesc',taskDescValidator)
                  												   .addField('taskDetailDuration',taskDurationValidator)
                  												   .addField('taskDetailStatus',taskStatusValidator);
            }).on('click','.remove-task',function(){
            	var $row  = $(this).parent().parent().parent(),
            		index = $row.attr('data-task-index');

	             // Remove fields
            	 bootstrapValidatorObj('#resourceTaskTrackerForm').removeField($row.find('[name="taskDetailList[' + index + '].taskDesc"]'))
            	 												 .removeField($row.find('[name="taskDetailList[' + index + '].duration"]'))
            	 												 .removeField($row.find('[name="taskDetailList[' + index + '].status"]'));
	             $row.remove();
            });

            $('#resetButton').on('click touchstart', function() {
            	bootstrapValidatorObj('#resourceTaskTrackerForm').resetForm(true);
            });
            
	       	 var bootstrapValidatorObj = function(formName){
	     	   	return $(formName).data('bootstrapValidator');
	     	 }
            
        };
        
        /* Initiate Function */
        var initFunction = function() {
            validationRules(); 
        };

        /* Return public properties/methods */
        return {
            initFunction: initFunction
        };

    }());

}(window, document, jQuery, FOURT));

/* Bind the validation utilities function to document load */
jQuery(FOURT.validationUtil.initFunction);
