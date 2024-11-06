package com.yohans.userbankinfo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class UserbankinfoApplicationTests {

//	@Test
//	void contextLoads() {
//		// Test that the context loads without issues
//	}

	@BeforeAll
	public static void init() {
		try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:26257/your_database?sslmode=disable", "root", "")) {
			// Check if the connection is valid
			assertTrue(connection.isValid(2));
		} catch (SQLException e) {
			fail("Database connection failed: " + e.getMessage());
		}
	}
}