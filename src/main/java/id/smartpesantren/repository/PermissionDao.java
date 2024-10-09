/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.smartpesantren.repository;

import id.smartpesantren.entity.Permission;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author cak-ust
 */
public interface PermissionDao extends PagingAndSortingRepository<Permission, String>{
    public List<Permission> findByIdNotIn(List<String> ids);
    
}
