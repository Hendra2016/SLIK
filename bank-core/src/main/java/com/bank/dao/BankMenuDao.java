package com.bank.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bank.model.BankMenu;

public interface BankMenuDao extends JpaRepository<BankMenu, Integer> {

	@Query(value = "WITH RECURSIVE Tree_CTE\n" +
			"(\n" +
			"    menu_id,\n" +
			"    menu_name,\n" +
			"    menu_url,\n" +
			"    menu_type,\n" +
			"    parent_id,\n" +
			"    tree_level\n" +
			")\n" +
			"AS\n" +
			"(\n" +
			"    SELECT\n" +
			"        m.menu_id,\n" +
			"        m.menu_name,\n" +
			"        m.menu_url,\n" +
			"        m.menu_type,\n" +
			"        m.parent_id,\n" +
			"        CAST(m.menu_id AS VARCHAR)\n" +
			"    FROM bank_menu m\n" +
			"    JOIN bank_role_menu r\n" +
			"        ON m.menu_id = r.menu_id\n" +
			"    WHERE r.role_id = :roleId\n" +
			"      AND m.parent_id =0\n" +
			"\n" +
			"    UNION ALL\n" +
			"\n" +
			"    SELECT\n" +
			"        c.menu_id,\n" +
			"        c.menu_name,\n" +
			"        c.menu_url,\n" +
			"        c.menu_type,\n" +
			"        c.parent_id,\n" +
			"        CONCAT(t.tree_level, '.', c.menu_id)\n" +
			"    FROM bank_menu c\n" +
			"    JOIN bank_role_menu r\n" +
			"        ON c.menu_id = r.menu_id\n" +
			"       AND r.role_id = :roleId\n" +
			"    JOIN Tree_CTE t\n" +
			"        ON c.parent_id = t.menu_id\n" +
			")\n" +
			"\n" +
			"SELECT *\n" +
			"FROM Tree_CTE\n" +
			"ORDER BY tree_level;", nativeQuery = true)
	public List<Object[]> getMenuParent(@Param("roleId") Integer roleId);

}
