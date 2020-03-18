package com.controller.PropertyEditor;

import com.model.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.beans.PropertyEditorSupport;

public class UserProcedurePropertyEditor extends PropertyEditorSupport {


   @Autowired
   UserService userService;

    @Override
    public void setAsText(String text) throws IllegalArgumentException {

        User user = userService.getUserById(text);
        setValue(user);

        System.out.println("PropertyEdition");
    }

}
