package com.mayan.ecommerce.repositories;

import com.mayan.ecommerce.entities.User;
import com.mayan.ecommerce.projections.UserDetailsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(nativeQuery = true, value = """
    SELECT tb_user.email AS username, tb_user.password, tb_role.id as roleId, tb_role.authority
    FROM tb_user
    INNER JOIN tb_user_role ON tb_user.id = tb_user_role.user_id
    INNER JOIN tb_role ON tb_user_role.role_id = tb_role.id
    WHERE tb_user.email = :email
    """)
    List<UserDetailsProjection> searchUserAndRolesByEmail(String email);

    Optional<User> findByEmail(String email);

}
