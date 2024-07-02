package com.example.demo;

import com.example.demo.Models.Tutorial;
import com.example.demo.Repositories.ITutorialRepository;
import com.example.demo.Services.TutorialServices;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

	@SpringBootTest
	class TutorialsApiApplicationTests {

		@Mock
		private ITutorialRepository iTutorialRepository;

		@InjectMocks
		//inyeccion de dependencias. Inyecta los mocks en el objeto de prueba.
		private TutorialServices tutorialServices;

		/*El método MockitoAnnotations.openMocks(this) se utiliza para inicializar los mocks que han sido anotados con @Mock y @InjectMocks. En otras palabras, se encarga de preparar los objetos simulados (mocks) que se utilizarán en las pruebas.*/
		@BeforeEach
		// indica que el metodo se deje ejecutar antes de cada metodo
		public void setUp() {
			MockitoAnnotations.openMocks(this);
		}

		@Test
		public void test_if_createTutorial_creates_Id() {
			//Arrange
			Tutorial newTutorial = new Tutorial();
			newTutorial.setId(1);
			when(iTutorialRepository.save(any(Tutorial.class))).thenReturn(newTutorial);

			//Act
			Tutorial result = tutorialServices.createTutorial(newTutorial);

			//Assert
			assertNotNull(result);
			assertEquals(1, result.getId());
			verify(iTutorialRepository).save(newTutorial);
		}

		@Test
		public void test_if_createTutorial_creates_title() {
			//Arrange
			Tutorial newTutorial = new Tutorial();
			newTutorial.setTitle("New Tutorial");

			when(iTutorialRepository.save(any(Tutorial.class))).thenReturn(newTutorial);

			//Act
			Tutorial result = tutorialServices.createTutorial(newTutorial);

			//Assert
			assertNotNull(result);
			assertEquals("New Tutorial", result.getTitle());
			verify(iTutorialRepository).save(newTutorial);
		}

		@Test
		public void test_if_createTutorial_creates_description() {
			//Arrange
			Tutorial newTutorial = new Tutorial();
			newTutorial.setDescription("Here goes a detailed description of tutorial");

			when(iTutorialRepository.save(any(Tutorial.class))).thenReturn(newTutorial);

			//Act
			Tutorial result = tutorialServices.createTutorial(newTutorial);

			//Assert
			assertNotNull(result);
			assertEquals("Here goes a detailed description of tutorial", result.getDescription());
			verify(iTutorialRepository).save(newTutorial);
		}

		@Test
		public void test_if_GetAllTutorials_gets_title() {
			//Arrange
			ArrayList<Tutorial> listOfTutorials = new ArrayList<>();

			Tutorial newTutorial1 = new Tutorial(1, "buy bread", "go to baker street");
			Tutorial newTutorial2 = new Tutorial(2, "go to gym", "practice a bit of soccer");
			Tutorial newTutorial3 = new Tutorial(3, "play guitar", "do agility exercises");
			listOfTutorials.add(newTutorial1);
			listOfTutorials.add(newTutorial2);
			listOfTutorials.add(newTutorial3);
			when(iTutorialRepository.findAll()).thenReturn(listOfTutorials);


			//Act
			ArrayList<Tutorial> result = tutorialServices.getAllTutorials();
			//Assert
			assertNotNull(result);
			assertEquals(3, result.size());
			assertEquals("buy bread", result.get(0).getTitle());
			assertEquals("go to gym", result.get(1).getTitle());
			assertEquals("play guitar", result.get(2).getTitle());

			verify(iTutorialRepository).findAll();
		}

		@Test
		public void test_if_GetAllTutorials_gets_description() {
			//Arrange
			ArrayList<Tutorial> listOfTutorials = new ArrayList<>();

			Tutorial newTutorial1 = new Tutorial(1, "buy bread", "go to baker street");
			Tutorial newTutorial2 = new Tutorial(2, "go to gym", "practice a bit of soccer");
			Tutorial newTutorial3 = new Tutorial(3, "play guitar", "do agility exercises");
			listOfTutorials.add(newTutorial1);
			listOfTutorials.add(newTutorial2);
			listOfTutorials.add(newTutorial3);
			when(iTutorialRepository.findAll()).thenReturn(listOfTutorials);


			//Act
			ArrayList<Tutorial> result = tutorialServices.getAllTutorials();
			//Assert
			assertNotNull(result);
			assertEquals(3, result.size());

			assertEquals("go to baker street", result.get(0).getDescription());
			assertEquals("practice a bit of soccer", result.get(1).getDescription());
			assertEquals("do agility exercises", result.get(2).getDescription());
			verify(iTutorialRepository).findAll();
		}

		@Test
		public void test_if_deleteAllTutorials_deletes_all() {
			//Arrange
			ArrayList<Tutorial> listOfTutorials = new ArrayList<>();

			Tutorial newTutorial1 = new Tutorial(1, "buy bread", "go to baker street");
			Tutorial newTutorial2 = new Tutorial(2, "go to gym", "practice a bit of soccer");
			Tutorial newTutorial3 = new Tutorial(3, "play guitar", "do agility exercises");
			listOfTutorials.add(newTutorial1);
			listOfTutorials.add(newTutorial2);
			listOfTutorials.add(newTutorial3);
			when(tutorialServices.getAllTutorials()).thenReturn(listOfTutorials);

			//Act
			tutorialServices.deleteAllTutorials(listOfTutorials);

			verify(iTutorialRepository).deleteAll(any());
		}

		@Test
		public void test_if_deleteTutorialById_deletes_by_Id() {
			//Arrange
			ArrayList<Tutorial> listOfTutorials = new ArrayList<>();

			Tutorial newTutorial1 = new Tutorial(1, "buy bread", "go to baker street");
			Tutorial newTutorial2 = new Tutorial(2, "go to gym", "practice a bit of soccer");
			Tutorial newTutorial3 = new Tutorial(3, "play guitar", "do agility exercises");
			listOfTutorials.add(newTutorial1);
			listOfTutorials.add(newTutorial2);
			listOfTutorials.add(newTutorial3);
			when(tutorialServices.getAllTutorials()).thenReturn(listOfTutorials);

			//Act
			tutorialServices.deleteTutorialById(2);

			//Assert
			verify(iTutorialRepository).deleteById(2);
		}

	}

}
