package es.uma.sportjump.sjs.dao.test.impl.jpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.uma.sportjump.sjs.dao.test.CalendarEventDaoTest;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-jpa-application-dao.xml")
public class CalendarEventDaoJpaTest  extends CalendarEventDaoTest{
	
	
	@Test
	public void testCalendarEventCRUD(){
		super.testCalendarEventCRUD();
	}

	
	@Test
	public void testGetAllEventByTeam(){
		super.testGetAllEventByTeam();
	}

}
