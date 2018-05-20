package daoimpl;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import dao.ReimbursementDao;
import design.ConnectionUtil;
import model.Reimbursement;

public class ReimbursementDaoImpl implements ReimbursementDao {
	private Logger logger;
	private static ReimbursementDaoImpl dao;
	
	public static ReimbursementDaoImpl getInstance(Logger logger) {
		if(dao == null)
			dao = new ReimbursementDaoImpl(logger);
		return dao;
	}
	
	private ReimbursementDaoImpl(Logger logger) {
		super();
		this.logger = logger;
	}

	@Override
	public boolean createReimbursement(String inUsername, String inStatus, InputStream inImage, String inCategory) {
		String sql = "{call create_reimbursement(?,?,?,?)}";
		try(Connection c = ConnectionUtil.connect(this.logger);
				CallableStatement s = c.prepareCall(sql);){
			s.setString(1, inUsername);
			s.setString(2, inStatus);
			s.setBlob(3, inImage);
			s.setString(4, inCategory);
			return s.executeUpdate() > 0;
		} catch(SQLException e) {
			logger.error(e.getSQLState());
			logger.error(e.getErrorCode());
			logger.error(e.getMessage());
			return false;
		}
	}
	
	@Override
	public Reimbursement readReimbursement(int inReimbursementid) {
		String sql = "SELECT * FROM reimbursement WHERE reimbursementid=?";

		try(Connection c = ConnectionUtil.connect(this.logger);
				PreparedStatement s = c.prepareStatement(sql);){
			s.setInt(1, inReimbursementid);
			ResultSet r = s.executeQuery();
			
			while(r.next()) {
				int reimbursementid = r.getInt(1);
				String employee = r.getString(2);
				String manager = r.getString(3);
				String status = r.getString(4);
				Blob blob = r.getBlob(5);
				String category = r.getString(6);

				InputStream image = null;
				if(blob == null) {
					ClassLoader classloader = Thread.currentThread().getContextClassLoader();
					image = classloader.getResourceAsStream("noimage.png");
				} else
					image = blob.getBinaryStream();
				
				return new Reimbursement(reimbursementid, employee, manager,
						status, image, category);
			}
		} catch(SQLException e) {
			logger.error(e.getSQLState());
			logger.error(e.getErrorCode());
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@Override
	public List<Reimbursement> readReimbursements(){
		String sql = "SELECT * FROM reimbursement";
		try(Connection c = ConnectionUtil.connect(this.logger);
				PreparedStatement s = c.prepareStatement(sql);){
			ResultSet r = s.executeQuery();
			List<Reimbursement> reimbursements = new ArrayList<>();
			while(r.next()) {
				int reimbursementid = r.getInt(1);
				String employee = r.getString(2);
				String manager = r.getString(3);
				String status = r.getString(4);
				Blob blob = r.getBlob(5);
				String category = r.getString(6);
				
				InputStream image = null;
				if(blob == null) {
					ClassLoader classloader = Thread.currentThread().getContextClassLoader();
					image = classloader.getResourceAsStream("noimage.png");
				} else
					image = blob.getBinaryStream();
				
				Reimbursement reimbursement =  new Reimbursement(reimbursementid, employee, manager,
						status, image, category);
				reimbursements.add(reimbursement);
			}
			return reimbursements;
		} catch(SQLException e) {
			logger.error(e.getSQLState());
			logger.error(e.getErrorCode());
			logger.error(e.getMessage());
		}
		return null;
	}
	
	public List<Reimbursement> readReimbursements(String inUsername){
		List<Reimbursement> reimbursements = readReimbursements();
		
		List<Reimbursement> returnValue = reimbursements.stream()
			.filter(r -> r.getEmployee().equals(inUsername))
			.collect(Collectors.toList());
		
		return returnValue;
	}

	@Override
	public boolean updateReimbursement(int inReimbursementid, String inStatus, String inManager) {
		String sql = "{call update_reimbursement(?,?,?)}";
		try(Connection c = ConnectionUtil.connect(this.logger);
				CallableStatement s = c.prepareCall(sql);){
			s.setInt(1, inReimbursementid);
			s.setString(2, inStatus);
			s.setString(3, inManager);
			return s.executeUpdate() > 0;
		} catch(SQLException e) {
			logger.error(e.getSQLState());
			logger.error(e.getErrorCode());
			logger.error(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean deleteReimbursement(String inUsername) {
		String sql = "{call delete_reimbursement(?)}";
		try(Connection c = ConnectionUtil.connect(this.logger);
				CallableStatement s = c.prepareCall(sql);){
			s.setString(1, inUsername);
			return s.executeUpdate() > 0;
		} catch(SQLException e) {
			logger.error(e.getSQLState());
			logger.error(e.getErrorCode());
			logger.error(e.getMessage());
			return false;
		}
	}
}
