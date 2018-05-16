//package daoimpltest;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
//import org.apache.log4j.Logger;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import daoimpl.EmployeeDaoImpl;
//import daoimpl.InformationDaoImpl;
//import model.Information;
//
//public class InformationDaoImplTest {
//	static Logger logger;
//	static EmployeeDaoImpl employeeDao;
//	static InformationDaoImpl informationDao;
//
//	@BeforeClass
//	public static void setUpBeforeClass() throws Exception {
//		logger = Logger.getLogger(InformationDaoImpl.class);
//		employeeDao = EmployeeDaoImpl.getInstance(logger);
//		informationDao = InformationDaoImpl.getInstance(logger);
//	}
//
//	@Test
//	public void testCreateInformation() {
//		employeeDao.createEmployee("william", "password");
//		boolean result = informationDao.createInformation("william", 
//				"firstname", "middlename", "lastname");
//		informationDao.deleteInformation("william");
//		employeeDao.deleteEmployee("william");
//		assertTrue(result);
//	}
//
//	@Test
//	public void testReadInformation() {
//		employeeDao.deleteEmployee("william");
//		employeeDao.createEmployee("william", "password");
//		informationDao.createInformation("william", "firstname", 
//				"middlename", "lastname");
//		Information information = informationDao.readInformation("william");
//		informationDao.deleteInformation("william");
//		employeeDao.deleteEmployee("william");
//		assertEquals(information.getFirstname(), "firstname");
//		assertEquals(information.getMiddlename(), "middlename");
//		assertEquals(information.getLastname(), "lastname");
//
//	}
//
//	@Test
//	public void testUpdateInformation() {
//		employeeDao.createEmployee("william", "password");
//		informationDao.createInformation("william", "firstname",
//				"middlename", "lastname");
//		boolean result = informationDao.updateInformation("william", 
//				"first", "middle", "last");
//		Information information = informationDao.readInformation("william");
//		informationDao.deleteInformation("william");
//		employeeDao.deleteEmployee("william");
//		assertTrue(result);
//		assertEquals(information.getFirstname(), "first");
//		assertEquals(information.getMiddlename(), "middle");
//		assertEquals(information.getLastname(), "last");
//
//	}
//
//	@Test
//	public void testDeleteInformation() {
//		employeeDao.createEmployee("william", "password");
//		informationDao.createInformation("william", "firstname",
//				"middlename", "lastname");
//		boolean result = informationDao.deleteInformation("william");
//		employeeDao.deleteEmployee("william");
//		assertTrue(result);
//		
//	}
//
//}
