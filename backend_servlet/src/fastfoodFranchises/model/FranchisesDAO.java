package fastfoodFranchises.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fastfoodFranchises.dto.Franchises;
import util.DBUtil;

public class FranchisesDAO {
	private static FranchisesDAO franchisesDAO = new FranchisesDAO();
	
	public static FranchisesDAO getInstance() {
		return franchisesDAO;
	}
	
	// 모든 프랜차이즈 조회
	public ArrayList<Franchises> getAllFranchises( String order ) throws SQLException {
		Connection con = null;	
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Franchises> list = null;

		try {
			con = DBUtil.getConnection();
			stmt = con.createStatement();
			rset = stmt.executeQuery( "select * from fastfood_franchises order by " + order + " desc" );
			list = new ArrayList<Franchises>();
			
			while ( rset.next() ) {
				list.add( construct( rset ) );
			}
		} finally {
			DBUtil.close( rset, stmt, con );
		}
		
		return list;
	}
	
	// 프랜차이즈 지정 조회
	public Franchises getFranchises( String brand ) throws SQLException {
		Connection con = null;	
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Franchises franchises = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement( "select * from fastfood_franchises where brand = ?" );
			pstmt.setString( 1, brand );
			rset = pstmt.executeQuery();
			
			if ( rset.next() ) {
				franchises = construct( rset );
			}
		} finally {
			DBUtil.close( rset, pstmt, con );
		}
		
		return franchises;
	}
	
	// 프랜차이즈 추가
	public boolean insertFranchises( Franchises franchises ) throws SQLException {
		Connection con = null;	
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement( "insert into fastfood_franchises values( ?, ?, ?, ?, ? )" );
			setQuery( pstmt, franchises );
	        result = ( pstmt.executeUpdate() != 0 );
		} finally {
			DBUtil.close( pstmt, con );
		}
		
		return result;
	}
	
	// 프랜차이즈 변경
	public boolean updateFranchises( Franchises franchises ) throws SQLException {
		Connection con = null;	
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement( "update fastfood_franchises set brand=?, company=?, franchises_num=?, avg_sales=?, area_avg_sales=? where brand = ?" );
			setQuery( pstmt, franchises );
			pstmt.setString( 6, franchises.getBrand() );
	        result = ( pstmt.executeUpdate() != 0 );
		} finally {
			DBUtil.close( pstmt, con );
		}
		
		return result;
	}
	
	// 프랜차이즈 지정 삭제
	public boolean deleteFranchises( String brand ) throws SQLException {
		Connection con = null;	
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement( "delete from fastfood_franchises where brand = ?" );
			pstmt.setString( 1, brand );
			result = ( pstmt.executeUpdate() != 0 );

		} finally {
			DBUtil.close( pstmt, con );
		}
		
		return result;
	}
	
	// constructor
	private Franchises construct( ResultSet rset ) throws SQLException {
		return new Franchises(
					rset.getString(1),
					rset.getString(2),
					rset.getInt(3),
					rset.getInt(4),
					rset.getInt(5)
				);
	}
	
	// set_Query
	private void setQuery( PreparedStatement pstmt, Franchises franchises ) throws SQLException {
        		pstmt.setString( 1, franchises.getBrand() );
        		pstmt.setString( 2, franchises.getCompany() );
        		pstmt.setInt( 3, franchises.getFranchisesNum() );
        		pstmt.setInt( 4, franchises.getAvgSales() );
        		pstmt.setInt( 5, franchises.getAreaAvgSales() );
	}
	
}
