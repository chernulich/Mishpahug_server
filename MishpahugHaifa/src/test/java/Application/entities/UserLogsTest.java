package Application.entities;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import Application.entities.values.LogsDataValue;
import Application.entities.values.LogsDataValue.UserActions;
import Application.repo.UserRepository;

/**
 * Testing creation and deletion of entities to ensure relations work as
 * intended;
 * 
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@Transactional
public class UserLogsTest {

private static final UserItem ALYSSA = new UserItem();
	
	@Autowired
	UserRepository userRepo;

	@Before
	public void clear() {
		userRepo.deleteAll();
	}
	
	@Before 
	public void buildUser() {
		ALYSSA.setFirstName("Alyssa");
	}

	

	/**
	 * Testing add, with explicit saving
	 */
	@Test
	public void addLogsUser() {
		
		UserItem createdUser = ALYSSA;
		createdUser.setLogs(new ArrayList<LogsDataValue>());
		createdUser.getLogs().add(new LogsDataValue(LocalDate.now(), LocalTime.now(), UserActions.REGISTERED, ""));
		
		userRepo.save(createdUser);
		
		UserItem persistedUser = userRepo.findById(createdUser.getId()).get();

		assertTrue(persistedUser.equals(createdUser));
		System.out.println(persistedUser);
		System.out.println(persistedUser.getLogs());
	}
}
