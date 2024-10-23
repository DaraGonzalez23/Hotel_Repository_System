package tcs.com.hotels_booking_system.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tcs.com.hotels_booking_system.entities.HotelEntity;
import tcs.com.hotels_booking_system.repositories.HotelRepository;
import tcs.com.hotels_booking_system.services.HotelService;
import java.util.Date;

@Service
public class HotelServiceImplement implements HotelService {

	@Autowired
	private HotelRepository hotelRepository;

	@Override
	public HotelEntity create(HotelEntity hotelEntity) {
		return hotelRepository.save(hotelEntity);
	}

	@Override
	public List<HotelEntity> allView() {
		return hotelRepository.findAll();
	}

	@Override
	public HotelEntity viewEntity(Long id) {
//		Optional<HotelEntity> hotelEntity = hotelRepository.findById(id);
//		if (!hotelEntity.isPresent()) {
//			return new HotelEntity();
//		} else {
//			return hotelEntity.get();
//		}
		return hotelRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Reservation with ID " + id + " not found"));
	}

	@Override
	public HotelEntity updateEntity(HotelEntity hotelEntity) {
		if (!hotelRepository.existsById(hotelEntity.getId())) {
			throw new IllegalArgumentException("Flight with ID " + hotelEntity.getId() + " doesn´t exist.");
		}
		return hotelRepository.save(hotelEntity);
	}

	@Override
	public void delete(Long id) {
		if (hotelRepository.existsById(id)) { // Check if the entity exists before deleting
			hotelRepository.deleteById(id);
		} else {
			throw new IllegalArgumentException("Reservation with ID " + id + " doesn´t exist."); // the method would
																									// attempt to delete
																									// a non-existing
																									// entity
		}

	}

	@Override
	public boolean isExist(Long i) {
		return hotelRepository.existsById(i);
	}

	@Override
	public List<HotelEntity> searchLocation(String location) {
		return hotelRepository.searchLocation(location);
	}

	@Override
	public List<HotelEntity> searchHotelName(String hotelName) {
		return hotelRepository.searchHotelName(hotelName);
	}

	@Override
	public List<HotelEntity> rangePrice(Double min, Double max) {
		return hotelRepository.rangePrice(min, max);
	}

	@Override
	public List<HotelEntity> searchCheckIn(Date startDate, Date endDate) {
		return hotelRepository.searchCheckIn(startDate, endDate);
	}

}
