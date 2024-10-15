package id.smartpesantren.repository;

import id.smartpesantren.dto.RegionDTO;
import id.smartpesantren.entity.SubDistrict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SubDistrictRepository extends JpaRepository<SubDistrict, Integer> {
    @Query("from SubDistrict c " +
            "where upper(c.code) like :q or upper(c.name) like :q ")
    Page<SubDistrict> filterSubDistrict(@Param("q") String q, Pageable p);

    @Query("from SubDistrict c " +
            "where (coalesce(:did,0)=0 OR c.district.id=:did) \n" +
            "and upper(c.code) like :q or upper(c.name) like :q ")
    List<SubDistrict> findAllSubDistrict(@Param("did") Integer did, @Param("q") String q, Pageable p);

    Optional<SubDistrict> findByCode(String code);

    @Query(value = "-- name: ResolveAllLocations :many\n" +
            "select c.province_id \"provinceId\", p.name \"provinceName\", d.city_id \"cityId\" , c.name \"cityName\", sd.district_id \"districtId\", d.name \"districtName\", sd.id \"subDistrictId\", sd.name \"subDistrictName\", \n" +
            "sd.name||', '||d.name||', '||c.name||', '||p.name \"locationName\",\n" +
            "SIMILARITY(sd.name||d.name, lower(replace(:q, ' ', ''))) similarity\n" +
            "from m_sub_district sd \n" +
            "join m_district d on d.id=sd.district_id \n" +
            "join m_city c on c.id=d.city_id \n" +
            "join m_province p on p.id = c.province_id  \n" +
            "WHERE \n" +
            "SIMILARITY(sd.name||d.name, lower(replace(:q, ' ', ''))) > 0.7 OR\n" +
            "SIMILARITY(sd.name||d.name, lower(replace(:q, ' ', ''))) > 0.3 OR\n" +
            "SIMILARITY(sd.name||d.name, lower(replace(:q, ' ', ''))) > 0.2\n" +
            "ORDER BY\n" +
            "similarity\n" +
            "DESC\n" +
            "limit :limit", nativeQuery = true)
    List<RegionDTO> findAllLocation(@Param("q") String q, @Param("limit") Integer limit);
}
