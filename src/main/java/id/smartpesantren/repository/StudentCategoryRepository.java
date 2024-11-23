package id.smartpesantren.repository;

import id.smartpesantren.entity.Foundation;
import id.smartpesantren.entity.StudentCategory;
import id.smartpesantren.web.rest.vm.StudentCategoryVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface StudentCategoryRepository extends JpaRepository<StudentCategory, String> {
    @Query("select new id.smartpesantren.web.rest.vm.StudentCategoryVM(s) \n" +
            "from StudentCategory s \n" +
            "where s.foundation.id=?#{principal.foundationId} \n" +
            "and upper(s.name) like :q ")
    Page<StudentCategoryVM> filter(@Param("q") String q, Pageable p);
    @Query("select new id.smartpesantren.web.rest.vm.StudentCategoryVM(s) \n" +
            "from StudentCategory s \n" +
            "where s.foundation.id=?#{principal.foundationId} \n" +
            "order by s.name")
    List<StudentCategoryVM> findAllTypes();

    public Optional<StudentCategory> findByFoundationAndName(Foundation foundation, String name);

    @Modifying
    @Query(value = "update ac_student_category set is_default = (id=:id)", nativeQuery = true)
    public void updateDefault(@Param("id") String id);
}
