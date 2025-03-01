package id.smartpesantren.repository;

import id.smartpesantren.dto.EmployeeDayOffDTO;
import id.smartpesantren.entity.Day;
import id.smartpesantren.entity.PersonData;
import id.smartpesantren.entity.WorkingDayOff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WorkingDayOffRepository extends JpaRepository<WorkingDayOff, String> {

    public WorkingDayOff findByEmployeeAndDay(PersonData p, Day d);

    @Query(value = "with pd as (\n" +
            "\tselect pd.id, pd.employee_no \"employeeNo\", pd.name, coalesce(jp.name,'') \"jobPosistion\", coalesce(wh.name, '') \"workingHour\",\n" +
            "\tcount(hwd.employee_id) dayOff\n" +
            "\tfrom person_data pd \n" +
            "\tleft join hr_job_position jp on jp.id=pd.job_position_id \n" +
            "\tleft join hr_working_hour wh on wh.id=pd.working_hour_id \n" +
            "\tleft join hr_working_dayoff hwd on hwd.employee_id = pd.id \n" +
            "\twhere pd.person_type ='EMPLOYEE'\n" +
            "\tand coalesce(pd.active, false) = true\n" +
            "\tand pd.foundation_id = ?#{principal.foundationId}\n" +
            "\tand (coalesce(:category,'')='' OR pd.employee_category_id = :category)\n" +
            "\tand (coalesce(:organization,'')='' OR pd.organization_id = :organization)\n" +
            "\tand (coalesce(:jobPosition,'')='' OR pd.job_position_id = :jobPosition)\n" +
            "\tand pd.name ilike :q \n" +
            "\tgroup by pd.id, pd.employee_no, pd.name, jp.name, coalesce(wh.name, '')\n" +
            ")\t\n" +
            "select pd.*,\n" +
            "CASE WHEN wd_senin.day_id IS NOT NULL THEN 'Libur' ELSE 'Masuk' END AS senin,\n" +
            "CASE WHEN wd_selasa.day_id IS NOT NULL THEN 'Libur' ELSE 'Masuk' END AS selasa,\n" +
            "CASE WHEN wd_rabu.day_id IS NOT NULL THEN 'Libur' ELSE 'Masuk' END AS rabu,\n" +
            "CASE WHEN wd_kamis.day_id IS NOT NULL THEN 'Libur' ELSE 'Masuk' END AS kamis,\n" +
            "CASE WHEN wd_jumat.day_id IS NOT NULL THEN 'Libur' ELSE 'Masuk' END AS jumat,\n" +
            "CASE WHEN wd_sabtu.day_id IS NOT NULL THEN 'Libur' ELSE 'Masuk' END AS sabtu,\n" +
            "CASE WHEN wd_ahad.day_id IS NOT NULL THEN 'Libur' ELSE 'Masuk' END AS ahad\n" +
            "from pd \n" +
            "LEFT JOIN hr_working_dayoff wd_senin ON wd_senin.employee_id = pd.id AND wd_senin.day_id = 1\n" +
            "LEFT JOIN hr_working_dayoff wd_selasa ON wd_selasa.employee_id = pd.id AND wd_selasa.day_id = 2\n" +
            "LEFT JOIN hr_working_dayoff wd_rabu ON wd_rabu.employee_id = pd.id AND wd_rabu.day_id = 3\n" +
            "LEFT JOIN hr_working_dayoff wd_kamis ON wd_kamis.employee_id = pd.id AND wd_kamis.day_id = 4\n" +
            "LEFT JOIN hr_working_dayoff wd_jumat ON wd_jumat.employee_id = pd.id AND wd_jumat.day_id = 5\n" +
            "LEFT JOIN hr_working_dayoff wd_sabtu ON wd_sabtu.employee_id = pd.id AND wd_sabtu.day_id = 6\n" +
            "LEFT JOIN hr_working_dayoff wd_ahad ON wd_ahad.employee_id = pd.id AND wd_ahad.day_id = 0\n" +
            "order by pd.\"employeeNo\""
            ,nativeQuery = true)
    public Iterable<EmployeeDayOffDTO> findAllDayOff(
            @Param("category") String category,
            @Param("organization") String organization,
            @Param("jobPosition") String jobPosition,
            @Param("q") String q
            );

    @Query(value = "with pd as (\n" +
            "\tselect pd.id, pd.employee_no \"employeeNo\", pd.name, coalesce(jp.name,'') \"jobPosistion\", coalesce(wh.name, '') \"workingHour\",\n" +
            "\tcount(hwd.employee_id) dayOff\n" +
            "\tfrom person_data pd \n" +
            "\tleft join hr_job_position jp on jp.id=pd.job_position_id \n" +
            "\tleft join hr_working_hour wh on wh.id=pd.working_hour_id \n" +
            "\tleft join hr_working_dayoff hwd on hwd.employee_id = pd.id \n" +
            "\twhere pd.id =:employeeId\n" +
            "\tgroup by pd.id, pd.employee_no, pd.name, jp.name, coalesce(wh.name, '')\n" +
            ")\t\n" +
            "select pd.*,\n" +
            "CASE WHEN wd_senin.day_id IS NOT NULL THEN 'Libur' ELSE 'Masuk' END AS senin,\n" +
            "CASE WHEN wd_selasa.day_id IS NOT NULL THEN 'Libur' ELSE 'Masuk' END AS selasa,\n" +
            "CASE WHEN wd_rabu.day_id IS NOT NULL THEN 'Libur' ELSE 'Masuk' END AS rabu,\n" +
            "CASE WHEN wd_kamis.day_id IS NOT NULL THEN 'Libur' ELSE 'Masuk' END AS kamis,\n" +
            "CASE WHEN wd_jumat.day_id IS NOT NULL THEN 'Libur' ELSE 'Masuk' END AS jumat,\n" +
            "CASE WHEN wd_sabtu.day_id IS NOT NULL THEN 'Libur' ELSE 'Masuk' END AS sabtu,\n" +
            "CASE WHEN wd_ahad.day_id IS NOT NULL THEN 'Libur' ELSE 'Masuk' END AS ahad\n" +
            "from pd \n" +
            "LEFT JOIN hr_working_dayoff wd_senin ON wd_senin.employee_id = pd.id AND wd_senin.day_id = 1\n" +
            "LEFT JOIN hr_working_dayoff wd_selasa ON wd_selasa.employee_id = pd.id AND wd_selasa.day_id = 2\n" +
            "LEFT JOIN hr_working_dayoff wd_rabu ON wd_rabu.employee_id = pd.id AND wd_rabu.day_id = 3\n" +
            "LEFT JOIN hr_working_dayoff wd_kamis ON wd_kamis.employee_id = pd.id AND wd_kamis.day_id = 4\n" +
            "LEFT JOIN hr_working_dayoff wd_jumat ON wd_jumat.employee_id = pd.id AND wd_jumat.day_id = 5\n" +
            "LEFT JOIN hr_working_dayoff wd_sabtu ON wd_sabtu.employee_id = pd.id AND wd_sabtu.day_id = 6\n" +
            "LEFT JOIN hr_working_dayoff wd_ahad ON wd_ahad.employee_id = pd.id AND wd_ahad.day_id = 0\n" +
            "order by pd.\"employeeNo\""
            ,nativeQuery = true)
    public EmployeeDayOffDTO findByEmployeeId(@Param("employeeId") String employeeId);
}
