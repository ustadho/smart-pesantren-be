package id.smartpesantren.repository;

import id.smartpesantren.entity.PersistentLogins;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersistentLoginsRepository extends JpaRepository<PersistentLogins, String> {
    PersistentLogins findBySeries(String series);
    void deleteByUsername(String username);
}