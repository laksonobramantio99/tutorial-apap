package apap.tutorial.gopud.repository;

import apap.tutorial.gopud.model.RoleModel;
import apap.tutorial.gopud.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDb extends JpaRepository<RoleModel, Long> {

}
