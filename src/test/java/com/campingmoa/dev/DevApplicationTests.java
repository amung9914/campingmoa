package com.campingmoa.dev;

import com.campingmoa.dev.domain.Member;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.DAYS;


class DevApplicationTests {



	@Test
	void contextLoads() {
		LocalDate startDate = LocalDate.of(2023,11,26);
		LocalDate endDate = LocalDate.of(2023,11,27);
		long days = DAYS.between(startDate,endDate);




	}

}
