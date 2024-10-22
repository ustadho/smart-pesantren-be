package id.smartpesantren.repository;

import id.smartpesantren.dto.OrganizationDTO;
import id.smartpesantren.dto.OrganizationTreeDTO;
import id.smartpesantren.entity.Organization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrganizationRepository extends PagingAndSortingRepository<Organization, String> {
    @Query("select new id.smartpesantren.dto.OrganizationDTO(c.id, p.id, c.code, c.name, c.description, c.active, c.jobLevel.id) " +
            "from Organization c \n" +
            "left join c.parent p \n" +
            "where c.foundation.id=?#{principal.foundationId} \n" +
            "and upper(c.name) like :q")
    public Page<OrganizationDTO> findAllOrganization(@Param("q") String q, Pageable p);

    @Query("select new id.smartpesantren.dto.OrganizationDTO(c.id, p.id, c.code, c.name, c.description, c.active, c.jobLevel.id) " +
            "from Organization c \n" +
            "left join c.parent p \n" +
            "where c.foundation.id=?#{principal.foundationId} \n" +
            "and upper(c.name) like :q")
    Iterable<OrganizationDTO> findAllOrganization(@Param("q") String q);

    @Query(value = "WITH RECURSIVE cte AS (\n" +
            "    SELECT a.id, a.code, a.name, a.parent_id, 0 AS level, a.active, cast(a.code as text) AS path\n" +
            "    FROM m_organization a\n" +
            "    WHERE a.parent_id IS null\n" +
            "    and a.foundation_id=?#{principal.foundationId}\n" +
            "    \n" +
            "    \n" +
            "    UNION ALL\n" +
            "    \n" +
            "    SELECT o.id, o.code, o.name, o.parent_id, p.level + 1 AS level, p.active,\n" +
            "    (cast(p.code as text) || cast('.000.' as text)) || cast(o.code as text) AS path\n" +
            "    FROM cte p,\n" +
            "    m_organization o\n" +
            "    WHERE o.parent_id = p.id\n" +
            ")\n" +
            "SELECT c.id, c.code, c.name, c.parent_id \"parentId\", l.level, c.active, l.color, ( SELECT count(1) AS count\n" +
            "FROM cte\n" +
            "WHERE cte.parent_id = c.id) AS \"childCount\", c.path\n" +
            "FROM cte c\n" +
            "join m_organization o on o.id=c.id\n" +
            "join m_job_level l on l.id=o.level_id\n" +
            "ORDER BY c.path;", nativeQuery = true)
    List<OrganizationTreeDTO> findAllOrganizationTree();
}
