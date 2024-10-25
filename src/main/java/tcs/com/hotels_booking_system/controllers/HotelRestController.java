package tcs.com.hotels_booking_system.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;
import java.util.List;

import tcs.com.hotels_booking_system.entities.HotelEntity;
import tcs.com.hotels_booking_system.services.HotelService;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api")
public class HotelRestController {

	@Autowired
	private HotelService hotelService;

	@GetMapping("/id/{id}/hotels") // See by ID
	public ResponseEntity<Object> viewId(@PathVariable Long id) {
		return new ResponseEntity<>(hotelService.viewEntity(id), HttpStatus.ACCEPTED);
	}

	@GetMapping("/hotels")
	public ResponseEntity<Object> viewHotels() {
		return new ResponseEntity<>(hotelService.allView(), HttpStatus.ACCEPTED);
	}

	@PostMapping("/hotels")
	public ResponseEntity<Object> create(@RequestBody HotelEntity hotelEntity) {
		hotelEntity.setId(null); // The ID isn´t here, is generated automatically

		// Verify the name of the City Origin isn´t empty
		if (hotelEntity.getHotelName() == null || hotelEntity.getHotelName().trim().isEmpty()) {
			return new ResponseEntity<>("The city of origin cannot be empty", HttpStatus.BAD_REQUEST);
		}
		// Verify the name of the City Destination isn´t empty
		if (hotelEntity.getLocation() == null || hotelEntity.getLocation().trim().isEmpty()) {
			return new ResponseEntity<>("The city of destination cannot be empty", HttpStatus.BAD_REQUEST);
		}

		// Verify the price isn´t empty
		if (hotelEntity.getPrice() <= 0) {
			return new ResponseEntity<>("The price must be a positive value", HttpStatus.BAD_REQUEST);
		}

		// Verify the date isn´t empty
		if (hotelEntity.getCheckIn() == null) {
			return new ResponseEntity<>("Introduce a valid date", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(hotelService.create(hotelEntity), HttpStatus.CREATED); // Modify 201 status
	}

	@PutMapping("/hotels/{id}") // Update all the object
	public ResponseEntity<Object> update(@RequestBody HotelEntity hotelEntity, @PathVariable Long id) { // Access to ID
																										// from the
		// parameter
		if (id == null) {
			return new ResponseEntity<>("Please send an Id", HttpStatus.BAD_REQUEST);
		} else {
			hotelEntity.setId(id); // ID Hotel
			return new ResponseEntity<>(hotelService.updateEntity(hotelEntity), HttpStatus.ACCEPTED);
		}
	}

	@DeleteMapping("/hotels/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id) {
		if (hotelService.isExist(id)) {
			hotelService.delete(id);
			return new ResponseEntity<>("Delete succesfully", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>("The ID " + id + " doesn´t exist, try with valid ID", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/hotels/locations/{location}")
	public ResponseEntity<Object> viewLocations(@PathVariable String location) {
		return new ResponseEntity<>(hotelService.searchLocation(location), HttpStatus.ACCEPTED);
	}

	@GetMapping("/hotels/hotelsName/{hotelName}")
	public ResponseEntity<Object> viewHotelsName(@PathVariable String hotelName) {
		return new ResponseEntity<>(hotelService.searchHotelName(hotelName), HttpStatus.ACCEPTED);
	}

	@GetMapping("/hotels/prices/{min}/{max}")
	public ResponseEntity<Object> viewPrices(@PathVariable Double min, @PathVariable Double max) {
		return new ResponseEntity<>(hotelService.rangePrice(min, max), HttpStatus.ACCEPTED);
	}

	@GetMapping("/hotels/checkin/{startDate}/{endDate}")
	public ResponseEntity<List<HotelEntity>> searchChecksIn(
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
		List<HotelEntity> hotels = hotelService.searchCheckIn(startDate, endDate);
		return new ResponseEntity<>(hotels, HttpStatus.OK);
//	@GetMapping ("hotels/checksIn/{startDate}/{endDate}")
//	public ResponseEntity<Object> viewDate
//	(@PathVariable Date startDate, @PathVariable Date endDate){
//		return new ResponseEntity<>(hotelService.searchCheckIn(startDate,endDate),HttpStatus.ACCEPTED);
//	}
	}
}
