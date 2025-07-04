package org.tk.spring.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {RestJavaConfig.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestRestController {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    void badRequest() throws Exception {
        MvcResult result = mockMvc.perform(get("/rest/stock/NSE:ABC")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andReturn();
        MockHttpServletResponse response = result.getResponse();
        ObjectMapper mapper = new ObjectMapper();
        StockError error = mapper.readValue(response.getContentAsByteArray(), StockError.class);
        Assertions.assertEquals(400, response.getStatus());
        Assertions.assertTrue("00122".equals(error.getErrorCode()));
    }

    @Test
    void getStockPriceTickerAsJSON() throws Exception {
        MvcResult result = mockMvc.perform(get("/rest/stock/NSE:ITC").contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andReturn();
        MockHttpServletResponse response = result.getResponse();
        ObjectMapper mapper = new ObjectMapper();
        Stock stock = mapper.readValue(response.getContentAsByteArray(), Stock.class);
        Assertions.assertTrue(stock.getCompany().equals("NSE:ITC"));
    }

    @Test
    void getAllStocksAsJSON() throws Exception {
        MvcResult result = mockMvc.perform(get("/rest/stocks")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
                .andReturn();
        MockHttpServletResponse response = result.getResponse();
        ObjectMapper mapper = new ObjectMapper();
        //https://www.baeldung.com/jackson-linkedhashmap-cannot-be-cast
        List<Stock> stocks = mapper.readValue(response.getContentAsByteArray(), new TypeReference<>() {
        });
        stocks.stream().forEach(System.out::println);
        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    void createNewStock() throws Exception {
        Stock newStock = new Stock("NSE:TK", 450.9);
        ObjectMapper mapper = new ObjectMapper();

        MockHttpServletRequestBuilder putBuilder = put("/rest/stock/" + newStock.getCompany())
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding("UTF-8")
                .content(mapper.writeValueAsString(newStock));

        MvcResult result = mockMvc.perform(putBuilder)
                .andDo(print())
                .andReturn();

        Assertions.assertEquals(201, result.getResponse().getStatus());
    }

    @Test
    void createNewStockValidated() throws Exception {
        Stock newStock = new Stock("NSE:ERR", 0.0);
        ObjectMapper mapper = new ObjectMapper();

        MockHttpServletRequestBuilder putBuilder = put("/rest/stock/" + newStock.getCompany())
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding("UTF-8")
                .content(mapper.writeValueAsString(newStock));

        MvcResult result = mockMvc.perform(putBuilder)
                .andDo(print())
                .andReturn();

    }

    @Test
    void getAllStocksAsXML() throws Exception {
        MvcResult result = mockMvc.perform(get("/rest/stocks")
                .accept(MediaType.APPLICATION_XML_VALUE)
                .contentType(MediaType.APPLICATION_XML_VALUE)).andDo(print())
                .andReturn();
        MockHttpServletResponse response = result.getResponse();

        XmlMapper xmlMapper = new XmlMapper();
        //https://www.baeldung.com/jackson-linkedhashmap-cannot-be-cast
        //ArrayList<LinkedHashMap> not as List<Stock>  - error if you access list as List<Stock>
        List<Stock> list = xmlMapper.readValue(response.getContentAsString(), List.class);
        //ERROR -> System.out.println(list.getClass()  +"-what object-"+ list.get(0).getClass());

        list = xmlMapper.readValue(response.getContentAsString(), new TypeReference<List<Stock>>() {
        });
        list.stream().forEach(System.out::println);
        Assertions.assertEquals(200, response.getStatus());
    }

}
