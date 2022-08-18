package br.ufc.mandacaru5.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="properties")
public class Property {

	@Id
	@GeneratedValue(generator = "sequence-generator")
	  @GenericGenerator(
		      name = "sequence-generator",
		      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
		      parameters = {
		        @Parameter(name = "sequence_name", value = "property_sequence"),
		        @Parameter(name = "initial_value", value = "1"),
		        @Parameter(name = "increment_size", value = "1")
		        }
		    )
	private int id;
	private String title;
	private String address;
	private Double terrainArea;
	private Double constructedArea;
	private int rooms;
	private int bathrooms;		
	private int garageVacancies;
	private double price;
	private String status;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;
	

	public Property() {
		super();
	}

	public Property(int id, String title, String address, Double terrainArea, Double constructedArea, int rooms,
			int bathrooms, int garageVacancies, double price, String status, User user) {
		super();
		this.id = id;
		this.title = title;
		this.address = address;
		this.terrainArea = terrainArea;
		this.constructedArea = constructedArea;
		this.rooms = rooms;
		this.bathrooms = bathrooms;
		this.garageVacancies = garageVacancies;
		this.price = price;
		this.status = status;
		this.user = user;
	}

	@Override
	public String toString() {
		return "Imovel [id=" + id + ", title=" + title + ", address=" + address + ", terrainArea=" + terrainArea
				+ ", constructedArea=" + constructedArea + ", rooms=" + rooms + ", bathrooms=" + bathrooms
				+ ", garageVacancies=" + garageVacancies + ", price=" + price + ", status=" + status + ", user=" + user
				+ "]";
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getTerrainArea() {
		return terrainArea;
	}

	public void setTerrainArea(Double terrainArea) {
		this.terrainArea = terrainArea;
	}

	public Double getConstructedArea() {
		return constructedArea;
	}

	public void setConstructedArea(Double constructedArea) {
		this.constructedArea = constructedArea;
	}

	public int getRooms() {
		return rooms;
	}

	public void setRooms(int rooms) {
		this.rooms = rooms;
	}

	public int getBathroons() {
		return bathrooms;
	}

	public void setBathroons(int bathroons) {
		this.bathrooms = bathroons;
	}

	public int getGarageVacancies() {
		return garageVacancies;
	}

	public void setGarageVacancies(int garageVacancies) {
		this.garageVacancies = garageVacancies;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}