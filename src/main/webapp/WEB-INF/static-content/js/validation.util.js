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

            $('#resourceTaskTrackerForm, #resetPwdForm').bootstrapValidator({
                live: 'enabled',
                message: 'This value is not valid',
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    /* for registration form */
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
                    taskDetailDesc: {
                		selector: '.taskDetailDesc',
                        validators: {
                            notEmpty: {
                                message: 'The task detail is required and cannot be empty'
                            }
                        }
                    },
                    taskDetailDuration: {
                		selector: '.taskDetailDuration',
                        validators: {
                            notEmpty: {
                                message: 'The task duration is required and cannot be empty'
                            }
                        }
                    }
                }
            }); 

            $('#resetButton').on('click touchstart', function() {
                $('#resourceTaskTrackerForm, #userReigstrationForm').data('bootstrapValidator').resetForm(true);
            });
            
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
