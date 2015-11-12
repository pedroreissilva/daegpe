/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ipleiria.dae.gpe.web.managers;

import pt.ipleiria.dae.gpe.web.app.AbstractManager;
import pt.ipleiria.dae.gpe.lib.beans.UserBean;
import pt.ipleiria.dae.gpe.lib.core.EntityValidationError;
import pt.ipleiria.dae.gpe.lib.dtos.UserDTO;
import java.util.EnumMap;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import pt.ipleiria.dae.gpe.lib.exceptions.EntityNotFoundException;
import pt.ipleiria.dae.gpe.lib.exceptions.EntityValidationException;
import pt.ipleiria.dae.gpe.web.models.student.UserDetailModel;

/**
 *
 * @author joeld
 */
@ManagedBean
@SessionScoped
public class StudentManager extends AbstractManager {

    @EJB
    private UserBean userBean;

    private UserDetailModel userDetailModel;
    private final EnumMap<EntityValidationError, String> errorMessages;

    public StudentManager() {
        errorMessages = new EnumMap<>(EntityValidationError.class);
        errorMessages.put(EntityValidationError.USER_NAME_REQUIRED, "Nome é obrigatório.");
        errorMessages.put(EntityValidationError.USER_EMAIL_REQUIRED, "Email é obrigatório.");
        errorMessages.put(EntityValidationError.USER_EMAIL_PATTERN, "Email inválido.");
    }

    @PostConstruct
    public void constructModels() {
        userDetailModel = new UserDetailModel();
    }

    ////////////////////////////////////////////
    ///////////////// Users ///////////////////
    public void saveUser() {
        UserDTO user = userDetailModel.provideUserDTO();
        try {
            userBean.save(user);
            PresentSuccessMessage("userdetailform", "Guardado com sucesso");
        } catch (EntityValidationException eve) {
            PresentErrorMessages("userdetailform", eve.getEntityValidationErrors(), errorMessages);
        } catch (EntityNotFoundException enf) {
            PresentErrorMessage("userdetailform", "Utilizador a ser editado, não foi encontrado ou foi removido.");
        }
    }

    ////////////////////////////////////////////
    ///////////////// Models ///////////////////
    public UserDetailModel getUserDetailModel() {
        return userDetailModel;
    }

}
