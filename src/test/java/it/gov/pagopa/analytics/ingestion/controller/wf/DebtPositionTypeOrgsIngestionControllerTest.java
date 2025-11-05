package it.gov.pagopa.analytics.ingestion.controller.wf;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.gov.pagopa.analytics.ingestion.dto.generated.WorkflowCreatedDTO;
import it.gov.pagopa.analytics.ingestion.wf.dptypeorg.DebtPositionTypeOrgsIngestionWFClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DebtPositionTypeOrgsIngestionControllerImpl.class)
@AutoConfigureMockMvc(addFilters = false)
class DebtPositionTypeOrgsIngestionControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockitoBean
  private DebtPositionTypeOrgsIngestionWFClient wfClientMock;

  @Test
  void whenProcessAssessmentsClassificationsThenOk() throws Exception {
    String workflowId = "workflow-1";
    String runId = "runId";
    WorkflowCreatedDTO expected = WorkflowCreatedDTO.builder()
      .workflowId(workflowId)
      .runId(runId)
      .build();

    Mockito.when(wfClientMock.ingestDebtPositionTypeOrgs())
      .thenReturn(expected);

    MvcResult result = mockMvc.perform(
        post("/workflow/debt-position-type-orgs-ingestion"))
      .andExpect(status().isCreated())
      .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
      .andReturn();

    WorkflowCreatedDTO resultResponse =
      objectMapper.readValue(result.getResponse().getContentAsString(), WorkflowCreatedDTO.class);
    assertEquals(expected, resultResponse);
  }
}
