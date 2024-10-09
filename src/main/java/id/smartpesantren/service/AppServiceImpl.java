/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.smartpesantren.service;

import id.smartpesantren.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author asus
 */
@Service
public class AppServiceImpl implements AppService {

    @Autowired
    UserService userService;

    @Override
    public User getCurrentUser() {
        return userService.getUserWithAuthorities().get();
    }

    @Override
    public String getSessionId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
