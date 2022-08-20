package br.ufc.mandacaru5.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.ufc.mandacaru5.model.Property;
import br.ufc.mandacaru5.model.User;
import br.ufc.mandacaru5.service.PropertyService;
import br.ufc.mandacaru5.service.UserService;

public class UserControllerTest {
	
	// User
	private static final int ID = 1;
	private static final String NAME = "João";
	private static final String EMAIL = "joao@alu.ufc.br";
	private static final String PASSWORD = "123";
	private static final String CPF = "111.111.111-11";
	private static final String PHONE_NUMBER = "1010-8888";
	private static final String ADDRESS = "Rua dos Mandacarus";
		
	// Property
	private static final int ID_P1 = 1;
	private static final String TITLE_1 = "Casa 1";
	private static final String ADDRESS_1 = "Casa 1";
	private static final double TERRAIN_AREA_1 = 450.0;
	private static final double CONSTRUCTED_AREA_1 = 350.0;
	private static final int ROOMS_1 = 1;
	private static final int BATHROOMS_1 = 1;
	private static final int GARAGE_VACANCIES_1 = 1;
	private static final double PRICE_1 = 400000.0;
	private static final String STATUS_1 = "Pending";
	
	private static final int ID_P2 = 1;
	private static final String TITLE_2 = "Casa 2";
	private static final String ADDRESS_2 = "Casa 2";
	private static final double TERRAIN_AREA_2 = 250.0;
	private static final double CONSTRUCTED_AREA_2 = 150.0;
	private static final int ROOMS_2 = 2;
	private static final int BATHROOMS_2 = 2;
	private static final int GARAGE_VACANCIES_2 = 2;
	private static final double PRICE_2 = 800000.0;
	private static final String STATUS_2 = "Approved";

	@InjectMocks
	private UserController controller;

	@Mock
	private UserService service;

	private User user;

	private List<Property> listProperty;

    @Autowired
    private TestRestTemplate restTemplate;

	private void start() {
		Property property1 = new Property();
		property1.setId(ID_P1);
		property1.setTitle(TITLE_1);
		property1.setAddress(ADDRESS_1);
		property1.setTerrainArea(TERRAIN_AREA_1);
		property1.setConstructedArea(CONSTRUCTED_AREA_1);
		property1.setRooms(ROOMS_1);
		property1.setBathroons(BATHROOMS_1);
		property1.setGarageVacancies(GARAGE_VACANCIES_1);
		property1.setPrice(PRICE_1);
		property1.setStatus(STATUS_1);
		
		Property property2 = new Property();
		property2.setId(ID_P2);
		property2.setTitle(TITLE_2);
		property2.setAddress(ADDRESS_2);
		property1.setTerrainArea(TERRAIN_AREA_2);
		property1.setConstructedArea(CONSTRUCTED_AREA_2);
		property2.setRooms(ROOMS_2);
		property2.setBathroons(BATHROOMS_2);
		property2.setGarageVacancies(GARAGE_VACANCIES_2);
		property2.setPrice(PRICE_2);
		property2.setStatus(STATUS_2);
		
		listProperty = new ArrayList<Property>();
		
		user = new User(ID, NAME, EMAIL, PASSWORD, CPF, PHONE_NUMBER, ADDRESS, listProperty);
	}

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		start();
	}

	@Test
	public void whenFindByIdThenReturnSuccess() {
		when(service.find(anyInt())).thenReturn(user);

		ResponseEntity<User> response = controller.find(ID);

		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(User.class, response.getBody().getClass());
		assertEquals(ID, response.getBody().getId());
		assertEquals(NAME, response.getBody().getName());
		// assertEquals(PRICE, response.getBody().getPrice());
		assertEquals(listProperty, response.getBody().getProperties());
	}

	@Test
	public void whenFindAllThenReturnSuccess() {
		when(service.findAll()).thenReturn(List.of(user));

		ResponseEntity<List<User>> response = controller.findAll();

		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(ID, response.getBody().get(0).getId());
		assertEquals(NAME, response.getBody().get(0).getName());
		// assertEquals(PRICE, response.getBody().get(0).getPrice());
		assertEquals(listProperty, response.getBody().get(0).getProperties());
	}

	@Test
	public void whenCreateThenReturnSuccess() {
		doNothing().when(service).save(0, user);

		controller.save(user);

		verify(service).save(0, user);
	}

	@Test
	public void whenUpdateThenReturnSuccess() {
		doNothing().when(service).save(ID, user);

		controller.update(ID, user);

		verify(service).save(ID, user);
	}

	@Test
	public void whenDeleteThenReturnSuccess() {
		doNothing().when(service).delete(ID);

		controller.delete(ID);

		verify(service).delete(ID);
	}

	@Test
	public void whenFindByNameThenReturnSuccess() {
		when(service.findByName(anyString())).thenReturn(user);

		ResponseEntity<User> response = controller.findByName(NAME);

		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(User.class, response.getBody().getClass());
		assertEquals(ID, response.getBody().getId());
		assertEquals(NAME, response.getBody().getName());
		// assertEquals(PRICE, response.getBody().getPrice());
		assertEquals(listProperty, response.getBody().getProperties());
	}

	@Test
	public void whenFindByNameThenReturnNotFound() {
		when(service.findByName(anyString())).thenReturn(null);

		ResponseEntity<User> response = controller.findByName(NAME);

		assertNotNull(response);
		assertNull(response.getBody());
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
}