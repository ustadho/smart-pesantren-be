package id.smartpesantren.repository;

import id.smartpesantren.dto.TahfidzTargetDTO;
import id.smartpesantren.entity.TahfidzTargetSantri;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TahfidzTargetRepository extends JpaRepository<TahfidzTargetSantri, String> {

    @Query(value = "select cs.student_id \"studentId\", s.name \"studentName\", s.nis \"studentNis\", coalesce(s.nisn,'') \"studentNisn\", s.dob \"studentDob\" , coalesce(ay.\"name\",'') \"joinAcademicYear\", \n" +
            "tt.id \"id\", \n" +
            "coalesce('Hal: '||tk.id ||' / Target: ' || tk.jml_halaman || ' Hal. ('|| tk.konv_juz ||' Juz '||tk.Konv_halaman ||' Hal.)', '') \"targetInfo\"\n" +
            "from ac_class_room_student cs\n" +
            "join ac_class_room acr on acr.id=cs.class_room_id \n" +
            "join ac_student s on s.id=cs.student_id \n" +
            "join academic_year ay on ay.id=s.join_year_id \n" +
            "left join tahfidz_target_santri tt on tt.student_id = cs.student_id and acr.academic_year_id = ay.id\n" +
            "left join tahfidz_konversi tk on tk.id = tt.target\n" +
            "where cs.class_room_id = :classRoomId \n" +
            "order by s.name, s.dob  ", nativeQuery = true)
    public List<TahfidzTargetDTO> findByClassRoomId(@Param("classRoomId") String classRoomId);
}
