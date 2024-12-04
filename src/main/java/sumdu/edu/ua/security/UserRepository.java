/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sumdu.edu.ua.security;

import org.springframework.data.jpa.repository.JpaRepository;
import sumdu.edu.ua.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);

}