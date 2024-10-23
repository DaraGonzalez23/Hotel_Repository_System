package tcs.com.hotels_booking_system.entities;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "hotel")
public class HotelEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "hotel_id", unique = true, nullable = false)
	private Long id;

	@Column(name ="hotel_name")
	private String hotelName;
	
	@Column(name ="location")
	private String location;
	
	@Column (name = "check_in")
	private Date checkIn;
	
	@Column(name ="price")
	private Double price;
	
	//Empty Constructor
	public HotelEntity() {}
	
	//Constructor
	
	public HotelEntity(Long id, String hotelName, String location, Date checkIn, Double price) {
		this.id = id;
		this.hotelName = hotelName;
		this.location = location;
		this.checkIn = checkIn;
		this.price = price;
	}
	
	public Long getId () {
		return id;
	}
	
	public void setId (Long id) {
		this.id = id;
	}
	
	public String getHotelName () {
		return hotelName;
	}
	
	public void setHotelName (String hotelName) {
		this.hotelName = hotelName;
	}
	
	public String getLocation () {
		return location;
	}
	
	public void setLocation (String location) {
		this.location = location;
	}
	
	public Date getCheckIn () {
		return checkIn;
	}
	
	public void setCheckIn (Date checkIn) {
		this.checkIn = checkIn;
	}
	
	public Double getPrice () {
		return price;
	}
	
	public void setPrice (Double price) {
		this.price = price;
	}
}
