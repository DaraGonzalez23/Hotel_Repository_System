package tcs.com.hotels_booking_system.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Date;


import tcs.com.hotels_booking_system.entities.HotelEntity;

@Repository
public interface HotelRepository extends JpaRepository<HotelEntity, Long> {

	// METHODS IN THE INTERFACE
	@Query("SELECT h " 
			+ " from HotelEntity h " 
			+ " where h.location like %:search% ")
	public List<HotelEntity> searchLocation(String search);

	@Query ("SELECT h "
			+ " from HotelEntity h "
			+ " where h.hotelName like %:searchName% ")
	public List<HotelEntity> searchHotelName(String searchName);
	
	@Query ("SELECT h "
			+ " from HotelEntity h "
			+ " where h.price between :min and :max " ) //Everytime I put a variable : and then the variable / or you can find ?, empata with the first parameter
	public List<HotelEntity> rangePrice(Double min, Double max); //It can use Search but usually use FIND

	@Query ("SELECT h "
			+ " from HotelEntity h "
			+ " where h.checkIn between :startDate and :endDate ")
	public List<HotelEntity> searchCheckIn (Date startDate, Date endDate);
}
