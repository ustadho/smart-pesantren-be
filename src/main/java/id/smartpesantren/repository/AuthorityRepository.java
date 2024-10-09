/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.smartpesantren.repository;

import id.smartpesantren.entity.AbstractAuditingEntity;
import id.smartpesantren.entity.Authority;
import id.smartpesantren.dto.AcademicYearDTO;
import id.smartpesantren.dto.ClassLevelDTO;
import id.smartpesantren.dto.ClassRoomDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 *
 * @author ustadho
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {

}

