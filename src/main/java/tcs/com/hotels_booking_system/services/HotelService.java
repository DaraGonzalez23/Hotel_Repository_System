package tcs.com.hotels_booking_system.services;

import java.util.List;

import tcs.com.hotels_booking_system.entities.HotelEntity;
import java.util.Date;

public interface HotelService {

	HotelEntity create(HotelEntity hotelEntity);

	List<HotelEntity> allView();

	HotelEntity viewEntity(Long Id);

	HotelEntity updateEntity(HotelEntity hotelEntity);

	void delete(Long id);
	
	boolean isExist(Long id);
	
	List<HotelEntity> searchLocation(String location);
	
	List<HotelEntity> searchHotelName (String hotelName);
	
	List<HotelEntity> rangePrice (Double min, Double max);
	
	List<HotelEntity> searchCheckIn (Date startDate, Date endDate);
	

}
