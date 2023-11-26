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

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(value = false)
class DevApplicationTests {

	@Autowired
	EntityManager em;


	@Test
	void contextLoads() {
		Member member = new Member();
		em.persist(member);



	}

}
