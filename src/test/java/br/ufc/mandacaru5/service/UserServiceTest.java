package br.ufc.mandacaru5.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.ufc.mandacaru5.model.Property;
import br.ufc.mandacaru5.model.User;
import br.ufc.mandacaru5.repository.UserRepository;

public class UserServiceTest {
	
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
	private UserService service;
	
	@Mock
	private UserRepository repository;	
	
	private User user;
	private List<Property> listProperty;
	
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
	public void whenFindByIdThenReturnAUser() {
	
		Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(Optional.of(user));
		
		User response = service.find(ID);
		
		Assertions.assertNotNull(response);
		Assertions.assertEquals(User.class, response.getClass());
		Assertions.assertEquals(ID, response.getId());
		Assertions.assertEquals(NAME, response.getName());
		Assertions.assertEquals(EMAIL, response.getEmail());
		Assertions.assertEquals(PASSWORD, response.getPassword());
		Assertions.assertEquals(CPF, response.getCpf());
		Assertions.assertEquals(PHONE_NUMBER, response.getPhoneNumber());
		Assertions.assertEquals(ADDRESS, response.getAddress());
		Assertions.assertEquals(listProperty, response.getProperties());
		
	}
	
	@Test
	public void whenFindByIdThenReturnNullIfIdLessThan1 () {
		User response = service.find(0);
		
		assertNull(response);
	}
	

	@Test
	public void whenFindByIdThenReturnNullIfOptionalNotPresent () {
		when(repository.findById(anyInt())).thenReturn(Optional.empty());
		
		User response = service.find(ID);
		
		assertNull(response);
	}
	
	@Test
	public void whenFindAllThenReturnAnList() {
		when(repository.findAll()).thenReturn(List.of(user));
		
		List<User> response = service.findAll();
		
		Assertions.assertNotNull(response);
		Assertions.assertEquals(ID, response.get(0).getId());
		Assertions.assertEquals(NAME, response.get(0).getName());
		Assertions.assertEquals(EMAIL, response.get(0).getEmail());
		Assertions.assertEquals(PASSWORD, response.get(0).getPassword());
		Assertions.assertEquals(CPF, response.get(0).getCpf());
		Assertions.assertEquals(PHONE_NUMBER, response.get(0).getPhoneNumber());
		Assertions.assertEquals(ADDRESS, response.get(0).getAddress());
		Assertions.assertEquals(listProperty, response.get(0).getProperties());
	}
	
	@Test
	public void whenSaveVerifySuccess() {
		when(repository.save(any())).thenReturn(user);
		service.save(0, user);
		verify(repository).save(any());
	}
	
	@Test
		public void whenDeleteVerifySuccess() {
		when(repository.findById(anyInt())).thenReturn(Optional.of(user));
		doNothing().when(repository).delete(user);
		
		service.delete(ID);
		
		verify(repository).findById(anyInt());
		verify(repository).delete(any());
	}
	
}
