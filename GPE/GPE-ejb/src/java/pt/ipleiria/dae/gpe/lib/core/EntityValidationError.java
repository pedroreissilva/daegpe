/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ipleiria.dae.gpe.lib.core;

/**
 *
 * @author joeld
 */
public enum EntityValidationError {

    // USER
    USER_INTERNALID_REQUIRED, USER_INTERNALID_NOT_UNIQUE, USER_USERTYPE_INVALID, USER_NAME_REQUIRED, USER_EMAIL_REQUIRED, USER_EMAIL_PATTERN,
    // UC
    UC_INTERNALID_REQUIRED, UC_INTERNALID_NOT_UNIQUE, UC_NAME_REQUIRED,
    // ATTENDANCE
    ATTENDANCE_INVALID_STUDENT, ATTENDANCE_INVALID_EVENT, ATTENDANCE_IS_NEW
    

}
