package com.mca.infrastructure.in.web.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mca.application.ports.in.SagaRelatedConsultPort;
import com.mca.application.ports.in.SagaVideoGameConsultAdapterPort;
import com.mca.infrastructure.adapters.in.web.SagaRelatedConsultAdapter;
import com.mca.infrastructure.adapters.in.web.SagaVideoGameConsultAdapter;
import com.mca.infrastructure.adapters.in.web.controller.SagaController;
import com.mca.infrastructure.model.Saga;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
@AutoConfigureMockMvc
class SagaControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private SagaVideoGameConsultAdapter sagaVideoGameConsultAdapter;

	@Mock
	private SagaRelatedConsultAdapter sagaRelatedConsultAdapter;

	@InjectMocks
	private SagaController sagaController;

	@Autowired
	private ObjectMapper objectMapper; // ObjectMapper para convertir el JSON en objetos Java

	@BeforeEach
	void setUp() throws IOException {
		this.sagaController = new SagaController(sagaVideoGameConsultAdapter, sagaRelatedConsultAdapter);
	}

	@Test
	void testGetSagasByGameIdReturnsBadRequest() throws Exception {
		ResponseEntity<?> response = sagaController.getSagasByGameId(null);
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	@Test
	void testGetSagasByGameIdReturnsNotFound() throws Exception {
		ResponseEntity<?> response = sagaController.getSagasByGameId('t');
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	@Test
	void testGetSagasRelatedBySagaIdReturnsBadRequest() throws Exception {
		ResponseEntity<?> response = sagaController.getSagasRelatedBySagaId(null);
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	@Test
	void testGetSagasRelatedBySagaIdReturnsNotFound() throws Exception {
		ResponseEntity<?> response = sagaController.getSagasRelatedBySagaId('t');
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	@Test
	void testGetSagasByGameId_Success() throws Exception {
		// Load data from mock_data.json file
		List<Map<String, Object>> mockData = objectMapper.readValue(
				Paths.get("mock/mock_data.json").toFile(),
				new TypeReference<List<Map<String, Object>>>() {}
		);

		// Configure the mocks using the data in the file
		for (Map<String, Object> mock : mockData) {
			String path = (String) mock.get("path");
			String body = (String) mock.get("body");
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			String id = path.split("/")[3];


			// Set the expected result for each request according to the request path
			if (path.contains("game/")) {
				List<Saga> expectedResponse = objectMapper.readValue(body, new TypeReference<List<Saga>>() {});
				doReturn(ResponseEntity.ok(expectedResponse))
						.when(this.sagaVideoGameConsultAdapter)
						.getListSagasByGameId(Integer.parseInt(id));
				ResponseEntity<?> responseTest = sagaController.getSagasByGameId(id);
				Assertions.assertEquals(HttpStatus.OK, responseTest.getStatusCode());

				List<Saga> responseBodyList = (List<Saga>) responseTest.getBody();

				// Compare expected and actual lists
				Assertions.assertEquals(expectedResponse.size(), responseBodyList.size());
				for (int i = 0; i < expectedResponse.size(); i++) {
					Saga expectedSaga = expectedResponse.get(i);
					Saga actualSaga = responseBodyList.get(i);
					Assertions.assertEquals(expectedSaga.getId(), actualSaga.getId());
					Assertions.assertEquals(expectedSaga.getTitle(), actualSaga.getTitle());
					Assertions.assertEquals(expectedSaga.getRelevance(), actualSaga.getRelevance());
				}

			}


		}
	}

	@Test
	void testGetSagasRelatedBySagaId_Success() throws Exception {
		// Load data from mock_data.json file
		List<Map<String, Object>> mockData = objectMapper.readValue(
				Paths.get("mock/mock_data.json").toFile(),
				new TypeReference<List<Map<String, Object>>>() {}
		);

		// Configure the mocks using the data in the file
		for (Map<String, Object> mock : mockData) {
			String path = (String) mock.get("path");
			String body = (String) mock.get("body");
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			String id = path.split("/")[3];


			// Set the expected result for each request according to the request path
			if (path.contains("game-saga")) {
				List<Integer> expectedResponse = objectMapper.readValue(body, new TypeReference<List<Integer>>() {});
				doReturn(ResponseEntity.ok(expectedResponse))
						.when(this.sagaRelatedConsultAdapter)
						.getIdsSagasRelatedBySagaId(Integer.parseInt(id));
				ResponseEntity<?> responseTest = sagaController.getSagasRelatedBySagaId(id);
				Assertions.assertEquals(HttpStatus.OK, responseTest.getStatusCode());
				String[] strArray = body.split(",");

				String[] responseBodyList = (String[]) responseTest.getBody().toString().split(",");
				Assertions.assertEquals(strArray[0].trim(), responseBodyList[0].trim());
				Assertions.assertEquals(strArray[1].trim(), responseBodyList[1].trim());
				Assertions.assertEquals(strArray[2].trim(), responseBodyList[2].trim());
			}

		}

	}
}