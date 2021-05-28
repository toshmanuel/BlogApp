package com.blogapp.blogapp;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@Slf4j
@SpringBootTest
class BlogAppApplicationTests {
	@Autowired
	DataSource dataSource;

	@Test
	void applicationCanConnectToDatabaseTest(){
		assertThat(dataSource).isNotNull();
		log.info("Data object is created");
		try (Connection conn = dataSource.getConnection()){
			assertThat(conn).isNotNull();
			assertThat(conn.getCatalog()).isEqualTo("blogdb");
			log.info("Connection --> {}", conn.getCatalog());
		}catch(SQLException e){
			log.info("Exception occurred while " +
					"connecting to the " +
					"database --> {}", e.getMessage());
		}
	}

	@Test
	void contextLoads() {
	}

}
